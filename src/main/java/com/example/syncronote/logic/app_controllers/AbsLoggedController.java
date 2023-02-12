package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.CategoryBean;
import com.example.syncronote.logic.beans.SessionUserBean;
import com.example.syncronote.logic.dao.CategoryDAO;
import com.example.syncronote.logic.model.Category;
import com.example.syncronote.logic.model.User;
import com.example.syncronote.logic.patterns.CategoryDAOFactory;
import com.example.syncronote.logic.session.SessionManager;

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

    public List<CategoryBean> getCategories() {
        List<CategoryBean> categoryBeanList = new ArrayList<>();
        List<Category> categories;
        CategoryDAOFactory categoryDAOFactory = new CategoryDAOFactory();

        try{
            CategoryDAO categoryDAO = categoryDAOFactory.createCategoryDAO();
            categories = categoryDAO.selectCategories();

            for (Category c: categories) {
                CategoryBean categoryBean = new CategoryBean(c.getName());
                categoryBeanList.add(categoryBean);
            }
        }
        catch (Exception e){
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }

        return categoryBeanList;
    }
}
