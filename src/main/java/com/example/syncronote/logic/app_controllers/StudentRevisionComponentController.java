package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.NoteChosenBean;
import com.example.syncronote.logic.beans.RevisionableNoteBean;
import com.example.syncronote.logic.dao.NoteDAO;
import com.example.syncronote.logic.dao.RevisionDAO;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.utilities.NavigatorSingleton;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentRevisionComponentController extends AbsLoggedController{
    private NoteDAO noteDAO;
    private RevisionDAO revisionDAO;

    public StudentRevisionComponentController(){
        noteDAO = new NoteDAO();
        revisionDAO = new RevisionDAO();
    }

    public void republishNote(NoteChosenBean noteBean) throws DAOException {
        try {
            noteDAO.updatePublishedNote(noteBean.getTitle(), noteBean.getFile().getPath());
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }

    public void deleteRevision(RevisionableNoteBean noteBean) throws DAOException {
        try {
            revisionDAO.finalizationRevision(noteBean.getNoteName());
            NavigatorSingleton.getInstance().gotoPage("StudentRevision.fxml");
        } catch (SQLException | IOException e) {
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }
}