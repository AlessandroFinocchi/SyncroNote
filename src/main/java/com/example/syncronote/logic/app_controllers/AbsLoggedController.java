package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.SessionUserBean;
import com.example.syncronote.logic.model.User;
import com.example.syncronote.logic.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class AbsLoggedController {

    public SessionUserBean getSessionInfos(){
        SessionManager sessionManager = SessionManager.getInstance();
        List<String> loggedUserNames = new ArrayList<>();

        for (User u: sessionManager.getLoggedUsers()) {
            loggedUserNames.add(u.getUsername());
        }

        return new SessionUserBean(
                sessionManager.getCurrentUser().getUsername(),
                sessionManager.getCurrentUser().getUserType(),
                loggedUserNames
        );
    }

    public String changeCurrentUser(String username){
        return SessionManager.getInstance().changeCurrentUser(username).getUsername();
    }

    public void logoutCurrentUser(){
        SessionManager.getInstance().logout();
    }
}
