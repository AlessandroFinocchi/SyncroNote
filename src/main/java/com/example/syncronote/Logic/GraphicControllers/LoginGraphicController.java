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
            UserSignup();
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

            if(user != null)
                m.ChangeScene("Home.fxml");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            MsgLbl.setText("Wrong credentials");
        }
    }

    private void UserSignup() {
        try{
            Main m = new Main();
            m.ChangeScene("SignUp.fxml");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
