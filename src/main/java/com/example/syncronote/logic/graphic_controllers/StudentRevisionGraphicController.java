package com.example.syncronote.logic.graphic_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class StudentRevisionGraphicController extends AbsLoggedGraphicController{
    @FXML
    private VBox revisionLayout;
    @FXML
    private ComboBox noteCombo;
    @FXML
    private ComboBox professorCombo;
    @FXML
    private TextArea questionTxt;
    @FXML
    private Button revisionBtn;

    @Override
    public void initialize(){

    }

    @FXML
    private void startRevision(ActionEvent actionEvent) {
    }
}
