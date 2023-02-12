package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.LoginCredentialsBean;
import com.example.syncronote.logic.dao.UserDAO;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.exceptions.SessionUserException;
import com.example.syncronote.logic.model.User;

import java.sql.SQLException;

public class LoginController extends AbsController {

    public void login(LoginCredentialsBean credentialsBean) throws DAOException, SQLException, SessionUserException {
        User user = new UserDAO().findUser(
                credentialsBean.getUsername(),
                credentialsBean.getPassword());

        storeSessionUser(user.getUsername(), user.getName(), user.getSurname(), user.getEmail(), user.getUserType());
    }
}
