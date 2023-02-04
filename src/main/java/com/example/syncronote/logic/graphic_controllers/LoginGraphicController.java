package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.LoginController;
import com.example.syncronote.logic.beans.LoginCredentialsBean;
import com.example.syncronote.logic.exceptions.EmptyFieldsException;
import com.example.syncronote.logic.exceptions.UserNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

public class LoginGraphicController extends IGraphicController {
    @FXML
    private Label msgLbl;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField pswField;
    @FXML
    private CheckBox rememberChkb;                  //TODO: add a json to remember if login has to be done or not
    @FXML
    private Label signUpLbl;

    private LoginController loginController;

    @Override
    public void initialize(){
        super.initialize();

        loginController = new LoginController();

        signUpLbl.setOnMouseClicked(mouseEvent -> {
            goToPage("SignUp.fxml");
        });
    }

    public void userLogin(ActionEvent event) {
        try{
            LoginCredentialsBean credentialsBean = new LoginCredentialsBean(
                    userField.getText(),
                    pswField.getText()
            );

            loginController.login(credentialsBean);

            goToPage("Home.fxml");
        }
        catch (EmptyFieldsException e){
            logger.log(Level.INFO, e.getMessage());
            msgLbl.setText("Empty Fields");
        }
        catch (UserNotFoundException e){
            logger.log(Level.INFO, e.getMessage());
            msgLbl.setText("Wrong credentials");
        }
        catch (SQLException e){
            logger.log(Level.INFO, e.getMessage());
            msgLbl.setText("Database not working");
        }
    }
}
