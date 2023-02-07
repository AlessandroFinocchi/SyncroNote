package com.example.syncronote.logic.dao.course_procedures;

import com.example.syncronote.logic.enums.GradeTypes;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.model.Course;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindProfessorCourseProcedureDAO extends GenericCourseProcedureDAO<List<Course>> {

    @Override
    public List<Course> execute(Object... params) throws DAOException, SQLException {
        List<Course> courseList = new ArrayList<>();
        String professor = (String) params[0];

        PreparedStatement stmt = null;
        Connection conn = null;

        conn = ConnectionFactory.getConnection();

        String sql = "SELECT " + ID_COURSE + "," + NAME + "," + GRADE + "," + PROFESSOR + "," + CATEGORY +
                " FROM Course WHERE " + PROFESSOR + " = ?";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, professor);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Course course = new Course(
                    rs.getInt(1),
                    rs.getString(2),
                    GradeTypes.fromString(rs.getString(3)),
                    rs.getString(4),
                    rs.getString(5));
            courseList.add(course);
        }

        // Closing ResultSet and freeing resources
        rs.close();
        stmt.close();

        return courseList;
    }
}
