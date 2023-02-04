package com.example.syncronote;

import com.example.syncronote.logic.utilities.NavigatorSingleton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        String firstPage = "Login.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(firstPage));
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.TRANSPARENT);

        NavigatorSingleton n = NavigatorSingleton.getInstance(primaryStage);

        n.getStg().initStyle(StageStyle.TRANSPARENT);
        n.getStg().setTitle("SyncroNotes");
        n.getStg().setResizable(false);
        n.getStg().setScene(scene);
        n.getStg().getIcons().add(new Image(getClass().getResourceAsStream("/ProjectIcon.png")));
        n.getStg().show();
    }

    public static void main(String[] args) {
        launch();
    }
}