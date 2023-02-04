package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class GenericProcedureDAO<P> {

    protected final String USERNAME = "Username";
    protected final String NAME = "Name";
    protected final String SURNAME = "Surname";
    protected final String EMAIL = "Email";
    protected final String PSW = "Password";
    protected final String ROLE = "Role";

    protected abstract P execute(Object... params) throws DAOException, SQLException;

    protected User getUser(ResultSet rs) throws SQLException{
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
