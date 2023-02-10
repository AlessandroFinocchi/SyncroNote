package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.HomeInfosBean;
import com.example.syncronote.logic.exceptions.SessionUserException;
import com.example.syncronote.logic.session.SessionManager;

public class HomeController extends AbsLoggedController{
    public void logoutCurrentUser() throws SessionUserException {
        SessionManager sessionManager = SessionManager.getInstance();
        if(getSessionInfos().getLoggedUsers().contains(sessionManager.getCurrentUser().getUsername()))
            sessionManager.logout();
        else
            throw new SessionUserException("User not logged in");
    }

    public HomeInfosBean getHomepageInfos(){
        SessionManager sessionManager = SessionManager.getInstance();

        return new HomeInfosBean(
                sessionManager.getCurrentUser().getUserType(),
                sessionManager.getCurrentUser().getName(),
                sessionManager.getCurrentUser().getSurname()
        );
    }
}
