package com.example.syncronote.logic.dao;

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
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDAO {

    protected static final String ID_COURSE = "IdCourse";
    protected static final String NAME = "Name";
    protected static final String GRADE = "Grade";
    protected static final String PROFESSOR = "Professor";
    protected static final String CATEGORY = "Category";

    public List<Course> findProfessorCourse(String professor) throws SQLException {
        List<Course> courseList = new ArrayList<>();

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

    public Integer deleteCourse(String name, String professor) throws DAOException, SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "DELETE FROM Course " +
                " WHERE " + NAME + " = ? AND " + PROFESSOR + " = ?";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, name);
        stmt.setString(2, professor);

        result = stmt.executeUpdate();

        if (result == 1) {
            Logger.getAnonymousLogger().log(Level.INFO, "COURSE DELETE");
        } else {
            throw new DAOException("Error deleting course");
        }

        stmt.close();

        return result;
    }

    public Integer createCourse(String name, GradeTypes grade, String professor, String category) throws DAOException, SQLException {
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
