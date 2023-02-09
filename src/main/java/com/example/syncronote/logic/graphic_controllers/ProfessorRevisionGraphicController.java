package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.Main;
import com.example.syncronote.logic.app_controllers.ProfessorRevisionComponentController;
import com.example.syncronote.logic.app_controllers.ProfessorRevisionController;
import com.example.syncronote.logic.app_controllers.StudentRevisionComponentController;
import com.example.syncronote.logic.beans.ProfessorRevisionBean;
import com.example.syncronote.logic.beans.StudentRevisionBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessorRevisionGraphicController extends AbsLoggedGraphicController{

    @FXML
    private VBox revisionLayout;

    private ProfessorRevisionController controller;

    @Override
    public void initialize(){
        super.initialize();
        controller = new ProfessorRevisionController();
        setUpRevisionLayout();
    }

    private void setUpRevisionLayout() {
        List<ProfessorRevisionBean> currentRevisions;
        try {
            currentRevisions = controller.getInRevisionNotes();
            ProfessorRevisionComponentController componentController = new ProfessorRevisionComponentController();
            for (ProfessorRevisionBean currentRevision : currentRevisions) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Objects.requireNonNull(Main.class.getResource(PROFESSOR_REVISION_COMPONENT)));

                HBox professorRevisionGraphicComponent = fxmlLoader.load();
                ProfessorRevisionComponentGraphicController componentGraphicController = fxmlLoader.getController();
                componentGraphicController.setRevisionValues(currentRevision, componentController);

                revisionLayout.getChildren().add(professorRevisionGraphicComponent);
            }
        }
        catch (IOException e){
            showErrorAlert("Rendering error", "Couldn't render notes");
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }
}
