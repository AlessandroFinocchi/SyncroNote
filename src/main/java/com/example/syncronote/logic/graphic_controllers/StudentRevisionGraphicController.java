package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.Main;
import com.example.syncronote.logic.app_controllers.StudentRevisionComponentController;
import com.example.syncronote.logic.app_controllers.StudentRevisionController;
import com.example.syncronote.logic.beans.*;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentRevisionGraphicController extends AbsLoggedGraphicController{
    @FXML
    private VBox revisionLayout;
    @FXML
    private ComboBox<String> noteCombo;
    @FXML
    private ComboBox<String> professorCombo;
    @FXML
    private TextArea questionTxt;
    @FXML
    private Button revisionBtn;

    private StudentRevisionController controller;

    @Override
    public void initialize(){
        super.initialize();
        controller = new StudentRevisionController();
        setUpNoteCombo();
        setUpRevisionLayout();
        professorCombo.setVisible(false);
        revisionBtn.setVisible(false);
    }

    private void setUpNoteCombo() {
        List<RevisionableNoteBean> notes = controller.getRevisableNotes();
        ObservableList<String> list = FXCollections.observableArrayList();

        for (RevisionableNoteBean note: notes) {
            list.add(note.getNoteName());
        }

        noteCombo.setItems(list);
    }

    public void onNoteSelected(ActionEvent actionEvent) {
        professorCombo.setVisible(true);
        setUpProfessorCombo(noteCombo.getValue());
    }

    private void setUpProfessorCombo(String selectedNote) {
        NoteChosenBean noteChosenBean = new NoteChosenBean(selectedNote);
        ProfessorUsernamesBean professors = controller.getProfessors(noteChosenBean);
        ObservableList<String> list = FXCollections.observableArrayList();

        list.addAll(professors.getProfessorNames());

        professorCombo.setItems(list);
    }

    public void onProfessorSelected(ActionEvent actionEvent) {
        revisionBtn.setVisible(true);
    }

    private void setUpRevisionLayout() {
        List<StudentRevisionBean> currentRevisions;
        try {
            currentRevisions = controller.getInRevisionNotes();
            StudentRevisionComponentController componentController = new StudentRevisionComponentController();
            for (StudentRevisionBean currentRevision : currentRevisions) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Objects.requireNonNull(Main.class.getResource(STUDENT_REVISION_COMPONENT)));

                HBox studentRevisionGraphicComponent = fxmlLoader.load();
                StudentRevisionComponentGraphicController componentGraphicController = fxmlLoader.getController();
                componentGraphicController.setRevisionValues(currentRevision, componentController);

                revisionLayout.getChildren().add(studentRevisionGraphicComponent);
            }
        }
        catch (IOException e){
            showErrorAlert("Rendering error", "Couldn't render notes");
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }

    }

    @FXML
    private void startRevision(ActionEvent actionEvent) {
        StartNewRevisionBean bean;
        try{
            bean = new StartNewRevisionBean(
                    noteCombo.getValue(),
                    professorCombo.getValue(),
                    questionTxt.getText()
            );
            controller.startNewRevision(bean);
            goToPage(STUDENT_REVISION);
        }
        catch (InvalidFormatException e) {
            showInfoAlert("Invalid format", e.getMessage());
        }
        catch (DAOException e) {
            showErrorAlert("Database error", e.getMessage());
        }
    }

}
