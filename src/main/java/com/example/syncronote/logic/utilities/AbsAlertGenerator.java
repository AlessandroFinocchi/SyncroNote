package com.example.syncronote.logic.utilities;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public abstract class AbsAlertGenerator {
    protected Alert errorAlert;
    protected Alert infoAlert;

    @FXML
    public void initialize(){
        errorAlert = new Alert(Alert.AlertType.ERROR);
        infoAlert = new Alert(Alert.AlertType.INFORMATION);
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
