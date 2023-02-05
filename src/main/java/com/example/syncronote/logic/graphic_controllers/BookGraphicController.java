package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.AbsBookController;
import com.example.syncronote.logic.app_controllers.BookProfessorController;
import com.example.syncronote.logic.app_controllers.BookStudentController;
import com.example.syncronote.logic.beans.CourseMapBean;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookGraphicController extends AbsLoggedGraphicController {
    @FXML
    private Label fileLbl;
    @FXML
    private Button publishBtn;
    @FXML
    private CheckBox privacyLbl;
    @FXML
    private Label courseLbl;
    @FXML
    private ComboBox<String> courseCombo;

    private AbsBookController bookController;
    private NoteChosenBean noteChosenBean;
    private List<CourseMapBean> courseMapBean;

    @Override
    public void initialize() {
        super.initialize();
        try{
            if(SessionManager.getInstance().getCurrentUser().getUserType().equals(UserTypes.STUDENT)){
                bookController = new BookStudentController();
                courseLbl.setVisible(false);
                courseCombo.setVisible(false);
            }
            else if(SessionManager.getInstance().getCurrentUser().getUserType().equals(UserTypes.PROFESSOR)){
                bookController = new BookProfessorController();
                courseMapBean = ((BookProfessorController)bookController).getCourses();
                courseLbl.setVisible(true);
                courseCombo.setVisible(true);

                for (CourseMapBean courseBean: courseMapBean) {
                    courseCombo.getItems().add(courseBean.getCourseName());
                }
            }
            else {
                showErrorAlert("Session problem", "Session user isn't set up");
                goToPage("Login");
            }
        } catch (DAOException | SQLException e) {
            showErrorAlert("Error!", e.getMessage());
        }
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
        CourseMapBean courseBean = null;
        try{
            if(noteChosenBean == null)
                throw new InvalidFormatException("File not selected");

            PublicationStudentBean publicationStudentBean = new PublicationStudentBean(
                    noteChosenBean.getFile(),
                    privacyLbl.isSelected()
            );

            for (CourseMapBean courseMap: courseMapBean) {
                if(courseMap.getCourseName().equals(courseCombo.getValue()))
                    courseBean = new CourseMapBean(
                            courseMap.getCourseId(),
                            courseMap.getCourseName()
                    );
            }

            if(courseBean == null)
                throw new InvalidFormatException("Couldn't find course");


            if(SessionManager.getInstance().getCurrentUser().getUserType().equals(UserTypes.STUDENT)){
                bookController.publishStudentNote(publicationStudentBean);
            }
            else if(SessionManager.getInstance().getCurrentUser().getUserType().equals(UserTypes.PROFESSOR)){
                ((BookProfessorController)bookController).publishProfessorNote(
                        publicationStudentBean,
                        courseBean
                );
            }
            else {
                showErrorAlert("Session problem", "Session user isn't set up");
                goToPage("Login");
            }


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
