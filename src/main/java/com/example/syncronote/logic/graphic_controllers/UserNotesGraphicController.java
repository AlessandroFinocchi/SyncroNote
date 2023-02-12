package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.Main;
import com.example.syncronote.logic.app_controllers.UserNotesController;
import com.example.syncronote.logic.beans.NoteComponentBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserNotesGraphicController extends AbsLoggedGraphicController {
    @FXML
    private VBox notesLayout;

    private List<NoteComponentBean> notesList;
    private UserNotesController userNotesController;

    @Override
    public void initialize() {
        super.initialize();

        userNotesController = new UserNotesController();

        setNotesLayout();
    }

    private void setNotesLayout(){
        notesList = userNotesController.getUserNotes();
        try {
            for (NoteComponentBean noteComponentBean : notesList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Objects.requireNonNull(Main.class.getResource(NOTE_COMPONENT)));

                HBox noteGraphicComponent = fxmlLoader.load();
                NoteComponentGraphicController noteComponentGraphicController = fxmlLoader.getController();
                noteComponentGraphicController.setNoteValues(noteComponentBean);

                notesLayout.getChildren().add(noteGraphicComponent);
            }
        }
        catch (IOException e){
            showErrorAlert("Rendering error", "Couldn't render notes");
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }
}
