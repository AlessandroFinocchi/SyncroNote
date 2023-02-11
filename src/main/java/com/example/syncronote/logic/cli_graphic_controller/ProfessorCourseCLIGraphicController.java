package com.example.syncronote.logic.cli_graphic_controller;

import com.example.syncronote.logic.enums.app_controllers.ProfessorCoursesController;
import com.example.syncronote.logic.beans.CategoryBean;
import com.example.syncronote.logic.beans.CourseBean;
import com.example.syncronote.logic.enums.GradeTypes;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import com.example.syncronote.logic.utilities.CLIPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class ProfessorCourseCLIGraphicController extends AbsLoggedCLIGraphicController {

    ProfessorCoursesController professorCoursesController;
    List<CourseBean> courses;
    List<CategoryBean> categories;
    BufferedReader reader;

    @Override
    public void start() {
        try {
            professorCoursesController = new ProfessorCoursesController();
            categories = professorCoursesController.getCategories();
            reader = new BufferedReader(new InputStreamReader(System.in));

            while(true) {
                int choice;
                choice = showMenu();
                switch (choice) {
                    case 1 -> createCourse();
                    case 2 -> deleteCourse();
                    case 3 -> visualizeCourse();
                    case 4 -> gotoHomepage();
                    case 5 -> System.exit(0);
                    default -> throw new InvalidFormatException("Invalid choice");
                }
            }
        }
        catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
        }
    }

    @Override
    public int showMenu() {
        CLIPrinter.printMessage("*** MANAGE COURSE ***\n");
        CLIPrinter.printMessage("1) Create course\n");
        CLIPrinter.printMessage("2) Delete course\n");
        CLIPrinter.printMessage("3) Visualize your courses\n");
        CLIPrinter.printMessage("4) Goto homepage\n");
        CLIPrinter.printMessage("5) Quit\n");

        return getMenuChoice(1, 6);
    }

    private void createCourse() throws IOException, InvalidFormatException, DAOException {
        CLIPrinter.printMessage("name: ");
        String name = reader.readLine();

        CLIPrinter.printMessage("grade (1 for Primary, 2 for Secondary, 3 for University) : ");
        String grade = reader.readLine();
        GradeTypes gradeType = switch (grade) {
            case "1" -> GradeTypes.PRIMARY;
            case "2" -> GradeTypes.SECONDARY;
            case "3" -> GradeTypes.UNIVERSITY;
            default -> throw new InvalidFormatException("Invalid grade\n");
        };

        CLIPrinter.printMessage("category: ");
        String category = reader.readLine();

        CourseBean courseBean = new CourseBean(
                name,
                gradeType.getId(),
                category
        );

        professorCoursesController.createCourse(courseBean);

        CLIPrinter.printMessage("Course created!\n");
    }

    private void deleteCourse() throws IOException, DAOException {
        CLIPrinter.printMessage("name: ");
        String name = reader.readLine();
        CourseBean courseBean = new CourseBean(name);
        professorCoursesController.deleteCourse(courseBean);
    }

    private void visualizeCourse() throws SQLException {

        courses = professorCoursesController.getCurrentProfessorCourses();
        for (CourseBean courseBean: courses) {
            CLIPrinter.printMessage(courseBean.getName() + "\n");
        }
    }

    private void gotoHomepage() {
        new HomeCLIGraphicController().start();
    }
}
