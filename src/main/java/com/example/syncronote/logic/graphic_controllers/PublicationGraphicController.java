package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.AbsPublicationController;
import com.example.syncronote.logic.app_controllers.PublicationProfessorController;
import com.example.syncronote.logic.app_controllers.PublicationStudentController;
import com.example.syncronote.logic.beans.*;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import com.example.syncronote.logic.exceptions.NoCoursesException;
import com.example.syncronote.logic.exceptions.SessionUserException;
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
    private CheckBox privacyLbl;
    @FXML
    private ComboBox<String> categoryCombo;
    @FXML
    private Label courseLbl;
    @FXML
    private ComboBox<String> courseCombo;
    @FXML
    private Button publishBtn;

    private AbsPublicationController publicationController;
    private NoteChosenBean noteChosenBean;
    private List<CourseIdMapBean> courseIdMapBean;

    @Override
    public void initialize() {
        super.initialize();
        try{
            if(userType.equals(UserTypes.STUDENT)){
                publicationController = new PublicationStudentController();

                courseLbl.setVisible(false);
                courseCombo.setVisible(false);
            }
            else if(userType.equals(UserTypes.PROFESSOR)){
                publicationController = new PublicationProfessorController();
                courseIdMapBean = ((PublicationProfessorController) publicationController).getCourses();

                courseLbl.setVisible(true);
                courseCombo.setVisible(true);

                if(!courseIdMapBean.isEmpty()){
                    for (CourseIdMapBean courseBean: courseIdMapBean) {
                        courseCombo.getItems().add(courseBean.getCourseName());
                    }

                    courseCombo.setValue(courseIdMapBean.get(0).getCourseName());
                }
            }
            else {
                showErrorAlert("Session problem", "Session user isn't set up");
                goToPage(LOGIN);
                return;
            }

            List<CategoryBean> categoryBeanList = publicationController.getCategories();

            for (CategoryBean c: categoryBeanList) {
                categoryCombo.getItems().add(c.getCategory());
            }

        } catch (DAOException | SQLException e) {
            showErrorAlert("Error!", e.getMessage());
        }
    }

    public void selectFile(MouseEvent actionEvent) {
        try{
            noteChosenBean = publicationController.getNewNote(actionEvent);
            fileLbl.setText(noteChosenBean.getTitle());
        }
        catch (InvalidFormatException e){
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }

    }

    public void publishNote(ActionEvent actionEvent) {
        CourseIdMapBean courseBean = null;
        PublicationStudentBean publicationBean;
        try{
            checkFileSelected();

            if(userType.equals(UserTypes.STUDENT)){
                publicationBean = new PublicationStudentBean(
                        noteChosenBean.getFile(),
                        privacyLbl.isSelected(),
                        categoryCombo.getValue()
                );
            }

            else if(userType.equals(UserTypes.PROFESSOR)){
                checkCourseSelected();
                for (CourseIdMapBean courseMap: courseIdMapBean) {
                    if(courseMap.getCourseName().equals(courseCombo.getValue())) {
                        courseBean = new CourseIdMapBean(
                                courseMap.getCourseId(),
                                courseMap.getCourseName()
                        );
                        break;
                    }
                }
                checkCourse(courseBean);
                publicationBean = new PublicationProfessorBean(
                        noteChosenBean.getFile(),
                        privacyLbl.isSelected(),
                        categoryCombo.getValue(),
                        courseBean.getCourseId()
                        );
            }
            else{
                throw new SessionUserException("User not set");
            }

            publicationController.publishNote(publicationBean);

            showInfoAlert("Publication", "Your note has been publicized");

            goToPage(HOME);
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
            goToPage(HOME);
        } catch (SessionUserException e) {
            showErrorAlert("User error", e.getMessage());
            goToPage(HOME);
        }
    }

    private void checkFileSelected() throws InvalidFormatException {
        if(noteChosenBean == null)
            throw new InvalidFormatException("File not selected");
    }

    private void checkCourseSelected() throws NoCoursesException {
        if(courseCombo.getValue() == null)
            throw new NoCoursesException("No courses found! Create one of them before");
    }

    private void checkCourse(CourseIdMapBean courseBean) throws InvalidFormatException {
        if(courseBean == null)
            throw new InvalidFormatException("Couldn't find course");
    }

}
