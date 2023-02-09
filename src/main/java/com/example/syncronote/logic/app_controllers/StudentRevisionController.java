package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.NoteChosenBean;
import com.example.syncronote.logic.beans.RevisionableNoteBean;
import com.example.syncronote.logic.beans.StartNewRevisionBean;
import com.example.syncronote.logic.beans.StudentRevisionBean;
import com.example.syncronote.logic.dao.ProfessorDAO;
import com.example.syncronote.logic.dao.RevisionDAO;
import com.example.syncronote.logic.enums.RevisionState;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.graphic_controllers.ProfessorUsernamesBean;
import com.example.syncronote.logic.model.Note;
import com.example.syncronote.logic.model.Revision;
import com.example.syncronote.logic.session.SessionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentRevisionController extends AbsLoggedController{
    private RevisionDAO revisionDAO;
    private ProfessorDAO professorDAO;

    public StudentRevisionController(){
        super();
        revisionDAO = new RevisionDAO();
        professorDAO = new ProfessorDAO();
    }

    public List<RevisionableNoteBean> getRevisableNotes() {
        List<Note> revisableNotes;
        List<RevisionableNoteBean> revisableNotesBean = new ArrayList<>();

        try {
            revisableNotes = revisionDAO.getRevisableNotes(SessionManager.getInstance().getCurrentUser().getUsername());

            for (Note note : revisableNotes) {
                RevisionableNoteBean revisionableNoteBean = new RevisionableNoteBean(note.getTitle());
                revisableNotesBean.add(revisionableNoteBean);
            }
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }

        return revisableNotesBean;
    }

    public ProfessorUsernamesBean getProfessors(NoteChosenBean noteChosenBean) {
        List<String> professors = null;
        try {
            professors = professorDAO.getRevisionProfessorUsernames(noteChosenBean.getTitle());
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }

        return new ProfessorUsernamesBean(professors);
    }

    public void startNewRevision(StartNewRevisionBean bean) throws DAOException {
        try{
            revisionDAO.insertNewRevision(
                    bean.getNote(),
                    bean.getProfessor(),
                    bean.getQuestion()
            );
        }
        catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }

    public List<StudentRevisionBean> getInRevisionNotes() {
        List<Revision> inRevisionNotes;
        List<StudentRevisionBean> studentRevisionBeans = new ArrayList<>();

        try {
            inRevisionNotes = revisionDAO.getCurrentRevisions(
                    SessionManager.getInstance().getCurrentUser().getUsername());

            for (Revision revision : inRevisionNotes) {
                RevisionState state;
                if(revision.getQuestion() == null)
                    state = RevisionState.COMPLETED;
                else if(revision.getResponse() == null)
                    state = RevisionState.IN_REVISION;
                else
                    state = RevisionState.REVISED;

                StudentRevisionBean revisionableNoteBean = new StudentRevisionBean(
                        revision.getTitle(),
                        state,
                        revision.getResponse());
                studentRevisionBeans.add(revisionableNoteBean);
            }
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }

        return studentRevisionBeans;
    }
}
