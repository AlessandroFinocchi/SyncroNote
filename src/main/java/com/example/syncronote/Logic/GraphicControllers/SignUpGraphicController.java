package com.example.syncronote.Logic.GraphicControllers;

import com.example.syncronote.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class SignUpGraphicController extends IController{
    @FXML
    private Label LoginLbl;

    @Override
    public void initialize(){
        super.initialize();
        LoginLbl.setOnMouseClicked(mouseEvent -> {
            UserLogin();
        });

    }
    public void UserSignup(ActionEvent event){

    }

    private void UserLogin() {
        try{
            Main m = new Main();
            m.ChangeScene("Login.fxml");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
