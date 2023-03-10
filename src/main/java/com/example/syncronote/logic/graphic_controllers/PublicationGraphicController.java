package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.AbsPublicationController;
import com.example.syncronote.logic.app_controllers.PublicationProfessorController;
import com.example.syncronote.logic.app_controllers.PublicationStudentController;
import com.example.syncronote.logic.beans.*;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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

        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
            showErrorAlert("Error", "Error in database");
        }
    }

    public void selectFile(MouseEvent actionEvent) {
        try{
            File noteFile;
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
            fileLbl.setText(noteChosenBean.getTitle());
        }
        catch (InvalidFormatException e){
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }

    public void publishNote(ActionEvent actionEvent) throws DAOException, SQLException {
        try{
            checkFileSelected();

            recognizePublication();
        }
        catch (StudentPublicationException e){
            publishAsStudent();
        }
        catch (ProfessorPublicationException e){
            publishAsProfessor();
        }
        catch (InvalidFormatException ex){
            showErrorAlert("Alert", ex.getMessage());
        }
    }

    private void recognizePublication() throws StudentPublicationException, ProfessorPublicationException {
        if(userType == UserTypes.STUDENT){
            throw new StudentPublicationException();
        }
        else
            throw new ProfessorPublicationException();
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

    private void publishAsStudent() throws DAOException, SQLException {
        try{
            PublicationStudentBean publicationBean = new PublicationStudentBean(
                    noteChosenBean.getFile(),
                    privacyLbl.isSelected(),
                    categoryCombo.getValue()
            );

            publicationController.publishNote(publicationBean);

            showInfoAlert("Publication", "Your note has been publicized");

            goToPage(HOME);
        }
        catch (InvalidFormatException ex) {
            showErrorAlert("Alert Student", ex.getMessage());
        }
    }

    private void publishAsProfessor(){
        CourseIdMapBean courseBean = null;
        PublicationProfessorBean publicationBean;
        try {
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

            publicationController.publishNote(publicationBean);

            showInfoAlert("Publication", "Your note has been publicized");

            goToPage(HOME);
        }
        catch (DAOException ex){
            Logger.getAnonymousLogger().log(Level.INFO, ex.getMessage());
            showErrorAlert("Publication Error", ex.getMessage());
        }
        catch (NoCoursesException | EmailSenderException | SQLException ex){
            showInfoAlert("Attention", ex.getMessage());
            goToPage(HOME);
        }
        catch (InvalidFormatException ex){
            showErrorAlert("Alert Professor", ex.getMessage());
        }
    }


}
