package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.ProfessorCoursesController;
import com.example.syncronote.logic.beans.CourseBean;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.utilities.AbsAlertGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfessorCourseComponentGraphicController extends AbsAlertGenerator {
    @FXML
    private Label courseNameLbl;

    @FXML
    private Label categoryLbl;

    @FXML
    private Label gradeLbl;

    private ProfessorCoursesController controller;

    @Override
    public void initialize(){
        super.initialize();
        categoryLbl.setText("<no category>");
    }

    public void setCourseValues(CourseBean courseBean, ProfessorCoursesController controller) {
        courseNameLbl.setText(courseBean.getName());
        categoryLbl.setText(courseBean.getCategory());
        gradeLbl.setText(courseBean.getGrade().getId());
        this.controller = controller;
    }

    public void deleteCourse(ActionEvent actionEvent) {
        try {
            controller.deleteCourse(courseNameLbl.getText());

        } catch (DAOException e) {
            showErrorAlert("Database error", e.getMessage());
        }
    }
}
