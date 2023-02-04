package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.exceptions.SessionUserException;
import com.example.syncronote.logic.model.User;
import com.example.syncronote.logic.session.SessionManager;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ILoggedGraphicController extends IGraphicController {
    @FXML
    protected AnchorPane containerPane; // Value injected by FXMLLoader
    @FXML
    protected AnchorPane leftPane; // Value injected by FXMLLoader
    @FXML
    protected ImageView burger; // Value injected by FXMLLoader
    @FXML
    protected JFXButton userBtn;
    @FXML
    protected JFXButton bookBtn;
    @FXML
    protected JFXButton searchBtn;
    @FXML
    protected JFXButton courseBtn;
    @FXML
    protected JFXButton exitBtn;
    @FXML
    protected HBox changeHBox;
    @FXML
    protected ComboBox<String> selectUserCombo;

    @Override
    public void initialize(){
        super.initialize();

        burger.setOnMouseClicked(mouseEvent -> {
            leftPane.setVisible(!leftPane.isVisible());
            containerPane.setDisable(leftPane.isVisible());
            containerPane.setOpacity(leftPane.isVisible() ? 0.8 : 1);
        });

        leftPane.setVisible(false);

        changeUserSetUp();

        exitBtn.setOnMouseClicked(mouseEvent -> {
            goToPage("Login.fxml");
        });

        changeHBox.setOnMouseClicked(mouseEvent -> {
            SessionManager sessionManager = SessionManager.getInstance();
            if(sessionManager.getCurrentUser().getUsername().equals(selectUserCombo.getValue())){
                sessionManager.changeCurrentUser(selectUserCombo.getValue());
                goToPage("Home.fxml");
            }
        });
    }

    private void changeUserSetUp(){

        SessionManager sessionManager = SessionManager.getInstance();

        for (User u: sessionManager.getLoggedUsers()) {
            selectUserCombo.getItems().add(u.getUsername());
        }

        selectUserCombo.setValue(sessionManager.getCurrentUser().getUsername());
    }
}
