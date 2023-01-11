module com.example.syncronote {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.syncronote to javafx.fxml;
    exports com.example.syncronote;
    exports com.example.syncronote.logic.graphic_controllers;
    opens com.example.syncronote.logic.graphic_controllers to javafx.fxml;
}