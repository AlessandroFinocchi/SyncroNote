package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PublicationDAO {

    public Integer insertPublication(Object... params) throws DAOException, SQLException {
        String title = (String) params[0];
        int course = (int) params[1];

        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "INSERT INTO Publication VALUES(?, ?)";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, title);
        stmt.setInt(2, course);

        result = stmt.executeUpdate();

        if (result == 1) {
            Logger.getAnonymousLogger().log(Level.INFO, "PUBLICATION INSERTED");
        }
        else if (result > 1){
            throw new DAOException("More than 1 row inserted, something went wrong");
        }
        else {
            Logger.getAnonymousLogger().log(Level.INFO, "PUBLICATION NOT INSERTED");
        }

        stmt.close();

        return result;
    }
}
