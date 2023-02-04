package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.dao.user_procedures.FindUsernameProcedureDAO;
import com.example.syncronote.logic.dao.user_procedures.InsertUserProcedureDAO;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.model.User;

import java.sql.SQLException;
import java.util.logging.Level;

public class SignUpController extends IController{

    public int signUp(String username, String name, String surname, String email, String psw, String role) {
        User user = null;
        int result = -1;
        try{
            user = new FindUsernameProcedureDAO().execute(username);

            if (user == null) {
                result = new InsertUserProcedureDAO().execute(
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
        catch (SQLException | DAOException e){
            logger.log(Level.INFO, e.getMessage());
        }

        return result;
    }
}
