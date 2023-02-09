package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.model.Professor;
import com.example.syncronote.logic.model.Student;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO {

    public Integer insertStudent(String username, String freshman) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "INSERT INTO Student VALUES(?, ?)";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, username);
        stmt.setString(2, freshman);

        result = stmt.executeUpdate();

        if (result > 0) {
            Logger.getAnonymousLogger().log(Level.INFO, "STUDENT INSERTED");
        } else {
            Logger.getAnonymousLogger().log(Level.INFO, "STUDENT NOT INSERTED");
        }

        stmt.close();

        return result;
    }

    public List<Student> getSubscribed(int courseId) throws SQLException {
        List<Student> studentList = new ArrayList<>();
        PreparedStatement stmt = null;
        Connection conn = null;

        conn = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM User u JOIN Student s on u.Username = s.Username JOIN Subscribed ss on s.Username = ss.Student" +
                " WHERE ss.Course = ?";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setInt(1, courseId);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            Student student = new Student(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    UserTypes.STUDENT,
                    rs.getString(8)
            );
            studentList.add(student);
        }

        // Closing ResultSet and freeing resources
        rs.close();
        stmt.close();

        return studentList;
    }
}
