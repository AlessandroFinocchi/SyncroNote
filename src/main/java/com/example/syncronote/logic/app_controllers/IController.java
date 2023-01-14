package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.model.User;
import com.example.syncronote.logic.session.SessionSingleton;

public abstract class IController {

    protected void storeSessionUser(User user){
        SessionSingleton.getInstance(user);
    }
}
