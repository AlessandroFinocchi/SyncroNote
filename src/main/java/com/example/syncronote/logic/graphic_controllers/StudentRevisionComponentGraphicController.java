package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.enums.app_controllers.StudentRevisionComponentController;
import com.example.syncronote.logic.beans.CorrectionNoteBean;
import com.example.syncronote.logic.beans.NoteChosenBean;
import com.example.syncronote.logic.beans.StudentRevisionBean;
import com.example.syncronote.logic.enums.RevisionState;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import com.example.syncronote.logic.utilities.AbsDialogNavigatorController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class StudentRevisionComponentGraphicController extends AbsDialogNavigatorController {

    @FXML
    private Label noteNameLbl;
    @FXML
    private Label revisionStateLbl;
    @FXML
    private Button lookResponseBtn;
    @FXML
    private Button updateNoteBtn;
    @FXML
    private Button checkBtn;
    @FXML
    private Button deleteBtn;

    private StudentRevisionComponentController controller;
    private String noteName;
    private String professorResponse = null;

    @Override
    public void initialize() {
        super.initialize();
        lookResponseBtn.setVisible(false);
        updateNoteBtn.setVisible(false);
        checkBtn.setVisible(false);
        deleteBtn.setVisible(false);
    }

    @FXML
    private void openRevision(ActionEvent actionEvent) {
        showInfoAlert("Professor response", professorResponse);
    }

    @FXML
    private void updateNote(ActionEvent actionEvent) {
        try {
            File noteFile;
            FileChooser fileChooser = new FileChooser();

            //configuration of the file explorer
            fileChooser.setTitle("Upload updated note");
            fileChooser.setInitialDirectory(
                    new File("src/main/resources/com/example/syncronote/notes"));

            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All images", noteName +".*")
            );

            //showing the file explorer
            Stage currentStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            noteFile = fileChooser.showOpenDialog(currentStage);

            if(noteFile == null)
                throw new InvalidFormatException("File not selected");

            String response = showTextAlert("Question", "Ask a question about " + noteName, "Enter question");

            CorrectionNoteBean noteBean = new CorrectionNoteBean(response, noteFile);

            controller.republishNote(noteBean);
        } catch (InvalidFormatException e) {
            showErrorAlert("Error", e.getMessage());
        } catch (DAOException e) {
            showErrorAlert("Database error", e.getMessage());
        }
    }

    @FXML
    private void finalizeRevision(ActionEvent actionEvent) {

        NoteChosenBean noteBean = new NoteChosenBean(noteName);
        try {
            controller.finalizeRevision(noteBean);
        } catch (DAOException e) {
            showErrorAlert("Database error", e.getMessage());
        }
    }

    public void setRevisionValues(StudentRevisionBean currentRevision, StudentRevisionComponentController controller) {
        noteNameLbl.setText(currentRevision.getNote());
        this.controller = controller;
        noteName = currentRevision.getNote();
        professorResponse = currentRevision.getResponse();

        if(currentRevision.getState() == RevisionState.IN_REVISION) {
            revisionStateLbl.setText(RevisionState.IN_REVISION.getId());
            deleteBtn.setVisible(true);
        }
        else if(currentRevision.getState() == RevisionState.REVISED) {
            revisionStateLbl.setText(RevisionState.REVISED.getId());
            lookResponseBtn.setVisible(true);
            updateNoteBtn.setVisible(true);
            professorResponse = currentRevision.getResponse();
        }
        else if(currentRevision.getState() == RevisionState.COMPLETED) {
            revisionStateLbl.setText(RevisionState.COMPLETED.getId());
            checkBtn.setVisible(true);
        }


    }
}
