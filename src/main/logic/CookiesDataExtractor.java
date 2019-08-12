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

import java.sql.*;

/**
 * SQLite scripts imbedded into Java for finding and updating data in the Google Chrome cookies database
 * @author Ante Skoric
 */

public final class CookiesDataExtractor implements DataBaseConnector {
    private CookiesDataExtractor(){}

    //TODO remove this
    public static void main(String[] args) {
        CookiesDataExtractor.selectCookies();
    }

    /**
     * Get name, host key, has expires, creation utc, expires utc, last access utc of the cookies from the Cookies database
     */
    public static void selectCookies(){
        String sqlStatement = "SELECT name, host_key, has_expires, creation_utc, expires_utc, last_access_utc FROM cookies";

        try(Connection connect = DataBaseConnector.connect("jdbc:sqlite:C:\\Users\\agrok\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Cookies");
            ResultSet resultSet = connect.createStatement().executeQuery(sqlStatement)){
            while (resultSet.next()){
                System.out.println(resultSet.getArray("name"));
            }
        }catch (SQLException a){
            //TODO make into logs
            System.err.println(a.getErrorCode());
        }
    }

    /**
     * Delete the cookie from the database Cookie
     * @param creationUtc the creation utc for the sql statement
     * @param name the name for the sql statement
     * @param hostKey the host key for the sql statement
     */
    public static void deleteCookie(Long creationUtc,String name,String hostKey){

        String sqlStatement = "DELETE FROM cookies WHERE creation_utc = ? AND name = ? AND host_key = ?";

        try(Connection connection = DataBaseConnector.connect("jdbc:sqlite:C:\\Users\\agrok\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Cookies");
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)){
            preparedStatement.setLong(1,creationUtc);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,hostKey);

            preparedStatement.execute();

        }catch (SQLException a){
            //TODO make into logs
            System.err.println(a.getErrorCode());
        }
    }
}