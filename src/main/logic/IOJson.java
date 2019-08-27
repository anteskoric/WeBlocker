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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.classes.WebsiteVisitTracer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class logic.IOJson is used for writing and deleting data from the json file
 *
 * @author Ante Skoric
 */

public final class IOJson {
    private IOJson(){}

    /**
     * Saves today's date as integer (Unix date timestamp), User's input (website URL) and User's input (average hours per day)
     * @param url users URL input of the website that should be blocked
     */
    public static void saveManualData(String url, long hours) {
        Gson gson = new GsonBuilder().create();
        LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("Europe/Berlin"));

        if(hours > 24)
            throw new IllegalArgumentException("The day contains 24 hours");

        long seconds = DateManager.getSecondsFromUTF(currentTime);

        List<WebsiteVisitTracer> tracers = IOJson.getJsonObjects();
        tracers.add(new WebsiteVisitTracer(url,hours,seconds));
        try(FileWriter writer = new FileWriter("C:\\Users\\agrok\\Desktop\\WeBlocker\\src\\main\\files\\VisitTracer.json")) {
            gson.toJson(tracers,writer);
        }catch (IOException a){
            System.err.println(a.getMessage());
        }
    }

    /**
     * Get data from the json file and store it into list
     * @return list of the objects from the json file
     */
    public static List<WebsiteVisitTracer> getJsonObjects() {
        List<WebsiteVisitTracer> tracers = new ArrayList<>();
        try(FileReader reader = new FileReader("C:\\Users\\agrok\\Desktop\\WeBlocker\\src\\main\\files\\VisitTracer.json")){
            WebsiteVisitTracer[] tracersArray = new Gson().fromJson(reader,WebsiteVisitTracer[].class);
            tracers = Arrays.stream(tracersArray).collect(Collectors.toList());
        }catch (IOException a){
            System.err.println(a.getMessage());
        }
        return tracers;
    }

    /**
     * Overwrites the VisitTracer.json with the objects from the List tracers
     * @param tracers the List of objects that will be saved in json
     */
    public static void overwriteJson(List<WebsiteVisitTracer> tracers){
        Gson gson = new GsonBuilder().create();
        try(FileWriter writer = new FileWriter("C:\\Users\\agrok\\Desktop\\WeBlocker\\src\\main\\files\\VisitTracer.json")) {
            gson.toJson(tracers,writer);
        }catch (IOException a){
            System.err.println(a.getMessage());
        }
    }
}
