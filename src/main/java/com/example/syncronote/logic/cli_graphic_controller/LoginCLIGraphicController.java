package com.example.syncronote.logic.cli_graphic_controller;

import com.example.syncronote.logic.app_controllers.LoginController;
import com.example.syncronote.logic.beans.LoginCredentialsBean;
import com.example.syncronote.logic.exceptions.SessionUserException;
import com.example.syncronote.logic.utilities.CLIPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class LoginCLIGraphicController extends AbsCLIGraphicController {

    @Override
    public void start() {
        while(true) {
            int choice;
            try {
                choice = showMenu();
                switch(choice) {
                    case 1 -> login();
                    case 2 -> new SignUpCLIGraphicController().start();
                    case 3 -> System.exit(0);
                    default -> throw new RuntimeException("Invalid choice");
                }
            } catch (IOException e) {
                logger.log(Level.INFO, e.getMessage());
            }
        }
    }

    @Override
    public int showMenu() throws IOException {
        CLIPrinter.printMessage("*** What should I do for you? ***\n");
        CLIPrinter.printMessage("1) Login\n");
        CLIPrinter.printMessage("2) Go to Sign Up\n");
        CLIPrinter.printMessage("3) Quit\n");

        return getMenuChoice(1, 3);
    }

    private void login() {
        LoginController loginController = new LoginController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            CLIPrinter.printMessage("username: ");
            String username = reader.readLine();
            CLIPrinter.printMessage("password: ");
            String password = reader.readLine();
            LoginCredentialsBean bean = new LoginCredentialsBean(username, password);
            loginController.login(bean);
            new HomeCLIGraphicController().start();
        }
        catch (SessionUserException e){
            logger.log(Level.INFO, e.getMessage());
            new HomeCLIGraphicController().start();

        }
        catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
        }
    }
}
