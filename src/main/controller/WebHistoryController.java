package controller;

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

import database.classes.Url;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.HistoryDataExtractor;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * WebHistoryController is a class for controlling the fxml WebHistory.fxml
 *
 * @author Ante Skoric
 */

public class WebHistoryController implements Initializable {

    /**
     * The elementsView is ListView with search terms or search history in it
     */
    @FXML
    private TableView<Url> elementsView;

    /**
     * The idColumn represents the column of the elementsView where the id of the object is saved
     */
    @FXML
    private TableColumn<Url,Integer> idColumn;

    /**
     * The titleColumn represents the column of the elementsView where the title of the object is saved
     */
    @FXML
    private TableColumn<Url,String> titleColumn;

    /**
     * The urlColumn represents the column of the elementsView where the url of the object is saved
     */
    @FXML
    private TableColumn<Url,String> urlColumn;

    /**
     * The visitCountColumn represents the column of the elementsView where the visitCounter of the object is saved
     */
    @FXML
    private TableColumn<Url, LocalDateTime> visitCountColumn;

    /**
     * The lastVisitColumn represents the column of the elementsView where the lastVisitTime of the object is saved
     */
    @FXML
    private TableColumn<Url,LocalDateTime> lastVisitColumn;


    /**
     * Is the label that describes the elementsView
     */
    @FXML
    private Label titleLabel;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleLabel.setText("Search History");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        visitCountColumn.setCellValueFactory(new PropertyValueFactory<>("visitCount"));
        lastVisitColumn.setCellValueFactory(new PropertyValueFactory<>("lastVisitTime"));

        elementsView.setItems(FXCollections.observableArrayList(HistoryDataExtractor.selectSearchHistory()));
    }

}
