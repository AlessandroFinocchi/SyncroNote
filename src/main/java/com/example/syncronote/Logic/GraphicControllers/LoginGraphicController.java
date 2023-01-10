package com.example.syncronote.Logic.GraphicControllers;

import com.example.syncronote.Logic.DAO.UserDAO;
import com.example.syncronote.Logic.Model.User;
import com.example.syncronote.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

public class LoginGraphicController extends IController{
    @FXML
    private Button LoginBtn;
    @FXML
    private Label MsgLbl;
    @FXML
    private TextField UserField;
    @FXML
    private PasswordField PswField;
    @FXML
    private CheckBox RememberChkb;                  //add a json to remember if login has to be done or not

    public void UserLogin(ActionEvent event) throws IOException {
        CheckLogin();
    }

    private void CheckLogin() throws IOException {
        Main m = new Main();
        String username = "pippo";
        String password = "1234";

        if(UserField.getText().equals(username) && PswField.getText().equals(password)){
            m.ChangeScene("Home.fxml");
        }
        else if(UserField.getText().isEmpty() || PswField.getText().isEmpty()){
            MsgLbl.setText("Empty Fields");
        }
        else{
            MsgLbl.setText("Wrong credentials");
        }
        Dummy();
    }

    public void Dummy(){
        try{
            List<User> userList = UserDAO.GetAllUsers();
            for(User u: userList){
                System.out.println("Nickname = " + u.getNickname() + "\n");
            }
        }
        catch(Exception ex){

        }
    }
}
