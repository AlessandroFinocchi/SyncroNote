package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.HomeController;
import com.example.syncronote.logic.beans.HomeInfosBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeGraphicController extends AbsLoggedGraphicController {
    @FXML
    private Label typeLbl;
    @FXML
    private Label nameLbl;
    @FXML
    protected Button logoutBtn;

    @Override
    public void initialize() {
        super.initialize();

        HomeController homeController = new HomeController();

        HomeInfosBean homeInfosBean = homeController.getHomepageInfos();

        typeLbl.setText(homeInfosBean.getUserType());

        nameLbl.setText(homeInfosBean.getName());

        logoutBtn.setOnMouseClicked(mouseEvent -> {
            new HomeController().logoutCurrentUser();
            goToPage("Login.fxml");
        });
    }
}
