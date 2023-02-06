package com.example.syncronote.logic.graphic_controllers;

import javafx.fxml.FXML;
import javafx.scene.shape.Polygon;

public class NotesGraphicController extends AbsLoggedGraphicController{
    @FXML
    private Polygon lookNotePlg;
    @FXML
    private Polygon publicationPlg;
    @FXML
    private Polygon revisionPlg;

    @Override
    public void initialize() {
        super.initialize();
        publicationPlg.setOnMouseClicked(mouseEvent -> goToPage("Publication.fxml"));
    }

}