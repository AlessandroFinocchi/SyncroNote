package com.example.syncronote;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    private static Stage stg;

    private Logger logger = Logger.getAnonymousLogger();

    public static Stage getStg() {
        return stg;
    }

    public static void setStg(Stage stage) {
        stg = stage;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stg = primaryStage;
        String firstPage = "Login.fxml";
        logger.log(Level.INFO, getClass().getResource(firstPage).toString());
        Parent root = FXMLLoader.load(getClass().getResource(firstPage));
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("SyncroNotes");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/ProjectIcon.png")));
        primaryStage.show();
    }

    public void ChangeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}