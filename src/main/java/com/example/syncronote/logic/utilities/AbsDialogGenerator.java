package com.example.syncronote.logic.utilities;

import com.example.syncronote.logic.exceptions.InvalidFormatException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public abstract class AbsDialogGenerator {
    protected Alert errorAlert;
    protected Alert infoAlert;
    protected TextInputDialog textAlert;

    @FXML
    public void initialize(){
        errorAlert = new Alert(Alert.AlertType.ERROR);
        infoAlert = new Alert(Alert.AlertType.INFORMATION);
        textAlert = new TextInputDialog("");
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



    protected String showTextAlert(String title, String header, String content) throws InvalidFormatException {
        textAlert.setTitle(title);
        textAlert.setHeaderText(header);
        textAlert.setContentText(content);

        Optional<String> result = textAlert.showAndWait();

        if(!result.isPresent())
            throw new InvalidFormatException("Please enter a response");

        return result.get();
    }
}
