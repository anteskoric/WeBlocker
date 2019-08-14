module JavaFxApplication {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.base;
    requires java.sql;

    opens controller;
    opens logic;
    opens database.classes;
}