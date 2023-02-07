package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.Main;
import com.example.syncronote.logic.app_controllers.ProfessorCoursesController;
import com.example.syncronote.logic.beans.CategoryBean;
import com.example.syncronote.logic.beans.CourseBean;
import com.example.syncronote.logic.beans.NoteComponentBean;
import com.example.syncronote.logic.enums.GradeTypes;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessorCoursesGraphicController extends AbsLoggedGraphicController {
    @FXML
    private TextField courseNameFld;
    @FXML
    private ComboBox<String> gradeCombo;
    @FXML
    private ComboBox<String> categoryCombo;
    @FXML
    private VBox courseLayout;

    private ProfessorCoursesController professorCoursesController;

    @Override
    public void initialize() {
        super.initialize();
        professorCoursesController = new ProfessorCoursesController();

        setUpGradeCombo();
        setUpCategoryCombo();
        try {
            setNotesLayout();
        } catch (DAOException e) {
            showErrorAlert("Couldn't render course", e.getMessage());
        }
    }

    @FXML
    private void createCourse(ActionEvent actionEvent) {
        try {
            CourseBean courseBean = new CourseBean(
                    courseNameFld.getText(),
                    gradeCombo.getValue(),
                    categoryCombo.getValue()
            );

            professorCoursesController.createCourse(courseBean);

            goToPage(PROFESSOR_COURSES);

        }
        catch (InvalidFormatException e) {
            showErrorAlert("Invalid data format", e.getMessage());
        }
        catch (DAOException e) {
            showErrorAlert("Error", e.getMessage());
        }
    }

    private void setUpGradeCombo() {
        for (GradeTypes grade: GradeTypes.values()) {
            gradeCombo.getItems().add(grade.getId());
        }
    }

    private void setUpCategoryCombo() {
        List<CategoryBean> categories = professorCoursesController.getCategories();

        for (CategoryBean categoryBean: categories) {
            categoryCombo.getItems().add(categoryBean.getCategory());
        }

        categoryCombo.setValue(categories.get(0).getCategory());
    }

    private void setNotesLayout() throws DAOException {
        List<CourseBean> courses;
        try {
            courses = professorCoursesController.getCurrentProfessorCourses();
            for (CourseBean courseBean : courses) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Objects.requireNonNull(Main.class.getResource(COURSE_COMPONENT)));

                HBox courseGraphicComponent = fxmlLoader.load();
                ProfessorCourseComponentGraphicController courseComponentGraphicController = fxmlLoader.getController();
                courseComponentGraphicController.setCourseValues(courseBean, professorCoursesController);

                courseLayout.getChildren().add(courseGraphicComponent);
            }
        }
        catch (IOException e){
            showErrorAlert("Rendering error", "Couldn't render notes");
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }
}
