package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.SessionUserException;
import com.example.syncronote.logic.model.User;
import com.example.syncronote.logic.session.SessionManager;

public abstract class AbsController {

    protected void storeSessionUser(String username, String name, String surname, String email, UserTypes userType) throws SessionUserException {

        SessionManager sessionManager = SessionManager.getInstance();
        User currentUser = new User(username, name, surname, email, userType);
        sessionManager.login(currentUser);
    }

    public static UserTypes getCurrentUserType(){
        return SessionManager.getInstance().getCurrentUser().getUserType();
    }
}