package com.example.syncronote.logic.dao.user_procedures;

import com.example.syncronote.logic.dao.GenericProcedureDAO;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertUserProcedureDAO extends GenericUserProcedureDAO<Integer> {

    @Override
    public Integer execute(Object... params) throws DAOException, SQLException {
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
