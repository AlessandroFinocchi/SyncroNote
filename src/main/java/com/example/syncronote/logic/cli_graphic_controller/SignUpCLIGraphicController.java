package com.example.syncronote.logic.cli_graphic_controller;

import com.example.syncronote.logic.app_controllers.SignUpController;
import com.example.syncronote.logic.beans.SignupBean;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import com.example.syncronote.logic.utilities.CLIPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class SignUpCLIGraphicController extends AbsCLIGraphicController {

    @Override
    public void start() {
        while(true) {
            int choice;
            try {
                choice = showMenu();
                switch(choice) {
                    case 1 -> signup();
                    case 2 -> new SignUpCLIGraphicController().start();
                    case 3 -> System.exit(0);
                    default -> throw new InvalidFormatException("Invalid choice");
                }
            } catch (IOException | InvalidFormatException e) {
                logger.log(Level.INFO, e.getMessage());
            }
        }
    }

    @Override
    public int showMenu() throws IOException {
        CLIPrinter.printMessage("*** What should I do for you? ***\n");
        CLIPrinter.printMessage("1) Signup\n");
        CLIPrinter.printMessage("2) Go to Login\n");
        CLIPrinter.printMessage("3) Quit\n");

        return getMenuChoice(1, 3);
    }

    private void signup() {
        SignUpController signUpController = new SignUpController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            CLIPrinter.printMessage("name: ");
            String name = reader.readLine();
            CLIPrinter.printMessage("surname: ");
            String surname = reader.readLine();
            CLIPrinter.printMessage("username: ");
            String username = reader.readLine();
            CLIPrinter.printMessage("email: ");
            String email = reader.readLine();
            CLIPrinter.printMessage("password: ");
            String psw = reader.readLine();
            CLIPrinter.printMessage("role (1 for student, 2 for professor) :");
            String role = reader.readLine();
            UserTypes userType;
            if(role.equals("1")) {
                userType = UserTypes.STUDENT;
                CLIPrinter.printMessage("freshman: ");
            }
            else if(role.equals("2")) {
                userType = UserTypes.PROFESSOR;
                CLIPrinter.printMessage("university: ");
            }
            else throw new InvalidFormatException("Invalid role");

            String userTypeAttr = reader.readLine();

            SignupBean bean = new SignupBean(
                    username,
                    name,
                    surname,
                    email,
                    psw,
                    userType.getId(),
                    userTypeAttr);

            signUpController.signUp(bean);
            new HomeCLIGraphicController().start();
        } catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
        }
    }
}
