package com.example.syncronote.logic.cli_graphic_controller;

import com.example.syncronote.logic.app_controllers.AbsController;
import com.example.syncronote.logic.app_controllers.AbsLoggedController;
import com.example.syncronote.logic.app_controllers.HomeController;
import com.example.syncronote.logic.beans.HomeInfosBean;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.SessionUserException;
import com.example.syncronote.logic.utilities.CLIPrinter;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

public class HomeCLIGraphicController extends AbsLoggedCLIGraphicController{
    HomeController homeController;

    public HomeCLIGraphicController(){
        homeController = new HomeController();
    }

    @Override
    public void start() {
        while(true) {
            int choice;
            try {
                choice = showMenu();
                switch(choice) {
                    case 1 -> publishNotes();
                    case 2 -> manageNotes();
                    case 3 -> manageRevisions();
                    case 4 -> manageCourses();
                    case 5 -> gotoLogin();
                    case 6-> changeUser();
                    case 7-> logout();
                    case 8 -> System.exit(0);
                    default -> throw new RuntimeException("Invalid choice");
                }
            } catch (IOException e) {
                logger.log(Level.INFO, e.getMessage());
            }
        }
    }

    @Override
    public int showMenu() throws IOException {
        HomeInfosBean homeInfosBean = homeController.getHomepageInfos();

        CLIPrinter.printMessage("*** What should I do for you " + homeInfosBean.getUserType() + " " + homeInfosBean.getName() + " ? ***\n");
        CLIPrinter.printMessage("1) Publish notes\n");
        CLIPrinter.printMessage("2) Manage notes\n");
        CLIPrinter.printMessage("3) Revision section\n");
        CLIPrinter.printMessage("4) Manage courses\n");
        CLIPrinter.printMessage("5) Go to login\n");
        CLIPrinter.printMessage("6) Change user\n");
        CLIPrinter.printMessage("7) Logout\n");
        CLIPrinter.printMessage("8) Quit\n");

        return getMenuChoice(1, 6);
    }

    private void publishNotes() {
        new PublicationCLIGraphicController().start();
    }

    private void manageNotes() {

    }

    private void manageRevisions() {
    }

    private void manageCourses() {
        if(AbsController.getCurrentUserType() == UserTypes.PROFESSOR)
            new ProfessorCourseCLIGraphicController().start();
    }

    private void gotoLogin() {
        new LoginCLIGraphicController().start();
    }

    private void changeUser() {
        Scanner scanner = new Scanner(System.in);
        CLIPrinter.printMessage("Which user do you want to use?");
        String username = scanner.nextLine();
        if(homeController.changeCurrentUser(username) != null)
            new HomeCLIGraphicController().start();
    }

    private void logout() {
        try {
            homeController.logoutCurrentUser();
            new LoginCLIGraphicController().start();
        } catch (SessionUserException e) {
            logger.log(Level.INFO, e.getMessage());
        }
    }

}
