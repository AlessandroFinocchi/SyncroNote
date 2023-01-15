package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.LoginCredentialsBean;
import com.example.syncronote.logic.dao.UserDAO;
import com.example.syncronote.logic.exceptions.UserNotFoundException;
import com.example.syncronote.logic.model.User;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController extends IController{

    public User login(LoginCredentialsBean credentialsBean) throws UserNotFoundException, SQLException, IOException {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUser(
                credentialsBean.getUsername(),
                credentialsBean.getPassword());

        storeSessionUser(user);

        return user;
    }
}
