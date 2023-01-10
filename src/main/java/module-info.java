module com.example.syncronote {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.syncronote to javafx.fxml;
    exports com.example.syncronote;
    exports com.example.syncronote.Logic.GraphicControllers;
    opens com.example.syncronote.Logic.GraphicControllers to javafx.fxml;
}