package com.example.syncronote.logic.dao.student_procedures;

import com.example.syncronote.logic.dao.GenericProcedureDAO;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.model.User;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindUsernameProcedureDAO extends GenericProcedureDAO<User> {

    @Override
    public User execute(Object... params) throws DAOException, SQLException {
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
}
