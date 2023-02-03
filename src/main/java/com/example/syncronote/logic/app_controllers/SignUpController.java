package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.dao.UserDAO;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.model.User;

import java.util.logging.Level;

public class SignUpController extends IController{

    public int signUp(String username, String name, String surname, String email, String psw, String role) {
        UserDAO userDAO = new UserDAO();
        User user = null;
        int result = -1;
        try{
            user = userDAO.findUsername(username);

            if (user == null) {
                result = userDAO.addUser(
                        username,
                        name,
                        surname,
                        email,
                        psw,
                        role);
            }
            else return result;

            UserTypes userType = UserTypes.valueOf(role.toUpperCase());     //because the enum is in upper case

            storeSessionUser(
                    username,
                    name,
                    surname,
                    email,
                    userType
                    );

            return result;
        }
        catch (Exception e){
            logger.log(Level.INFO, e.getMessage());
        }

        return result;
    }
}
