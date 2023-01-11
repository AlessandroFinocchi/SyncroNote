package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.dao.UserDAO;
import com.example.syncronote.logic.model.User;
import com.example.syncronote.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.logging.Level;

public class LoginGraphicController extends IController{
    @FXML
    private Label MsgLbl;
    @FXML
    private TextField UserField;
    @FXML
    private PasswordField PswField;
    @FXML
    private CheckBox RememberChkb;                  //TODO: add a json to remember if login has to be done or not
    @FXML
    private Label SignUpLbl;

    @Override
    public void initialize(){
        super.initialize();
        SignUpLbl.setOnMouseClicked(mouseEvent -> {
            goToPage("SignUp.fxml");
        });
    }

    public void userLogin(ActionEvent event) {
        Main m = new Main();
        String username = UserField.getText();
        String password = PswField.getText();

        try{
            if(username.isEmpty() || password.isEmpty()){
                MsgLbl.setText("Empty Fields");
                return;
            }
            User user = UserDAO.findUser(username, password);

            /*TODO: save user as a Session Singleton to keep trace about the profile
             *  (it is a method in IController because is shared with SignUPGC)*/

            goToPage("Home.fxml");
        }
        catch (Exception e){
            logger.log(Level.INFO, e.getMessage());
            MsgLbl.setText("Wrong credentials");
        }
    }
}
