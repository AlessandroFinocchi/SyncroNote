package com.example.syncronote.logic.dao.user_procedures;

import com.example.syncronote.logic.dao.GenericProcedureDAO;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class GenericUserProcedureDAO<P> implements GenericProcedureDAO<P>{

    protected static final String USERNAME = "Username";
    protected static final String NAME = "Name";
    protected static final String SURNAME = "Surname";
    protected static final String EMAIL = "Email";
    protected static final String PSW = "Password";
    protected static final String ROLE = "Role";

    protected User getUser(ResultSet rs) throws SQLException {
        User user;
        UserTypes type;

        if(rs.getString(ROLE).equals("Student"))
            type = UserTypes.STUDENT;
        else if (rs.getString(ROLE).equals("Professor"))
            type = UserTypes.PROFESSOR;
        else
            type = UserTypes.ADMIN;

        user = new User(
                rs.getString(USERNAME),
                rs.getString(NAME),
                rs.getString(SURNAME),
                rs.getString(EMAIL),
                type);

        return user;
    }
}
