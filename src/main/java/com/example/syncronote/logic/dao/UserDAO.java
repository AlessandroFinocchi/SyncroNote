package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.model.User;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {

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

    //DB calls

    public User findUsername(Object... params) throws DAOException, SQLException {
        String username = (String) params[0];
        PreparedStatement stmt = null;
        Connection conn = null;
        User user = null;

        conn = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM User WHERE " + USERNAME + " = ?";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();

        // Verify if ResultSet is empty
        if(!rs.first()) {
            return null;
        }

        // Repositioning of the cursor
        rs.first();

        user = getUser(rs);

        // Closing ResultSet and freeing resources
        rs.close();
        stmt.close();

        return user;
    }

    public User findUser(Object... params) throws DAOException, SQLException {
        String username = (String) params[0];
        String password = (String) params[1];
        PreparedStatement stmt = null;
        Connection conn = null;
        User user = null;

        conn = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM User WHERE " + USERNAME + " = ? AND " + PSW + " = ?;";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        // Verify if ResultSet is empty
        if(!rs.first()) {
            throw new DAOException("Utente non trovato");
        }

        // Repositioning of the cursor
        rs.first();

        user = getUser(rs);

        // Closing ResultSet and freeing resources
        rs.close();
        stmt.close();

        return user;
    }

    public Integer registerUser(Object... params) throws DAOException, SQLException {
        String username = (String) params[0];
        String name = (String) params[1];
        String surname = (String) params[2];
        String email = (String) params[3];
        String psw = (String) params[4];
        String role = (String) params[5];

        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "INSERT INTO User (" + USERNAME + ", " + NAME +", " + SURNAME +", " + EMAIL + ", " + PSW + ", " + ROLE + ")"
                + " VALUES(?, ?, ?, ?, ?, ?)";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, username);
        stmt.setString(2, name);
        stmt.setString(3, surname);
        stmt.setString(4, email);
        stmt.setString(5, psw);
        stmt.setString(6, role);

        result = stmt.executeUpdate();

        if (result > 0) {
            Logger.getAnonymousLogger().log(Level.INFO, "ROW INSERTED");
        } else {
            Logger.getAnonymousLogger().log(Level.INFO, "ROW NOT INSERTED");
        }

        stmt.close();

        return result;
    }
}
