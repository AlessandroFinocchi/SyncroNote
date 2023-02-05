package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.PublicationStudentBean;
import com.example.syncronote.logic.exceptions.DAOException;

import java.sql.SQLException;

public class BookProfessorController extends BookStudentController{

    @Override
    public void publishNote(PublicationStudentBean publicationStudentBean) throws DAOException, SQLException {
        super.publishNote(publicationStudentBean);
    }
}
