package logic;

// The MIT License
//
//Copyright (c) 2010-2019 Google, Inc. http://angularjs.org

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

import interfaces.DataBaseConnector;
import json.classes.WebsiteVisitTracer;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * The class WebsiteBlocker is a utility class for checking if the json web url objects should be blocked or not.
 * If the object from the VisitTracer.json is older then one week it will be read
 * and it will be checked if the average usage is grater then the users input, if the usage is greater the website
 * will be blocked
 * The class is called from MainMenuController and it reads objects from VisitTracer.json
 *
 * @author Ante Skoric
 */
public final class WebsiteBlocker implements DataBaseConnector {

    private WebsiteBlocker() {
    }

    /**
     * The method checkWebsiteBlockage gets all objects that are older than one week
     * from VisitTracer.json and calls checkWebsiteUsage
     */
    public static void checkWebsiteBlockage() {
        List<WebsiteVisitTracer> uncheckedTrackers = new ArrayList<>();
        LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("Europe/Berlin"));
        long currentTimeInSeconds = DateManager.getSecondsFromUTF(currentTime);
        long sevenDaysInSeconds = 604800;
        List<WebsiteVisitTracer> tracers = IOJson.getJsonObjects();
        for (WebsiteVisitTracer tracer : tracers) {
            if (tracer.getEntryCreation() <= (currentTimeInSeconds - sevenDaysInSeconds)) {
                checkWebsiteUsage(tracer, currentTimeInSeconds);
            } else {
                uncheckedTrackers.add(tracer);
            }
        }
        IOJson.overwriteJson(uncheckedTrackers);
    }

    /**
     * The method checkWebsiteUsage checks if the average daily usage of the website is
     * greater then allowed daily usage (Users input), if it is greater the website will be blocked
     *
     * @param tracer  The WebsiteVisitTracer object that should be checked
     * @param endTime the time in seconds when the last visit is allowed
     */
    private static void checkWebsiteUsage(WebsiteVisitTracer tracer, long endTime) {
        String sqlStatement = "SELECT SUM(visit_duration) AS visits,\n" +
                "       url\n" +
                " FROM visits\n" +
                " WHERE url = (\n" +
                "                 SELECT id\n" +
                "                  FROM urls\n" +
                "                  WHERE url = '" + tracer.getUrl() + "'\n" +
                "             )\n" +
                "AND \n" +
                "       visit_time BETWEEN " + DateManager.getMicrosecondsFromSeconds(tracer.getEntryCreation()) + " AND " + DateManager.getMicrosecondsFromSeconds(endTime)+ "\n" +
                " GROUP BY url;\n";

        long averageDailyUsage = 0;
        try (Connection connect = DataBaseConnector.connect("jdbc:sqlite:C:\\Users\\agrok\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\History");
             ResultSet resultSet = connect.createStatement().executeQuery(sqlStatement)) {
            while (resultSet.next()) {
                averageDailyUsage = resultSet.getLong("visits") / 7;
            }
        } catch (SQLException a) {
            //TODO make into logs
            System.err.println(a.getMessage());
        }
        long allowedDailyUsageInMicroseconds = DateManager.getMicrosecondsFromHours(tracer.getHours());
        if (averageDailyUsage > allowedDailyUsageInMicroseconds)
            IOHosts.writeIntoHost(IOHosts.getHostName(tracer.getUrl()));
    }
}