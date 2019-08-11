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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * MainMenuController is a class for controlling the fxml MainMenu.fxml
 *
 * @author Ante Skoric
 */
public class MainMenuController implements Initializable {

    /**
     * The websiteUsage button
     */
    @FXML
    private Button websiteHistory;

    /**
     * The blockWebsite button
     */
    @FXML
    private Button blockWebsite;

    /**
     * The blockedAndUnblock button
     */
    @FXML
    private Button blockedAndUnblock;

    /**
     * The cookies button
     */
    @FXML
    private Button cookies;

    /**
     * The contact button
     */
    @FXML
    private Button contact;

    /**
     * The method will be called when the button WebsiteUsage is clicked
     */
    @FXML
    public void onActionWebsiteHistory() {
        changeStage("/fxml/WebHistory.fxml","Web history");
    }

    /**
     * The method will be called when the button BlockWebsite is clicked
     */
    @FXML
    public void onActionBlockWebsite() {
        changeStage("/fxml/BlockWebSite.fxml","Block Website");
    }

    /**
     * The method will be called when the button BlockedAndUnblock is clicked
     */
    @FXML
    public void onActionBlockedAndUnblock() {
        changeStage("/fxml/BlockedAndUnblock.fxml","Blocked And Unblocked");
    }

    @FXML
    public void onActionCookies(){
        changeStage("/fxml/Cookies.fxml","Cookies");
    }
    /**
     * The method will be called when the button Contact is clicked
     */
    @FXML
    public void onActionContact() {
        changeStage("/fxml/Contact.fxml","Contact");
    }

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

    }

    /**
     * The method changeStage creates new Stage from the given fxml file
     * @param path Path of fxml file
     */
    private void changeStage(String path, String title) {
        try {
            //TODO Delete commented code
            //Stage currentStage = (Stage) this.websiteUsage.getScene().getWindow();
            //currentStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/BlockWebSite.fxml"))));
            Stage blockWebSiteStage = new Stage();
            Pane root = FXMLLoader.load(getClass().getResource(path));
            blockWebSiteStage.initModality(Modality.APPLICATION_MODAL);
            blockWebSiteStage.setScene(new Scene(root));
            blockWebSiteStage.resizableProperty().setValue(Boolean.FALSE);
            blockWebSiteStage.setTitle(title);
            blockWebSiteStage.show();
        } catch (IOException a) {
            //TODO make into loggs
            System.err.println(a);
        }
    }
}