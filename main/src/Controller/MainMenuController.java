package Controller;

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
 * MainMenuController is a class for controlling the GUI MainMenu.fxml
 * @author Ante Skoric
 */
public class MainMenuController implements Initializable {

    @FXML
    private Button websiteUsage;

    @FXML
    private Button blockWebsite;

    @FXML
    private Button blockedAndUnblock;

    @FXML
    private Button contact;

    public void onActionWebsiteUsage(){
        changeStage("/GUI/WebsiteUsage.fxml");
    }

    public void onActionBlockWebsite(){
        changeStage("/GUI/BlockWebSite.fxml");
    }

    public void onActionBlockedAndUnblock(){
        changeStage("/GUI/BlockedAndUnblock.fxml");
    }

    public void onActionContact(){
        changeStage("/GUI/Contact.fxml");
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

    private void changeStage(String path){
        try {
            //TODO Delete commented code
            //Stage currentStage = (Stage) this.websiteUsage.getScene().getWindow();
            //currentStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/BlockWebSite.fxml"))));
            Stage blockWebSiteStage = new Stage();
            Pane root = FXMLLoader.load(getClass().getResource(path));
            blockWebSiteStage.initModality(Modality.APPLICATION_MODAL);
            blockWebSiteStage.setScene(new Scene(root));
            blockWebSiteStage.show();
        }catch (IOException a){
            //TODO make into loggs
            System.err.println(a);
        }
    }
}
