package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller.Controller for the GUI
 * @author Ante Skoric
 */
public class MainMenuController implements Initializable {

    @FXML
    private Button websiteUsageButton;

    @FXML
    private Button blockWebsiteButton;

    @FXML
    private Button blockedAndUnblock;

    @FXML
    private Button kontakt;
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
}
