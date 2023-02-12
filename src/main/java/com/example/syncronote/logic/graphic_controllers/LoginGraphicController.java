package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.LoginController;
import com.example.syncronote.logic.beans.LoginCredentialsBean;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import com.example.syncronote.logic.exceptions.SessionUserException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.logging.Level;

public class LoginGraphicController extends AbsGraphicController {
    @FXML
    private Label msgLbl;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField pswField;
    @FXML
    private Label signUpLbl;

    private LoginController loginController;

    @Override
    public void initialize(){
        super.initialize();

        loginController = new LoginController();

        signUpLbl.setOnMouseClicked(mouseEvent -> {
            goToPage(SIGN_UP);
        });
    }

    public void userLogin(ActionEvent event) {
        try{
            LoginCredentialsBean credentialsBean = new LoginCredentialsBean(
                    userField.getText(),
                    pswField.getText()
            );

            loginController.login(credentialsBean);

            goToPage(HOME);
        }
        catch (InvalidFormatException e){
            logger.log(Level.INFO, e.getMessage());
            msgLbl.setText("Empty Fields");
        }
        catch (DAOException e){
            logger.log(Level.INFO, e.getMessage());
            msgLbl.setText("Wrong credentials");
        }
        catch (SQLException e){
            logger.log(Level.INFO, e.getMessage());
            msgLbl.setText("Database not working");
        }
        catch (SessionUserException e){
            logger.log(Level.INFO, e.getMessage());
            msgLbl.setText("User already logged in");
            goToPage(HOME);
        }
    }
}
