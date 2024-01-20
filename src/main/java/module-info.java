module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires java.sql;
    requires java.base;
    requires javafx.graphics;

    opens main to javafx.fxml;
    opens Class;
    opens Controller;
    exports main;
    exports Class;
    exports Controller;
}
