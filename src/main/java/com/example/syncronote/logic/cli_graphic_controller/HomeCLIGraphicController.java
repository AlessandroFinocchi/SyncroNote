package com.example.syncronote.logic.cli_graphic_controller;

import com.example.syncronote.logic.app_controllers.HomeController;
import com.example.syncronote.logic.beans.HomeInfosBean;
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
                    case 1 -> manageNotes();
                    case 2 -> manageCourses();
                    case 3 -> gotoLogin();
                    case 4-> changeUser();
                    case 5-> logout();
                    case 6 -> System.exit(0);
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
        CLIPrinter.printMessage("1) Manage notes\n");
        CLIPrinter.printMessage("2) Manage courses\n");
        CLIPrinter.printMessage("3) Go to login\n");
        CLIPrinter.printMessage("4) Change user\n");
        CLIPrinter.printMessage("5) Logout\n");
        CLIPrinter.printMessage("6) Quit\n");

        return getMenuChoice(1, 6);
    }

    private void manageNotes() {
        CLIPrinter.printMessage("1\n");
    }

    private void manageCourses() {
        CLIPrinter.printMessage("2\n");
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
