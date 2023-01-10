package com.example.syncronote.Logic.GraphicControllers;

import com.example.syncronote.Main;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public abstract class IController{

    @FXML
    protected AnchorPane ContainerPane; // Value injected by FXMLLoader
    @FXML
    protected AnchorPane LeftPane; // Value injected by FXMLLoader
    @FXML
    protected Pane TitlePane; // Value injected by FXMLLoader
    @FXML
    protected ImageView Burger; // Value injected by FXMLLoader
    @FXML
    protected ImageView CloseBtn; // Value injected by FXMLLoader
    @FXML
    protected ImageView MinimizeBtn; // Value injected by FXMLLoader

    protected double x, y;

    @FXML
    public void initialize(){
        assert CloseBtn != null : "fx:id=\"CloseBtn\" was not injected: check your FXML file.";
        assert MinimizeBtn != null : "fx:id=\"MinimizeBtn\" was not injected: check your FXML file.";
        assert TitlePane != null : "fx:id=\"TitlePane\" was not injected: check your FXML file.";

        TitlePane.setOnMouseDragged(mouseEvent -> {
            Main.stg.setX(mouseEvent.getScreenX() - x);
            Main.stg.setY(mouseEvent.getScreenY() - y);
        });

        TitlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        CloseBtn.setOnMouseClicked(mouseEvent -> Main.stg.close());
        MinimizeBtn.setOnMouseClicked(mouseEvent -> Main.stg.setIconified(true));
    }
}
