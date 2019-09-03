package interfaces;

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

import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

/**
 * Methods for the Controller classes
 *
 * @author Ante Skoric
 */

public interface ControllerAlerts {

    //TODO duplicate code

    /**
     * Check if the selected row is empty
     * @param tableView TableView of the class
     * @return true if the column is empty, else false
     */
    static boolean isColumnNull(TableView tableView) {
        if (tableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Column");
            alert.setContentText("The selected column is empty");
            alert.setHeaderText(null);
            alert.showAndWait();
            return true;
        }else {
            return false;
        }
    }

    /**
     * Check if the selected row is empty
     * @param listView ListView of the class
     * @return true if the column is empty, else false
     */
    static boolean isColumnNull(ListView listView) {
        if (listView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empty Column");
            alert.setContentText("The selected column is empty");
            alert.setHeaderText(null);
            alert.showAndWait();
            return true;
        }else {
            return false;
        }
    }

    /**
     * GUI alert popup
     * @param titleText the text of the title
     * @param contentText the text of the content
     */
    static void setAlert(String titleText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleText);
        alert.setContentText(contentText);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
