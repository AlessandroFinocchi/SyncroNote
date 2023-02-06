package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.AbsController;
import com.example.syncronote.logic.app_controllers.HomeController;
import com.example.syncronote.logic.beans.SessionUserBean;
import com.example.syncronote.logic.enums.UserTypes;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public abstract class AbsLoggedGraphicController extends AbsGraphicController {
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
    protected JFXButton loginBtn;
    @FXML
    protected HBox changeHBox;
    @FXML
    protected ComboBox<String> selectUserCombo;

    protected UserTypes userType;

    @Override
    public void initialize(){
        super.initialize();

        userType = AbsController.getCurrentUserType();

        burger.setOnMouseClicked(mouseEvent -> {
            leftPane.setVisible(!leftPane.isVisible());
            containerPane.setDisable(leftPane.isVisible());
            containerPane.setOpacity(leftPane.isVisible() ? 0.8 : 1);
        });

        leftPane.setVisible(false);

        changeUserSetUp();

        userBtn.setOnMouseClicked(mouseEvent ->
                goToPage("Home.fxml"));

        bookBtn.setOnMouseClicked(
                mouseEvent -> goToPage("NotesActionChoice.fxml"));

        loginBtn.setOnMouseClicked(mouseEvent ->
            goToPage("Login.fxml"));

        changeHBox.setOnMouseClicked(mouseEvent -> {
            if(new HomeController().changeCurrentUser(selectUserCombo.getValue()).equals(selectUserCombo.getValue())){
                goToPage("Home.fxml");
            }
        });
    }

    private void changeUserSetUp(){
        HomeController homeController = new HomeController();
        SessionUserBean sessionUserBean = homeController.getSessionInfos();

        for (String username: sessionUserBean.getLoggedUsers()) {
            selectUserCombo.getItems().add(username);
        }

        selectUserCombo.setValue(sessionUserBean.getUsername());
    }
}
