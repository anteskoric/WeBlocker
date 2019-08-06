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

import Exceptions.ElementsNotSelectedException;
import Exceptions.TextFieldDoesNotContainCharactersException;
import Exceptions.ElementsCollisionException;
import Logic.IOHosts;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * BlockWebSiteController is a class for controlling the GUI BlockWebSite.fxml.
 *
 * @author Ante Skoric
 */
public class BlockWebSiteController implements Initializable {

    @FXML
    private CheckBox immediatelyCheckBox;

    @FXML
    private TextField manuallyField;

    @FXML
    private TextField urlField;

    @FXML
    private Button submit;

    private String getTextUrlField() {
        return this.urlField.getText();
    }

    private String getTextManuallyField(){
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
         * Force the field to be numeric only
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

    public void onActionSubmit(){
     verifySubmitClick();
     if(this.immediatelyCheckBox.isSelected())
         blockWebSiteImmediately();
     else
         blockWebSiteManually();
    }

    private void blockWebSiteImmediately() {
        IOHosts.blockSite(getTextUrlField());
    }

    private void blockWebSiteManually(){

    }

    /**
     * Check if button immediately and textfield manually are used
     * If both are used Exception will be thrown
     */
    private void verifySubmitClick(){
        if(!(urlFieldContainsCharacters()))
            throw new TextFieldDoesNotContainCharactersException("The URL text field needs must contain website url");
        if(this.immediatelyCheckBox.isSelected() && manuallyFieldContainsCharacters())
            throw new ElementsCollisionException("You can only block a website manually or immediately");
        if(this.immediatelyCheckBox.isSelected() && manuallyFieldContainsCharacters() && urlFieldContainsCharacters())
            throw new ElementsCollisionException("You can only block a website manually or immediately");
        if(!(this.immediatelyCheckBox.isSelected()) && !manuallyFieldContainsCharacters() && !urlFieldContainsCharacters())
            throw new ElementsNotSelectedException("You need to select manuel or immediate button and type URL of the website you want to block");
        if(urlFieldContainsCharacters() && !(this.immediatelyCheckBox.isSelected()) && !(manuallyFieldContainsCharacters()))
            throw new ElementsNotSelectedException("You need to use manuel or immediate button to block the website");
    }

    private boolean urlFieldContainsCharacters(){
        return !(this.urlField.getText() == null || this.urlField.getText().trim().isEmpty());
    }
    private boolean manuallyFieldContainsCharacters(){
        return !(this.manuallyField.getText() == null || this.manuallyField.getText().trim().isEmpty());
    }
}
