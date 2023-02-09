package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO {

    public Integer insertStudent(String username, String freshman) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "INSERT INTO Student VALUES(?, ?)";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, username);
        stmt.setString(2, freshman);

        result = stmt.executeUpdate();

        if (result > 0) {
            Logger.getAnonymousLogger().log(Level.INFO, "STUDENT INSERTED");
        } else {
            Logger.getAnonymousLogger().log(Level.INFO, "STUDENT NOT INSERTED");
        }

        stmt.close();

        return result;
    }
}
