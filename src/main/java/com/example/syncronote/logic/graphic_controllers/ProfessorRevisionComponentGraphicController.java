package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.enums.app_controllers.ProfessorRevisionComponentController;
import com.example.syncronote.logic.beans.NoteFilePathBean;
import com.example.syncronote.logic.beans.ProfessorRevisionBean;
import com.example.syncronote.logic.beans.ResponseBean;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import com.example.syncronote.logic.utilities.AbsDialogNavigatorController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class ProfessorRevisionComponentGraphicController extends AbsDialogNavigatorController {
    @FXML
    private Label noteLbl;

    @FXML
    private Label studentLbl;

    @FXML
    private Label categoryLbl;

    ProfessorRevisionComponentController controller;
    private String studentQuestion;
    private NoteFilePathBean noteFilePathBean;

    @Override
    public void initialize(){
        super.initialize();
        categoryLbl.setText("<no category>");
    }

    public void setRevisionValues(ProfessorRevisionBean currentRevision, ProfessorRevisionComponentController componentController) {
        noteLbl.setText(currentRevision.getNote());
        studentLbl.setText(currentRevision.getStudent());
        categoryLbl.setText(currentRevision.getCategory());
        studentQuestion = currentRevision.getStudentQuestion();
        noteFilePathBean = new NoteFilePathBean(currentRevision.getFilePath());
        controller = componentController;
    }

    @FXML
    void openNote(ActionEvent event) {
        try {
            controller.openNote(noteFilePathBean);
            showInfoAlert("Student question: ", studentQuestion);
        }
        catch (IOException e) {
            showErrorAlert("Error", "Cannot open file");
        }
    }

    @FXML
    void correctNote(ActionEvent event) {
        try {
            String response = showTextAlert("Response", "Comment note " + noteLbl.getText(), "Enter comment");
            ResponseBean responseBean = new ResponseBean(noteLbl.getText(), studentQuestion, response);
            controller.correctNote(responseBean);
        } catch (InvalidFormatException e) {
            showErrorAlert("Error", e.getMessage());
        }
    }

    @FXML
    void finalizeRevision(ActionEvent event) {
        ResponseBean responseBean = new ResponseBean(noteLbl.getText(), null, null);
        controller.correctNote(responseBean);
    }
}
