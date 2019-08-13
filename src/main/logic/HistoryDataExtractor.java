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

import database.classes.Term;
import interfaces.DataBaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * SQLite scripts imbedded into Java for finding and updating data in the Google Chrome history database
 * @author Ante Skoric
 */

public final class HistoryDataExtractor implements DataBaseConnector {

    private HistoryDataExtractor(){}

    //TODO delete this
    public static void main(String[] args) {
       System.out.println(HistoryDataExtractor.selectSearchTerms());
    }
    /**
     * Get title, url, visit count and last visited time from the database history from the table urls
     */
    public static void selectSearchHistory(){
        String sqlStatement = "SELECT title, url, visit_count, last_visit_time FROM urls ORDER BY last_visit_time DESC;";

        try (Connection connect = DataBaseConnector.connect("jdbc:sqlite:C:\\Users\\agrok\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\History");
             ResultSet resultSet = connect.createStatement().executeQuery(sqlStatement)){
            while (resultSet.next()){
                System.out.println(resultSet.getArray("add_something"));
            }
        }catch (SQLException a){
            //TODO make into logs
            System.err.println(a.getErrorCode());
        }
    }

    /**
     * Get term from the database history from the table keyword_search_terms
     */
    public static List<Term> selectSearchTerms(){
        String sqlStatement = "SELECT term FROM keyword_search_terms;";
        List<Term> terms = new ArrayList<>();

        try(Connection connect = DataBaseConnector.connect("jdbc:sqlite:C:\\Users\\agrok\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\History");
            ResultSet resultSet = connect.createStatement().executeQuery(sqlStatement)){
            while (resultSet.next()){
                terms.add(new Term(resultSet.getString("term")));
            }
        }catch (SQLException a){
            //TODO make into logs
            System.err.println(a.getErrorCode());
        }
        return terms;
    }

    /**
     * Delete search history from the database history table urls
     * @param id Is the integer that specifies url in the database
     * @param title Is string that represents title of the url
     */
    public static void deleteSearchHistory(int id, String title){
        String sqlStatement = "DELETE FROM urls WHERE id = ? AND title = ?";

        try(Connection connection = DataBaseConnector.connect("jdbc:sqlite:C:\\Users\\agrok\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\History");
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,title);
            preparedStatement.execute();
        }catch (SQLException a){
            //TODO make into logs
            System.err.println(a.getErrorCode());
        }
    }

    /**
     * Delete searched terms from the database history table keyword_search_terms
     * @param keyWordId is integer that represents id for the keyword
     * @param urlId is integer that represents id for the url
     * @param term is string that represents the term
     */
    public static void deleteSearchedTerms(int keyWordId, int urlId, String term){
        String sqlStatement = "DELETE FROM keyword_search_terms WHERE keyword_id = ? AND url_id = ? AND term = ?";
        try(Connection connection = DataBaseConnector.connect("jdbc:sqlite:C:\\Users\\agrok\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\History");
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setInt(1,keyWordId);
            preparedStatement.setInt(2,urlId);
            preparedStatement.setString(3,term);
            preparedStatement.execute();
        }catch (SQLException a){
            //TODO make into logs
            System.err.println(a.getErrorCode());
        }
    }
}