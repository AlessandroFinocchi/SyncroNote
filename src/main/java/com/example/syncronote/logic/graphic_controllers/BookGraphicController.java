package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.AbsBookController;
import com.example.syncronote.logic.app_controllers.BookProfessorController;
import com.example.syncronote.logic.app_controllers.BookStudentController;
import com.example.syncronote.logic.beans.NoteChosenBean;
import com.example.syncronote.logic.beans.PublicationStudentBean;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import com.example.syncronote.logic.session.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookGraphicController extends AbsLoggedGraphicController {
    @FXML
    public Label fileLbl;
    @FXML
    public Button publishBtn;
    @FXML
    public CheckBox privacyLbl;

    private NoteChosenBean noteChosenBean;
    private AbsBookController bookController;

    @Override
    public void initialize() {
        super.initialize();
        if(SessionManager.getInstance().getCurrentUser().getUserType().equals(UserTypes.STUDENT))
            bookController = new BookStudentController();
        else if(SessionManager.getInstance().getCurrentUser().getUserType().equals(UserTypes.PROFESSOR))
            bookController = new BookProfessorController();
    }

    public void selectFile(MouseEvent actionEvent) {
        try{
            noteChosenBean = bookController.getNewNote(actionEvent);
            fileLbl.setText(noteChosenBean.getTitle());
        }
        catch (InvalidFormatException e){
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }

    }

    public void publishNote(ActionEvent actionEvent) {
        try{
            if(noteChosenBean == null)
                throw new InvalidFormatException("File not selected");

            PublicationStudentBean publicationStudentBean = new PublicationStudentBean(
                    noteChosenBean.getFile(),
                    privacyLbl.isSelected()
            );

            bookController.publishNote(publicationStudentBean);
            showInfoAlert("Publication", "Your note has been publicized");
            goToPage("Home.fxml");
        }
        catch (InvalidFormatException e){
            showErrorAlert("Alert", e.getMessage());
        }
        catch (DAOException | SQLException e){
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
            showErrorAlert("Publication Error", e.getMessage());
        }
    }
}
