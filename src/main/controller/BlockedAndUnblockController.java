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

import interfaces.ControllerAlerts;
import logic.IOHosts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * BlockAndUnblockController is a class for controlling the fxml BlockedAndUnblock.fxml.
 *
 * @author Ante Skoric
 */

public class BlockedAndUnblockController implements Initializable, ControllerAlerts{

    /**
     * The blockedWebsites, is a ListView that shows all blocked websites
     */
    @FXML
    private ListView<String> blockedWebsites;

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
        addElementsIntoListView();
    }

    /**
     * The onActionBlockedWebsites will be called when user clicks item in the ListView blockedWebsites,
     * The method calls IOHosts.unblockSite and unblocks the website that the user clicked on
     */
    @FXML
    protected void onActionBlockedWebsites() {
        String tempFilePath = "C:\\Users\\agrok\\Desktop\\WeBlocker\\src\\main\\files\\tempFile.txt";
        if(!ControllerAlerts.isColumnNull(blockedWebsites)) {
            try {
                IOHosts.unblockSite(blockedWebsites.getSelectionModel().getSelectedItem(), tempFilePath);
            } catch (IllegalArgumentException a) {
                ControllerAlerts.setAlert("Website not blocked", "The website is not blocked");
            }
        }
        addElementsIntoListView();
    }

    /**
     * The addElementsInto ListView adds all blocked sites from the file hosts into the ListView
     */
    private void addElementsIntoListView() {
        ObservableList<String> blockedWebsitesList = FXCollections.observableArrayList(IOHosts.getBlockedSites());
        this.blockedWebsites.setItems(blockedWebsitesList);
    }
}