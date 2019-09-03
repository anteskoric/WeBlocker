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

import exceptions.URLAlreadyExistingException;
import interfaces.ControllerAlerts;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import logic.IOHosts;
import logic.IOJson;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * BlockWebSiteController is a class for controlling the fxml BlockWebSite.fxml.
 *
 * @author Ante Skoric
 */
public class BlockWebSiteController implements Initializable, ControllerAlerts {

    //TODO test block website manually

    /**
     * CheckBox for immediate website blockage
     */
    @FXML
    private CheckBox immediatelyCheckBox;

    /**
     * TextField for Manually website blockage
     * The inputs is Integer that represents hours
     */
    @FXML
    private TextField manuallyField;

    /**
     * TextField for the website that should be blocked
     */
    @FXML
    private TextField urlField;

    /**
     * Submit button
     */
    @FXML
    private Button submit;

    /**
     * Getter for the context of the urlField
     *
     * @return Context (text) of the urlField
     */
    private String getTextUrlField() {
        return this.urlField.getText();
    }

    /**
     * Getter for the context of the manuallyField
     *
     * @return Context (text) of the manuellyField
     */
    private String getTextManuallyField() {
        return this.manuallyField.getText();
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
        /**
         * Force the manuallyField to be numeric only
         * Source: https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
         */
        this.manuallyField.textProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    manuallyField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    /**
     * The method will be called if the user clicks on the button submit
     */
    @FXML
    protected void onActionSubmit() {
        verifySubmitClick();
        if (this.immediatelyCheckBox.isSelected())
            blockWebSiteImmediately();
        else
            blockWebSiteManually();
    }

    /**
     * Blocks the website immediately
     */
    private void blockWebSiteImmediately() {
        try{
            IOHosts.writeIntoHost(IOHosts.getHostName(getTextUrlField()));
        }catch (URLAlreadyExistingException a){
            ControllerAlerts.setAlert("URL Already Exists","The URL is already blocked");
        }catch (NullPointerException a){
            ControllerAlerts.setAlert("False input", "False URL input");
        }catch (IllegalArgumentException a){
            ControllerAlerts.setAlert("False URL","Please use URL of the website");
        }
    }

    /**
     * Block the website manually
     */
    private void blockWebSiteManually() {
        long hoursInt = 0;
        try {
            hoursInt = Long.parseLong(getTextManuallyField());
        } catch (NumberFormatException e) {
            //TODO into logs
            System.err.println(e.getMessage());
            return;
        }

        try {
            IOHosts.checkUsersUrlInput(getTextUrlField());
        }catch (IllegalArgumentException a){
            ControllerAlerts.setAlert("False URL","Please use the URL of a website");
            return;
        }

        try {
            IOJson.saveManualData(getTextUrlField(), hoursInt);
        }catch (IllegalArgumentException a){
            ControllerAlerts.setAlert("False input","The day contains 24 hours");
        }
    }

    /**
     * Check if button immediately and textfield manually are used
     */
    private void verifySubmitClick() {
        if (!(urlFieldContainsCharacters()))
            ControllerAlerts.setAlert("Text filed does not contain text","The URL text field needs must contain website url.");
        if (this.immediatelyCheckBox.isSelected() && manuallyFieldContainsCharacters())
            ControllerAlerts.setAlert("Elements collision", "You can only block a website manually or immediately.");
        if (this.immediatelyCheckBox.isSelected() && manuallyFieldContainsCharacters() && urlFieldContainsCharacters())
            ControllerAlerts.setAlert("Elements collision","You can only block a website manually or immediately.");
        if (!(this.immediatelyCheckBox.isSelected()) && !manuallyFieldContainsCharacters() && !urlFieldContainsCharacters())
            ControllerAlerts.setAlert("Elements not selected","You need to select manuel or immediate button and type URL of the website you want to block.");
        if (urlFieldContainsCharacters() && !(this.immediatelyCheckBox.isSelected()) && !(manuallyFieldContainsCharacters()))
            ControllerAlerts.setAlert("Elements not selected", "You need to use manuel or immediate button to block the website.");
    }

    /**
     * Check if the urlField contains characters
     *
     * @return boolean true if the urlField contains characters else false
     */
    private boolean urlFieldContainsCharacters() {
        return !(this.urlField.getText() == null || this.urlField.getText().trim().isEmpty());
    }

    /**
     * Check if the manuallyField contains characters
     *
     * @return boolean true if the manuallyField contains characters else false
     */
    private boolean manuallyFieldContainsCharacters() {
        return !(this.manuallyField.getText() == null || this.manuallyField.getText().trim().isEmpty());
    }
}