package com.example.syncronote.logic.dao.note_procedures;

import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PublishNoteProcedureDAO extends GenericNoteProcedureDAO<Integer> {

    @Override
    public Integer execute(Object... params) throws DAOException, SQLException {
        String title = (String) params[0];
        String username = (String) params[1];
        String filePath = (String) params[2];
        boolean isPrivate = (boolean) params[3];

        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "INSERT INTO Note (" + TITLE + ", " + AUTHOR +", " + FILEPATH + ", " + VISIBILITY + ")"
                + " VALUES(?, ?, ?, ?)";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, title);
        stmt.setString(2, username);
        stmt.setString(3, filePath);
        stmt.setString(4, getVisibility(isPrivate).getId());

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

