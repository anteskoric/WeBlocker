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
import exceptions.SelectedColumnIsEmptyException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.HistoryDataExtractor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * WebHistoryController is a class for controlling the fxml WebHistory.fxml
 *
 * @author Ante Skoric
 */

public class WebHistoryController implements Initializable {

    //TODO make all javafx methods protected?
    /**
     * Search history button
     */
    @FXML
    private Button searchHistoryButton;

    /**
     * Searched terms button
     */

    @FXML
    private Button searchedTermsButton;


    /**
     * Deletes all rows in the TableView
     */
    @FXML
    private Button deleteAllButton;

    /**
     * The elementsView is TableView with search terms or search history in it
     */
    @FXML
    private TableView<Url> elementsView;

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
    private TableColumn<Url, String> visitCountColumn;

    /**
     * The lastVisitColumn represents the column of the elementsView where the lastVisitTime of the object is saved
     */
    @FXML
    private TableColumn<Url, String> lastVisitColumn;


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
        initializeSearchHistory();
        addElementsIntoTableView();
    }

    /**
     * Initialize the TableView, after initializing the elements must be add into the TableView
     */
    private void initializeSearchHistory() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        visitCountColumn.setCellValueFactory(new PropertyValueFactory<>("visitCount"));
        lastVisitColumn.setCellValueFactory(new PropertyValueFactory<>("lastVisitTime"));
    }

    /**
     * Adds elements into TableView
     */
    private void addElementsIntoTableView() {
        elementsView.setItems(FXCollections.observableArrayList(HistoryDataExtractor.selectSearchHistory()));
    }

    @FXML
    /**
     * Loads new FXM file TermsHistory.fxml
     */
    private void onActionSearchedTerms(){
        try{
            Stage currentStage = (Stage) this.elementsView.getScene().getWindow();
            currentStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/TermsHistory.fxml"))));
        }catch (IOException a){
            //TODO into logs
            System.err.println(a);
        }
    }

    /**
     * Deletes cookies from the db and updates the TableView
     * @throws SelectedColumnIsEmptyException if selected row is empty throws the exception
     */
    @FXML
    public void onActionTableView(){
        isColumnNull();
        HistoryDataExtractor.deleteSearchHistory(elementsView.getSelectionModel().getSelectedItem().getId(),
                elementsView.getSelectionModel().getSelectedItem().getUrl(),
                elementsView.getSelectionModel().getSelectedItem().getTitle());

        addElementsIntoTableView();
    }

    /**
     * Initializes the table view to search history and adds elements into it
     */
    @FXML
    public void onActionSearchHistory(){
        initializeSearchHistory();
        addElementsIntoTableView();
    }

    /**
     * Deletes all rows from the table urls from the DB history
     * Alert box will be shown, if the user clicks the ok button all rows of the table will be deleted
     * At the end the TableView will be updated
     */
    @FXML
    public void onActionDeleteAll(){
        Alert deleteConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        deleteConfirmation.setContentText("Are you sure that you want to delete all rows?");
        deleteConfirmation.showAndWait().filter(response -> response == ButtonType.OK)
                .ifPresent(response -> HistoryDataExtractor.deleteAllHistory());
        addElementsIntoTableView();
    }

    /**
     * Check if the selected row is empty
     * @throws SelectedColumnIsEmptyException if empty
     */
    private void isColumnNull() {
        if(elementsView.getSelectionModel().isEmpty())
            throw new SelectedColumnIsEmptyException("The chosen row is empty");
    }
}