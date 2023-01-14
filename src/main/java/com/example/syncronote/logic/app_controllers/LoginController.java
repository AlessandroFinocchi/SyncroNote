package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.dao.UserDAO;
import com.example.syncronote.logic.model.User;

public class LoginController extends IController{

    public User login(String username, String psw) throws Exception{
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUser(username, psw);

        storeSessionUser(user);

        return user;
    }
}
