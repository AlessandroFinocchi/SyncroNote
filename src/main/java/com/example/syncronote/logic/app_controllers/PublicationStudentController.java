package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.PublicationStudentBean;
import com.example.syncronote.logic.dao.note_procedures.PublishNoteProcedureDAO;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.session.SessionManager;

import java.sql.SQLException;


public class PublicationStudentController extends AbsPublicationController {

    @Override
    public void publishNote(PublicationStudentBean publicationStudentBean) throws DAOException, SQLException {
        int result = new PublishNoteProcedureDAO().execute(
                publicationStudentBean.getTitle(),
                SessionManager.getInstance().getCurrentUser().getUsername(),
                publicationStudentBean.getFile().getPath(),
                publicationStudentBean.isPrivateNote());

        if (result == 0)
            throw new DAOException("No rows affected!");
    }
}
