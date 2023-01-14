package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class IGraphicController {
    @FXML
    private Pane titlePane; // Value injected by FXMLLoader
    @FXML
    private ImageView closeBtn; // Value injected by FXMLLoader
    @FXML
    private ImageView minimizeBtn; // Value injected by FXMLLoader

    protected Alert errorAlert;

    private double x, y;

    protected Logger logger = Logger.getAnonymousLogger();

    @FXML
    public void initialize(){
        assert closeBtn != null : "fx:id=\"CloseBtn\" was not injected: check your FXML file.";
        assert minimizeBtn != null : "fx:id=\"MinimizeBtn\" was not injected: check your FXML file.";
        assert titlePane != null : "fx:id=\"TitlePane\" was not injected: check your FXML file.";

        titlePane.setOnMouseDragged(mouseEvent -> {
            Main.getStg().setX(mouseEvent.getScreenX() - x);
            Main.getStg().setY(mouseEvent.getScreenY() - y);
        });

        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        closeBtn.setOnMouseClicked(mouseEvent -> Main.getStg().close());
        minimizeBtn.setOnMouseClicked(mouseEvent -> Main.getStg().setIconified(true));
        
        errorAlert = new Alert(Alert.AlertType.ERROR);
    }

    protected void goToPage(String page) {
        try{
            Main m = new Main();
            m.ChangeScene(page);
        }
        catch (IOException e){
            logger.log(Level.INFO, e.getMessage());
        }
    }
}
