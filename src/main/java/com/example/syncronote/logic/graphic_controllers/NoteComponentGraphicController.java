package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.beans.NoteComponentBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class NoteComponentGraphicController {
    @FXML
    private Label titleLbl;
    @FXML
    private Label categoryLbl;
    @FXML
    private Label descriptionLbl;
    @FXML
    private Label visibilityLbl;

    @FXML
    public void initialize(){
    }

    public void setNoteValues(NoteComponentBean noteValues){
        titleLbl.setText(noteValues.getTitle());
        categoryLbl.setText(noteValues.getCategory());
        descriptionLbl.setText(noteValues.getDescription());
        visibilityLbl.setText(noteValues.getVisibility());
    }

    @FXML
    private void downloadNote(ActionEvent actionEvent) {
    }

    @FXML
    private void deleteNote(ActionEvent actionEvent) {
    }
}
