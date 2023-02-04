package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.HomeInfosBean;
import com.example.syncronote.logic.session.SessionManager;

public class HomeController extends AbsLoggedController{
    public HomeInfosBean getHomepageInfos(){
        SessionManager sessionManager = SessionManager.getInstance();

        return new HomeInfosBean(
                sessionManager.getCurrentUser().getUserType(),
                sessionManager.getCurrentUser().getName(),
                sessionManager.getCurrentUser().getSurname()
        );
    }
}
