package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.NoteChosenBean;
import com.example.syncronote.logic.beans.PublicationStudentBean;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;

public abstract class AbsPublicationController extends AbsLoggedController{

    public NoteChosenBean getNewNote(MouseEvent actionEvent) throws InvalidFormatException {
        File noteFile;
        NoteChosenBean noteChosenBean;
        FileChooser fileChooser = new FileChooser();

        //configuration of the file explorer
        fileChooser.setTitle("Upload new note");
        fileChooser.setInitialDirectory(
                new File("src/main/resources/com/example/syncronote/notes"));

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf")
        );

        //showing the file explorer
        Stage currentStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        noteFile = fileChooser.showOpenDialog(currentStage);

        if(noteFile == null)
            throw new InvalidFormatException("File not selected");

        noteChosenBean = new NoteChosenBean(noteFile);

        return noteChosenBean;
    }

    public abstract void publishNote(PublicationStudentBean publicationStudentBean) throws DAOException, SQLException;
}