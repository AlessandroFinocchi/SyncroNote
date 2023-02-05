package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.BookController;
import com.example.syncronote.logic.beans.NoteChosenBean;
import com.example.syncronote.logic.beans.PublicizationBean;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
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
    private BookController bookController;

    @Override
    public void initialize() {
        super.initialize();
        bookController = new BookController();
        //UserTypes mode = new BookController().getSessionInfos().getType();

        /*
        * TODO: change behavior and gui if mode is professor or student:
        *  1 Decorate the Book controller here
        *  2 Set a state for the Book controller passing a UserType as parameter of the constructor
        * */

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

            PublicizationBean publicizationBean = new PublicizationBean(
                    noteChosenBean.getFile(),
                    privacyLbl.isSelected()
            );

            bookController.publishNote(publicizationBean);
        }
        catch (InvalidFormatException e){
            showAlert("Alert", e.getMessage());
        }
        catch (DAOException | SQLException e){
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }
}
