package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.StudentRevisionController;
import com.example.syncronote.logic.beans.RevisionableNoteBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.util.List;

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
        ProfessorUsernamesBean professors = controller.getProfessors(selectedNote);
        ObservableList<String> list = FXCollections.observableArrayList();

        list.addAll(professors.getProfessorNames());

        professorCombo.setItems(list);
    }

    public void onProfessorSelected(ActionEvent actionEvent) {
        revisionBtn.setVisible(true);
    }

    @FXML
    private void startRevision(ActionEvent actionEvent) {
        if(questionTxt.getText().isEmpty())
            showErrorAlert("Complete the procedure", "Ask a question about your note to the professor");

        //CONTINUA DA QUI: INSERISCI SU DB LA REVISIONE, GOTO PAGE QUESTA
        // NELL'INITIALIZE SETUPPA LA SCROLL PANE
    }

}
