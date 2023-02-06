package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.UserNotesController;
import com.example.syncronote.logic.beans.NoteComponentBean;
import com.example.syncronote.logic.enums.VisibilityTypes;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.utilities.AbsAlertGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NoteComponentGraphicController extends AbsAlertGenerator {
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
        throw new UnsupportedOperationException();
    }

    @FXML
    private void deleteNote(ActionEvent actionEvent) {
        UserNotesController userNotesController = new UserNotesController();
        NoteComponentBean noteComponentBean = new NoteComponentBean(
                titleLbl.getText(),
                categoryLbl.getText(),
                descriptionLbl.getText(),
                VisibilityTypes.fromString(visibilityLbl.getText())
        );

        try {
            userNotesController.deleteNote(noteComponentBean);
        }
        catch (DAOException e){
            showErrorAlert("Error", e.getMessage());
        }
    }
}
