package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.AbsController;
import com.example.syncronote.logic.enums.UserTypes;
import javafx.fxml.FXML;
import javafx.scene.shape.Polygon;

public class NotesActionChoiceGraphicController extends AbsLoggedGraphicController{
    @FXML
    private Polygon lookNotePlg;
    @FXML
    private Polygon publicationPlg;
    @FXML
    private Polygon revisionPlg;

    @Override
    public void initialize() {
        super.initialize();
        lookNotePlg.setOnMouseClicked(mouseEvent -> goToPage(USER_NOTES));
        publicationPlg.setOnMouseClicked(mouseEvent -> goToPage(PUBLICATION));
        revisionPlg.setOnMouseClicked(mouseEvent -> {
            UserTypes userTypes = AbsController.getCurrentUserType();
            if(userTypes == UserTypes.STUDENT)
                goToPage(STUDENT_REVISION);
            else if(userTypes == UserTypes.PROFESSOR)
                goToPage(PROFESSOR_REVISION);
        });
    }

}
