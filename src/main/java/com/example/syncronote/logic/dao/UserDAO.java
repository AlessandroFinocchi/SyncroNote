package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.UserNotFoundException;
import com.example.syncronote.logic.model.User;
import com.example.syncronote.logic.session.ConnectionSingleton;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {

    private static final String USERNAME = "Username";
    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";
    private static final String EMAIL = "Email";
    private static final String PSW = "Password";
    private static final String ROLE = "Role";

    private static Logger logger = Logger.getAnonymousLogger();

    public User findUser(String username, String password) throws UserNotFoundException {
        PreparedStatement stmt = null;
        Connection conn = null;
        User user = null;

        try {
            ConnectionSingleton credentials = ConnectionSingleton.getInstance();
            conn = DriverManager.getConnection(credentials.getDbUrlConfig(), credentials.getUserConfig(), credentials.getPassConfig());

            String sql = "SELECT * FROM User WHERE " + USERNAME + " = ? AND " + PSW + " = ?;";
            // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
            stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            // Verify if ResultSet is empty
            if(!rs.first()) {
                throw new UserNotFoundException();
            }

            // Repositioning of the cursor
            rs.first();

            user = getUser(rs);

            // Closing ResultSet and freeing resources
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException | IOException e){
            logger.info(e.getMessage());
        }

        return user;
    }

    public User findUsername(String username) {
        PreparedStatement stmt = null;
        Connection conn = null;
        User user = null;

        try {
            ConnectionSingleton credentials = ConnectionSingleton.getInstance();

            conn = DriverManager.getConnection(credentials.getDbUrlConfig(), credentials.getUserConfig(), credentials.getPassConfig());

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
            conn.close();
        }
        catch(SQLException | IOException e){
            logger.info(e.getMessage());
        }

        return user;
    }

    public int addUser(String username, String name, String surname, String email, String psw, String role) {
        PreparedStatement stmt = null;
        Connection conn = null;
        int result = -1;

        try {
            ConnectionSingleton credentials = ConnectionSingleton.getInstance();
            conn = DriverManager.getConnection(credentials.getDbUrlConfig(), credentials.getUserConfig(), credentials.getPassConfig());

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
                logger.log(Level.INFO, "ROW INSERTED");
            } else {
                logger.log(Level.INFO, "ROW NOT INSERTED");
            }

            stmt.close();
            conn.close();

        }

        catch(SQLException | IOException e){
            logger.info(e.getMessage());
        }

        return result;
    }
    
    private User getUser(ResultSet rs) throws SQLException{
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
