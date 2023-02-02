package com.example.syncronote.logic.graphic_controllers;

import com.example.syncronote.logic.exceptions.UserNotSetException;
import com.example.syncronote.logic.model.User;
import com.example.syncronote.logic.session.SessionSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeGraphicController extends ILoggedGraphicController {

    @FXML
    private Button generalBtn;

    Logger logger = Logger.getAnonymousLogger();
    public void onGeneralBtnClicked(ActionEvent event){
        try{
            User user = SessionSingleton.getInstance().getSessionUser();
            System.out.println("TROVATOOOOOOOOO " + user.getName());
        }
        catch (UserNotSetException e){
            logger.log(Level.INFO, e.getMessage());
        }
        goToPage("Login.fxml");
    }
}
