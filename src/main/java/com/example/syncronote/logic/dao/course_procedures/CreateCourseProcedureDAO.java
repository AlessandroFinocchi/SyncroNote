package com.example.syncronote.logic.dao.course_procedures;

import com.example.syncronote.logic.enums.GradeTypes;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateCourseProcedureDAO extends GenericCourseProcedureDAO<Integer> {

    @Override
    public Integer execute(Object... params) throws DAOException, SQLException {
        String name = (String) params[0];
        GradeTypes grade = (GradeTypes) params[1];
        String professor = (String) params[2];
        String category = (String) params[3];

        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "INSERT INTO Course (" + NAME + "," + GRADE + "," + PROFESSOR + "," + CATEGORY + ") " +
                " VALUES(?, ?, ?, ?)";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, name);
        stmt.setString(2, grade.getId());
        stmt.setString(3, professor);
        stmt.setString(4, category);

        result = stmt.executeUpdate();

        if (result == 1) {
            Logger.getAnonymousLogger().log(Level.INFO, "COURSE INSERTED");
        } else {
            throw new DAOException("Error creating course");
        }

        stmt.close();

        return result;
    }
}

