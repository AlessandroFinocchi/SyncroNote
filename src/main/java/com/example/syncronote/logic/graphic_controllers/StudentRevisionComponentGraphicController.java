package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.utilities.AbsAlertGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StudentRevisionComponentGraphicController extends AbsAlertGenerator {

    @FXML
    private Label noteNameLbl;

    @FXML
    private Label revisionStateLbl;

    @Override
    public void initialize() {
        super.initialize();
        revisionStateLbl.setText("<no state>");
    }

    @FXML
    private void openRevision(ActionEvent actionEvent) {
        throw new UnsupportedOperationException();
    }

    @FXML
    private void finalizeRevision(ActionEvent actionEvent) {
        throw new UnsupportedOperationException();
    }
}
