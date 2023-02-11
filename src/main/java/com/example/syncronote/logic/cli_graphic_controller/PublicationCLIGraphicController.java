package com.example.syncronote.logic.cli_graphic_controller;

import com.example.syncronote.logic.beans.*;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.enums.app_controllers.AbsController;
import com.example.syncronote.logic.enums.app_controllers.AbsPublicationController;
import com.example.syncronote.logic.enums.app_controllers.PublicationProfessorController;
import com.example.syncronote.logic.enums.app_controllers.PublicationStudentController;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.exceptions.EmailSenderException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import com.example.syncronote.logic.exceptions.SessionUserException;
import com.example.syncronote.logic.utilities.CLIPrinter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class PublicationCLIGraphicController extends AbsCLIGraphicController{

    AbsPublicationController controller;
    BufferedReader reader;
    private List<CourseIdMapBean> courseIdMapBean;

    @Override
    public void start() {
        try {
            if(AbsController.getCurrentUserType() == UserTypes.STUDENT)
                controller = new PublicationStudentController();
            else if(AbsController.getCurrentUserType() == UserTypes.PROFESSOR) {
                controller = new PublicationProfessorController();
                courseIdMapBean = ((PublicationProfessorController) controller).getCourses();
            }
            else throw new SessionUserException("Session user isn't set up");

            reader = new BufferedReader(new InputStreamReader(System.in));

            while(true) {
                int choice;
                choice = showMenu();
                switch (choice) {
                    case 1 -> publishNote();
                    case 2 -> gotoHomepage();
                    case 3 -> System.exit(0);
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
        CLIPrinter.printMessage("*** PUBLISH NOTE ***\n");
        CLIPrinter.printMessage("1) Publish note\n");
        CLIPrinter.printMessage("2) Goto homepage\n");
        CLIPrinter.printMessage("3) Quit\n");

        return getMenuChoice(1, 6);
    }

    private void publishNote() throws IOException, InvalidFormatException, DAOException, SQLException, EmailSenderException {
        PublicationStudentBean publicationBean;
        CourseIdMapBean courseBean = null;
        CLIPrinter.printMessage("absolute path: ");
        String filePath = reader.readLine();
        CLIPrinter.printMessage("private? (yes or not): ");
        String visibilityString = reader.readLine();
        CLIPrinter.printMessage("category: ");
        String category = reader.readLine();

        File noteFile = new File(filePath);

        if(AbsController.getCurrentUserType() == UserTypes.STUDENT){
            publicationBean = new PublicationStudentBean(
                    noteFile,
                    visibilityString.equalsIgnoreCase("yes"),
                    category
            );
        }
        else{
            CLIPrinter.printMessage("course: ");
            String course = reader.readLine();

            for (CourseIdMapBean courseMap: courseIdMapBean) {
                if(courseMap.getCourseName().equals(course)) {
                    courseBean = new CourseIdMapBean(
                            courseMap.getCourseId(),
                            courseMap.getCourseName()
                    );
                    break;
                }
            }
            checkCourse(courseBean);
            publicationBean = new PublicationProfessorBean(
                    noteFile,
                    visibilityString.equalsIgnoreCase("yes"),
                    category,
                    courseBean.getCourseId()
            );

        }

        controller.publishNote(publicationBean);

    }

    private void gotoHomepage() {
        new HomeCLIGraphicController().start();
    }

    private void checkCourse(CourseIdMapBean courseBean) throws InvalidFormatException {
        if(courseBean == null)
            throw new InvalidFormatException("Couldn't find course");
    }
}
