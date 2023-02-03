package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.model.User;
import com.example.syncronote.logic.session.SessionSingleton;

import java.util.logging.Logger;

public abstract class IController {

    Logger logger = Logger.getAnonymousLogger();

    protected void storeSessionUser(String username, String name, String surname, String email, UserTypes userType){
        SessionSingleton.getInstance(username, name, surname, email, userType);
    }
}
