module JavaFxApplication {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.base;
    requires java.sql;
    requires json.simple;
    requires gson;
    requires com.fasterxml.jackson.core;
    requires javafx.media;

    opens controller;
    opens logic;
    opens database.classes;
    opens css.design;
    opens images;
    opens fxml;
}