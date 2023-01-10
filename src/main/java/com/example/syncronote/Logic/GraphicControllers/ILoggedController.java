package com.example.syncronote.Logic.GraphicControllers;

import javafx.fxml.FXML;

public class ILoggedController extends IController{
    @FXML
    @Override
    public void initialize(){
        super.initialize();

        Burger.setOnMouseClicked(mouseEvent -> {
            LeftPane.setVisible(!LeftPane.isVisible());
            ContainerPane.setDisable(LeftPane.isVisible());
            ContainerPane.setOpacity(LeftPane.isVisible() ? 0.8 : 1);
        });

        LeftPane.setVisible(false);
    }
}
