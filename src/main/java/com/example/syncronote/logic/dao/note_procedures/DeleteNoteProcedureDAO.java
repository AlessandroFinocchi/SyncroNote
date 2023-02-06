package com.example.syncronote.logic.dao.note_procedures;

import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteNoteProcedureDAO extends GenericNoteProcedureDAO<Integer>{

    @Override
    public Integer execute(Object... params) throws SQLException, DAOException {
        String title = (String) params[0];

        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "DELETE FROM Note WHERE Title = ?"; //ON CASCADE for the Note in Publication
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, title);

        result = stmt.executeUpdate();

        if (result > 0) {
            Logger.getAnonymousLogger().log(Level.INFO, "ROW DELETED");
        } else {
            throw new DAOException("Couldn't delete note");
        }

        stmt.close();

        return result;
    }
}
