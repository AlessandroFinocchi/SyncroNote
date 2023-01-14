package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.app_controllers.LoginController;
import com.example.syncronote.logic.model.User;
import com.example.syncronote.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
        Main m = new Main();
        String username = userField.getText();
        String password = pswField.getText();

        try{
            if(username.isEmpty() || password.isEmpty()){
                msgLbl.setText("Empty Fields");
                return;
            }
            User user = loginController.login(username, password);

            goToPage("Home.fxml");
        }
        catch (Exception e){
            logger.log(Level.INFO, e.getMessage());
            msgLbl.setText("Wrong credentials");
        }
    }
}
