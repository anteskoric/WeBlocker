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

import database.classes.WebSiteVisit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.HistoryDataExtractor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static logic.WebsiteBlocker.checkWebsiteBlockage;

/**
 * MainMenuController is a class for controlling the fxml MainMenu.fxml
 *
 * @author Ante Skoric
 */
public class MainMenuController implements Initializable {

    /**
     * Shows top ten used web sites
     */
    @FXML
    private PieChart topTenSitesChart;

    /**
     * Shows the website usage in percentage
     */
    @FXML
    private Label usageLabel;

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
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTopTenVisitedSites();
        getUsageDisplay();
        checkWebsiteBlockage();
    }

    /**
     * Displays the web site usage in percentage after mouse is moved on the pie chart node
     */
    private void getUsageDisplay() {
        for (final PieChart.Data data : topTenSitesChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED,
                    e -> usageLabel.setText("Usage: " + data.getPieValue() + "%"));
        }
    }

    /**
     * Set the values of the pie chart
     */

    private void setTopTenVisitedSites() {
        ObservableList<PieChart.Data> pieData = FXCollections.observableList(new ArrayList<>());
        List<WebSiteVisit> websites = HistoryDataExtractor.selectTopTenWebsites();
        long totalTime = getTotalVisitTime(websites);
        for (WebSiteVisit website : websites) {
            pieData.add(new PieChart.Data(website.getTitle(), (website.getVisitDuration() * 100) / totalTime));
            //DateManager.setHoursMinutes(resultSet
        }
        topTenSitesChart.setData(pieData);
    }

    /**
     * Get total visit time of the top ten visited websites
     *
     * @param websites The list of the top ten visited websites
     * @return total visited time
     */
    private long getTotalVisitTime(List<WebSiteVisit> websites) {
        long totalVisitTime = 0;
        for (WebSiteVisit website : websites) {
            totalVisitTime = Math.addExact(totalVisitTime, website.getVisitDuration());
        }
        return totalVisitTime;
    }

    /**
     * The method will be called when the button WebsiteUsage is clicked
     */
    @FXML
    protected void onActionWebsiteHistory() {
        changeStage("/fxml/WebHistory.fxml", "Web history");
    }

    /**
     * The method will be called when the button BlockWebsite is clicked
     */
    @FXML
    protected void onActionBlockWebsite() {
        changeStage("/fxml/BlockWebSite.fxml", "Block Website");
    }

    /**
     * The method will be called when the button BlockedAndUnblock is clicked
     */
    @FXML
    protected void onActionBlockedAndUnblock() {
        changeStage("/fxml/BlockedAndUnblock.fxml", "Blocked And Unblocked");
    }

    /**
     * The method will be called when the button Cookies is clicked
     */
    @FXML
    protected void onActionCookies() {
        changeStage("/fxml/Cookies.fxml", "Cookies");
    }

    /**
     * The method will be called when the button Contact is clicked
     */
    @FXML
    protected void onActionContact() {
        changeStage("/fxml/Contact.fxml", "Contact");
    }

    /**
     * The method changeStage creates new Stage from the given fxml file
     *
     * @param path Path of fxml file
     */
    private void changeStage(String path, String title) {
        try {
            Stage blockWebSiteStage = new Stage();
            Pane root = FXMLLoader.load(getClass().getResource(path));
            blockWebSiteStage.initModality(Modality.APPLICATION_MODAL);
            blockWebSiteStage.setScene(new Scene(root));
            blockWebSiteStage.resizableProperty().setValue(Boolean.FALSE);
            blockWebSiteStage.setTitle(title);
            blockWebSiteStage.show();
        } catch (IOException a) {
            //TODO make into loggs
            System.err.println(a.getMessage());
        }
    }
}