package com.example.syncronote.logic.utilities;

import com.example.syncronote.logic.exceptions.InvalidFormatException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbsDialogNavigatorController {
    protected Alert errorAlert;
    protected Alert infoAlert;
    protected TextInputDialog textAlert;

    protected static final String COURSE_COMPONENT = "CourseComponent.fxml";
    protected static final String NOTE_COMPONENT = "NoteComponent.fxml";
    protected static final String NOTE_ACTION_CHOICE = "NotesActionChoice.fxml";
    protected static final String PROFESSOR_COURSES = "ProfessorCourses.fxml";
    protected static final String PROFESSOR_REVISION = "ProfessorRevision.fxml";
    protected static final String PROFESSOR_REVISION_COMPONENT = "ProfessorRevisionComponent.fxml";
    protected static final String PUBLICATION = "Publication.fxml";
    protected static final String STUDENT_REVISION = "StudentRevision.fxml";
    protected static final String STUDENT_REVISION_COMPONENT = "StudentRevisionComponent.fxml";
    protected static final String USER_NOTES = "UserNotes.fxml";

    @FXML
    public void initialize(){
        errorAlert = new Alert(Alert.AlertType.ERROR);
        infoAlert = new Alert(Alert.AlertType.INFORMATION);
        textAlert = new TextInputDialog("");
    }


    protected void showErrorAlert(String title, String header){
        errorAlert.setTitle(title);
        errorAlert.setHeaderText(header);
        errorAlert.show();
    }

    protected void showInfoAlert(String title, String header){
        infoAlert.setTitle(title);
        infoAlert.setHeaderText(header);
        infoAlert.show();
    }

    protected String showTextAlert(String title, String header, String content) throws InvalidFormatException {
        textAlert.setTitle(title);
        textAlert.setHeaderText(header);
        textAlert.setContentText(content);

        Optional<String> result = textAlert.showAndWait();

        if(!result.isPresent())
            throw new InvalidFormatException("Please enter a response");

        return result.get();
    }

    protected void goToPage(String page) {
        try{
            NavigatorSingleton.getInstance().gotoPage(page);
        }
        catch (IOException e){
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }
}
