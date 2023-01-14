package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.dao.UserDAO;
import com.example.syncronote.logic.model.User;

public class LoginController {

    public User login(String username, String psw) throws Exception{
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUser(username, psw);

        /*TODO: save user as a Session Singleton to keep trace about the profile
         *  (it is a method in IGraphicController because is shared with SignUPGC*/

        return user;
    }
}
