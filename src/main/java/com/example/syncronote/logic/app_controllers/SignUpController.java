package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.SignupBean;
import com.example.syncronote.logic.dao.professor_procedures.InsertProfessorDAO;
import com.example.syncronote.logic.dao.student_procedures.InsertStudentProcedureDAO;
import com.example.syncronote.logic.dao.user_procedures.FindUsernameProcedureDAO;
import com.example.syncronote.logic.dao.user_procedures.InsertUserProcedureDAO;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import com.example.syncronote.logic.exceptions.SessionUserException;
import com.example.syncronote.logic.model.User;

import java.sql.SQLException;

public class SignUpController extends AbsController {

    public int signUp(SignupBean signupBean) throws SQLException, DAOException, InvalidFormatException, SessionUserException {
        User user = null;
        int result = -1;

        user = new FindUsernameProcedureDAO().execute(signupBean.getUsername());

        if (user == null) {
            result = new InsertUserProcedureDAO().execute(
                    signupBean.getUsername(),
                    signupBean.getName(),
                    signupBean.getSurname(),
                    signupBean.getEmail(),
                    signupBean.getPassword(),
                    signupBean.getRole());
        }
        else return result;

        UserTypes userType = UserTypes.valueOf(signupBean.getRole().toUpperCase());     //because the enum is in upper case

        switch (userType){
            case PROFESSOR : new InsertProfessorDAO().execute(signupBean.getUsername(), signupBean.getUserTypeAttr());
                break;
            case STUDENT : new InsertStudentProcedureDAO().execute(signupBean.getUsername(), signupBean.getUserTypeAttr());
                break;
            default: throw new InvalidFormatException("Unknown user type provided");
        }

        storeSessionUser(
                signupBean.getUsername(),
                signupBean.getName(),
                signupBean.getSurname(),
                signupBean.getEmail(),
                userType
                );

        return result;
    }
}
