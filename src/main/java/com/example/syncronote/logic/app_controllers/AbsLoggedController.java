package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.CategoryBean;
import com.example.syncronote.logic.beans.SessionUserBean;
import com.example.syncronote.logic.dao.CategoryDAO;
import com.example.syncronote.logic.model.Category;
import com.example.syncronote.logic.model.User;
import com.example.syncronote.logic.session.SessionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public List<CategoryBean> getCategories() {
        List<CategoryBean> categoryBeanList = new ArrayList<>();
        List<Category> categories;

        try{
            categories = new CategoryDAO().selectCategories();

            for (Category c: categories) {
                CategoryBean categoryBean = new CategoryBean(c.getName());
                categoryBeanList.add(categoryBean);
            }
        }
        catch (SQLException e){
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }

        return categoryBeanList;
    }
}
