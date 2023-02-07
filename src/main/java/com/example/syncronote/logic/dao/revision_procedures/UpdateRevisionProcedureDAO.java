package com.example.syncronote.logic.dao.revision_procedures;

import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// The update with the response of the professor to a specific revision
public class UpdateRevisionProcedureDAO extends GenericRevisionProcedureDAO<Integer> {

    @Override
    public Integer execute(Object... params) throws DAOException, SQLException {
        String note = (String) params[0];
        String professorResponse = (String) params[0];

        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "UPDATE Revision SET " + PROFESSOR_RESPONSE +  " = ? WHERE " + NOTE + " = ?";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, professorResponse);
        stmt.setString(2, note);

        result = stmt.executeUpdate();

        if (result == 1) {
            Logger.getAnonymousLogger().log(Level.INFO, "ROW UPDATE");
        } else {
            Logger.getAnonymousLogger().log(Level.INFO, "ROW NOT CORRECTLY UPDATED");
        }

        stmt.close();

        return result;
    }
}
