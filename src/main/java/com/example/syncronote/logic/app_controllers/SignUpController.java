package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.dao.UserDAO;
import com.example.syncronote.logic.model.User;

public class SignUpController {

    public int signUp(String username, String name, String surname, String email, String psw, String role) {
        UserDAO userDAO = new UserDAO();
        int result = -1;
        try{
            if (userDAO.findUsername(username) == null) {
                result = userDAO.addUser(
                        username,
                        name,
                        surname,
                        email,
                        psw,
                        role);
            }

            /*TODO: save user as a Session Singleton to keep trace about the profile
             *  (it is a method in IGraphicController because is shared with LoginGC)*/

            return result;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }
}
