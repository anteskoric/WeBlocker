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

import database.classes.Term;
import exceptions.SelectedColumnIsEmptyException;
import interfaces.ControllerAlerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import logic.HistoryDataExtractor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * TermsHistoryController is a class for controlling the fxml TermsHistory.fxml
 *
 * @author Ante Skoric
 */
public class TermsHistoryController implements Initializable, ControllerAlerts {

    /**
     * The terms list view
     */
    @FXML
    private ListView<Term> termsListView;

    /**
     * The button that deletes all rows from the list view
     */
    @FXML
    private Button deleteAllButton;

    /**
     * The search terms button
     */
    @FXML
    private Button searchedTermsButton;

    /**
     * The search history button
     */
    @FXML
    private Button searchHistoryButton;

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
        addTerms();
    }

    /**
     * Adds terms into the terms list view
     */
    private void addTerms() {
        ObservableList<Term> terms = FXCollections.observableArrayList(HistoryDataExtractor.selectSearchTerms());
        this.termsListView.setItems(terms);
    }

    /**
     * If button search terms is clicked the elements in the list view will be updated
     */
    @FXML
    protected void onActionSearchedTerms() {
        addTerms();
    }

    /**
     * Change the scene to web history
     */
    @FXML
    protected void onActionSearchHistory() {
        changeScene("/fxml/WebHistory.fxml");
    }

    /**
     * Changes the scene of the stage
     *
     * @param file path of the new fxml scene
     */
    private void changeScene(String file) {
        try {
            Stage currentStage = (Stage) this.termsListView.getScene().getWindow();
            currentStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(file))));
        } catch (IOException a) {
            //TODO make into logs
            System.err.println(a.getMessage());
        }
    }

    /**
     * Deletes the clicked row and the list view is updated
     *
     * @throws SelectedColumnIsEmptyException if row is empty
     */
    @FXML
    protected void onActionListView() {
        //TODO column is null, when you click creation date or other columns that describe the rows
        if(ControllerAlerts.isColumnNull(termsListView))
            HistoryDataExtractor.deleteSearchedTerms(termsListView.getSelectionModel().getSelectedItem().getKeywordID(), termsListView.getSelectionModel().getSelectedItem().getUrlId(), termsListView.getSelectionModel().getSelectedItem().getTerm());
        addTerms();
    }

    /**
     * Deletes all rows in table keyword_search_terms from the DB history
     * Alert box will be shown, if the user clicks the ok button all rows of the table will be deleted
     * At the end the ListView will be updated
     */
    @FXML
    protected void onActionDeleteAll() {
        Alert deleteConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        deleteConfirmation.setContentText("Are you sure that you want to delete all rows?");
        deleteConfirmation.showAndWait().filter(response -> response == ButtonType.OK)
                .ifPresent(response -> HistoryDataExtractor.deleteAllTerms());
        addTerms();
    }

}
