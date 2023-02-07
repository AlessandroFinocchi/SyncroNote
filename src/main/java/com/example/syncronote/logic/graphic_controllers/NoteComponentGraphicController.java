package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.UserNotesController;
import com.example.syncronote.logic.beans.NoteComponentBean;
import com.example.syncronote.logic.enums.VisibilityTypes;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.utilities.AbsAlertGenerator;
import com.example.syncronote.logic.utilities.NavigatorSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

public class NoteComponentGraphicController extends AbsAlertGenerator {
    @FXML
    private Label titleLbl;
    @FXML
    private Label categoryLbl;
    @FXML
    private Label descriptionLbl;
    @FXML
    private Label visibilityLbl;

    private String filePath;

    @Override
    public void initialize(){
        super.initialize();
        descriptionLbl.setText("<no description>");
    }

    public void setNoteValues(NoteComponentBean noteValues){
        titleLbl.setText(noteValues.getTitle());
        categoryLbl.setText(noteValues.getCategory());
        descriptionLbl.setText(noteValues.getDescription());
        visibilityLbl.setText(noteValues.getVisibility());
        filePath = noteValues.getFilePath();
    }

    @FXML
    private void openNote(ActionEvent actionEvent) {
        UserNotesController userNotesController = new UserNotesController();
        try {
            userNotesController.openNote(filePath);
        } catch (IOException e) {
            showErrorAlert("Error", "Cannot open file");
        }
    }

    @FXML
    private void deleteNote(ActionEvent actionEvent) {
        UserNotesController userNotesController = new UserNotesController();
        NoteComponentBean noteComponentBean = new NoteComponentBean(
                titleLbl.getText(),
                categoryLbl.getText(),
                descriptionLbl.getText(),
                VisibilityTypes.fromString(visibilityLbl.getText()),
                filePath
        );

        try {
            userNotesController.deleteNote(noteComponentBean);
            NavigatorSingleton.getInstance().gotoPage("UserNotes.fxml");
        }
        catch (DAOException e){
            showErrorAlert("DB Error", e.getMessage());
        }
        catch (IOException e){
            showInfoAlert("Navigation Error", e.getMessage());
        }


    }
}
