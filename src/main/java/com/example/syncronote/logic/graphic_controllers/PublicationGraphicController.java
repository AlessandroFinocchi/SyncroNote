package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.AbsPublicationController;
import com.example.syncronote.logic.app_controllers.PublicationProfessorController;
import com.example.syncronote.logic.app_controllers.PublicationStudentController;
import com.example.syncronote.logic.beans.CourseMapBean;
import com.example.syncronote.logic.beans.NoteChosenBean;
import com.example.syncronote.logic.beans.PublicationProfessorBean;
import com.example.syncronote.logic.beans.PublicationStudentBean;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import com.example.syncronote.logic.exceptions.NoCoursesException;
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

public class PublicationGraphicController extends AbsLoggedGraphicController {
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

    private AbsPublicationController bookController;
    private NoteChosenBean noteChosenBean;
    private List<CourseMapBean> courseMapBean;

    @Override
    public void initialize() {
        super.initialize();
        try{
            if(userType.equals(UserTypes.STUDENT)){
                bookController = new PublicationStudentController();
                courseLbl.setVisible(false);
                courseCombo.setVisible(false);
            }
            else if(userType.equals(UserTypes.PROFESSOR)){
                bookController = new PublicationProfessorController();
                courseMapBean = ((PublicationProfessorController)bookController).getCourses();

                courseLbl.setVisible(true);
                courseCombo.setVisible(true);

                if(!courseMapBean.isEmpty()){
                    for (CourseMapBean courseBean: courseMapBean) {
                        courseCombo.getItems().add(courseBean.getCourseName());
                    }

                    courseCombo.setValue(courseMapBean.get(0).getCourseName());
                }
            }
            else {
                showErrorAlert("Session problem", "Session user isn't set up");
                goToPage("Login.fxml");
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
        PublicationStudentBean publicationBean;
        try{
            if(noteChosenBean == null)
                throw new InvalidFormatException("File not selected");

            if(userType.equals(UserTypes.STUDENT)){

                publicationBean = new PublicationStudentBean(
                        noteChosenBean.getFile(),
                        privacyLbl.isSelected()
                );

                bookController.publishNote(publicationBean);
            }

            else if(userType.equals(UserTypes.PROFESSOR)){

                if(courseCombo.getValue() == null)
                    throw new NoCoursesException("No courses found! Create one of them before");

                for (CourseMapBean courseMap: courseMapBean) {
                    if(courseMap.getCourseName().equals(courseCombo.getValue())) {
                        courseBean = new CourseMapBean(
                                courseMap.getCourseId(),
                                courseMap.getCourseName()
                        );
                        break;
                    }
                }

                if(courseBean == null)
                    throw new InvalidFormatException("Couldn't find course");

                publicationBean = new PublicationProfessorBean(
                        noteChosenBean.getFile(),
                        privacyLbl.isSelected(),
                        courseBean.getCourseId()
                        );

                bookController.publishNote(publicationBean);
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
        catch (NoCoursesException e){
            showInfoAlert("Attention", e.getMessage());
            goToPage("Home.fxml");
        }
    }
}
