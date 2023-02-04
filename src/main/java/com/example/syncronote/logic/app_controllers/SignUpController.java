package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.dao.professor_procedures.InsertProfessorDAO;
import com.example.syncronote.logic.dao.student_procedures.InsertStudentProcedureDAO;
import com.example.syncronote.logic.dao.user_procedures.FindUsernameProcedureDAO;
import com.example.syncronote.logic.dao.user_procedures.InsertUserProcedureDAO;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.model.User;

import java.sql.SQLException;
import java.util.logging.Level;

public class SignUpController extends IController{

    public int signUp(String username, String name, String surname, String email, String psw, String role, String userTypeAttrLbl) {
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

            switch (userType){
                case PROFESSOR : new InsertProfessorDAO().execute(username, userTypeAttrLbl);
                    break;
                case STUDENT : new InsertStudentProcedureDAO().execute(username, userTypeAttrLbl);
                    break;
            }

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
