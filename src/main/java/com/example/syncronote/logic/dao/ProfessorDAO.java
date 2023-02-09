package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessorDAO {
    private static final String USERNAME = "Username";
    private static final String UNIVERSITY = "University";

    public Integer insertProfessor(String username, String university) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "INSERT INTO Professor VALUES(?, ?)";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, username);
        stmt.setString(2, university);

        result = stmt.executeUpdate();

        if (result > 0) {
            Logger.getAnonymousLogger().log(Level.INFO, "PROFESSOR INSERTED");
        } else {
            Logger.getAnonymousLogger().log(Level.INFO, "PROFESSOR NOT INSERTED");
        }

        stmt.close();

        return result;
    }

    // Returns a list of every professor with a course of the selected note category
    public List<String> getRevisionProfessorUsernames(String noteName) throws SQLException {
        List<String> professors = new ArrayList<>();

        PreparedStatement stmt = null;
        Connection conn = null;

        conn = ConnectionFactory.getConnection();

        String sql = "SELECT " + USERNAME +
                " FROM Professor p join Course c on p.Username = c.Professor" +
                " WHERE c.Category = (SELECT Category FROM Note WHERE Title = ?)";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, noteName);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            professors.add(rs.getString(1));
        }

        // Closing ResultSet and freeing resources
        rs.close();
        stmt.close();

        return professors;
    }
}
