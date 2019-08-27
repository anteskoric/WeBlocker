module JavaFxApplication {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.base;
    requires java.sql;
    requires json.simple;
    requires gson;
    requires com.fasterxml.jackson.core;

    opens controller;
    opens logic;
    opens database.classes;
}