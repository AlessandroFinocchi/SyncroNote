package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.CorrectionNoteBean;
import com.example.syncronote.logic.beans.NoteChosenBean;
import com.example.syncronote.logic.dao.NoteDAO;
import com.example.syncronote.logic.dao.RevisionDAO;
import com.example.syncronote.logic.exceptions.DAOException;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentRevisionComponentController{
    private NoteDAO noteDAO;
    private RevisionDAO revisionDAO;

    public StudentRevisionComponentController(){
        noteDAO = new NoteDAO();
        revisionDAO = new RevisionDAO();
    }

    public void republishNote(CorrectionNoteBean noteBean) throws DAOException {
        try {
            noteDAO.updatePublishedNote(noteBean.getTitle(), noteBean.getFile().getPath());
            revisionDAO.updateRevision(noteBean.getTitle(), noteBean.getNewQuestion(), null);

        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }

    public void finalizeRevision(NoteChosenBean noteBean) throws DAOException {

        try {
            revisionDAO.finalizationRevision(noteBean.getTitle());
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }
}