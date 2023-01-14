package com.example.syncronote.logic.graphic_controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ILoggedController extends IGraphicController {
    @FXML
    protected AnchorPane containerPane; // Value injected by FXMLLoader
    @FXML
    protected AnchorPane leftPane; // Value injected by FXMLLoader
    @FXML
    protected ImageView burger; // Value injected by FXMLLoader
    @Override
    public void initialize(){
        super.initialize();

        burger.setOnMouseClicked(mouseEvent -> {
            leftPane.setVisible(!leftPane.isVisible());
            containerPane.setDisable(leftPane.isVisible());
            containerPane.setOpacity(leftPane.isVisible() ? 0.8 : 1);
        });

        leftPane.setVisible(false);
    }
}
