module JavaFxApplication {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.base;

    opens Controller;
    opens Logic;
}
