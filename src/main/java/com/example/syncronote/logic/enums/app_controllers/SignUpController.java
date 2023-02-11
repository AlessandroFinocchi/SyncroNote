package com.example.syncronote.logic.enums.app_controllers;

import com.example.syncronote.logic.beans.SignupBean;
import com.example.syncronote.logic.dao.ProfessorDAO;
import com.example.syncronote.logic.dao.StudentDAO;
import com.example.syncronote.logic.dao.UserDAO;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import com.example.syncronote.logic.exceptions.SessionUserException;
import com.example.syncronote.logic.model.User;

import java.sql.SQLException;

public class SignUpController extends AbsController {

    public int signUp(SignupBean signupBean) throws SQLException, InvalidFormatException, SessionUserException {
        User user = null;
        int result = -1;

        user = new UserDAO().findUsername(signupBean.getUsername());

        if (user == null) {
            result = new UserDAO().registerUser(
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
            case PROFESSOR : new ProfessorDAO().insertProfessor(signupBean.getUsername(), signupBean.getUserTypeAttr());
                break;
            case STUDENT : new StudentDAO().insertStudent(signupBean.getUsername(), signupBean.getUserTypeAttr());
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
