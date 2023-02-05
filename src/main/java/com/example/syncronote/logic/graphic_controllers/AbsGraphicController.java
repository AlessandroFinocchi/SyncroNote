package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.utilities.NavigatorSingleton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbsGraphicController {
    @FXML
    private Pane titlePane; // Value injected by FXMLLoader
    @FXML
    private ImageView closeBtn; // Value injected by FXMLLoader
    @FXML
    private ImageView minimizeBtn; // Value injected by FXMLLoader

    protected Alert errorAlert;
    protected Alert infoAlert;

    private double x;
    private double y;

    protected Logger logger = Logger.getAnonymousLogger();

    @FXML
    public void initialize(){
        assert closeBtn != null : "fx:id=\"CloseBtn\" was not injected: check your FXML file.";
        assert minimizeBtn != null : "fx:id=\"MinimizeBtn\" was not injected: check your FXML file.";
        assert titlePane != null : "fx:id=\"TitlePane\" was not injected: check your FXML file.";


        titlePane.setOnMouseDragged(mouseEvent -> {
            NavigatorSingleton n = NavigatorSingleton.getInstance();
            n.getStg().setX(mouseEvent.getScreenX() - x);
            n.getStg().setY(mouseEvent.getScreenY() - y);
        });

        titlePane.setOnMousePressed(mouseEvent -> {
            NavigatorSingleton n = NavigatorSingleton.getInstance();
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        closeBtn.setOnMouseClicked(mouseEvent -> {
            NavigatorSingleton n = NavigatorSingleton.getInstance();
            n.getStg().close();
        });
        minimizeBtn.setOnMouseClicked(mouseEvent -> {
            NavigatorSingleton n = NavigatorSingleton.getInstance();
            n.getStg().setIconified(true);
        });
        
        errorAlert = new Alert(Alert.AlertType.ERROR);
        infoAlert = new Alert(Alert.AlertType.INFORMATION);
    }

    protected void goToPage(String page) {
        try{
            NavigatorSingleton.getInstance().gotoPage(page);
        }
        catch (IOException e){
            logger.log(Level.INFO, e.getMessage());
        }
    }

    protected void showErrorAlert(String title, String header){
        errorAlert.setTitle(title);
        errorAlert.setHeaderText(header);
        errorAlert.show();
    }

    protected void showInfoAlert(String title, String header){
        infoAlert.setTitle(title);
        infoAlert.setHeaderText(header);
        infoAlert.show();
    }
}
