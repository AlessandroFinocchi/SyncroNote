package com.example.syncronote.logic.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class StudentDAOTest {

    @Test
    void insertStudent() {
        StudentDAO studentDAO = new StudentDAO();

        try{
            studentDAO.insertStudent("Alex", "1234567");
            Assertions.fail(); //it has to fail, the student is already registered
        } catch (SQLException e) {
            assertEquals(1,1);
        }
    }
}