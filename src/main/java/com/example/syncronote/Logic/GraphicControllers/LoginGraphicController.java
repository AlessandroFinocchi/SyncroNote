package com.example.syncronote.Logic.GraphicControllers;

import com.example.syncronote.Logic.DAO.UserDAO;
import com.example.syncronote.Logic.Model.User;
import com.example.syncronote.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

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

    public void UserLogin(ActionEvent event) {
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
            System.out.println(e.getMessage());
            MsgLbl.setText("Wrong credentials");
        }
    }
}
