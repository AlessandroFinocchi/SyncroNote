package com.example.syncronote.logic.utilities;

import com.example.syncronote.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NavigatorSingleton {
    private static NavigatorSingleton instance = null;
    protected static Stage stg;

    public static Stage getStg() {
        return stg;
    }

    protected NavigatorSingleton(Stage stg) {
        this.stg = stg;
    }


    public static synchronized NavigatorSingleton getInstance(Stage stg){
        if(NavigatorSingleton.instance == null)
            NavigatorSingleton.instance = new NavigatorSingleton(stg);

        return instance;
    }

    public static synchronized NavigatorSingleton getInstance() {
        return instance;
    }


    public void gotoPage(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        stg.getScene().setRoot(pane);
    }
}
