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

import database.classes.Cookie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.CookiesDataExtractor;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * CookiesController is a class for controlling the fxml Cookies.fxml
 * @author Ante Skoric
 */
public class CookiesController implements Initializable {

    /**
     * The cookiesTable is the TableView that displays all the cookies
     */
    @FXML
    private TableView<Cookie> cookiesTable;

    /**
     * nameColumn is TableColumn of the TableView cookiesTable it displays the name of the object
     */
    @FXML
    private TableColumn<Cookie, String> nameColumn;

    /**
     * hostKeyColumn is TableColumn of the TableView cookiesTable it displays the hostKey of the object
     */
    @FXML
    private TableColumn<Cookie, String> hostKeyColumn;

    /**
     * hasExpiredColumn is TableColumn of the TableView cookiesTable it displays if the cookies has expired
     * If cookie is expired it will display 1 else 0
     */
    @FXML
    private TableColumn<Cookie,Integer> hasExpiredColumn;

    /**
     * creationDateColumn is TableColumn of the TableView cookiesTable it displays the creationTime of the object
     */
    @FXML
    private TableColumn<Cookie, LocalDateTime> creationDateColumn;

    /**
     * expiresDateColumn is TableColumn of the TableView cookiesTable it displays the expiresTime of the object
     */
    @FXML
    private TableColumn<Cookie,LocalDateTime> expiresDateColumn;

    /**
     * lastAccessColumn is TableColumn of the TableView cookiesTable it displays the lastAccessTime of the object
     */
    @FXML
    private TableColumn<Cookie, LocalDateTime> lastAccessColumn;

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
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        hostKeyColumn.setCellValueFactory(new PropertyValueFactory<>("hostKey"));
        hasExpiredColumn.setCellValueFactory(new PropertyValueFactory<>("hasExpiredColumn"));
        creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationTime"));
        expiresDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiresTime"));
        lastAccessColumn.setCellValueFactory(new PropertyValueFactory<>("lastAccessTime"));

        ObservableList<Cookie> cookiesList = FXCollections.observableArrayList(CookiesDataExtractor.selectCookies());
        cookiesTable.setItems(cookiesList);
    }
}
