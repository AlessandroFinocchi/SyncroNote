package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.RevisionableNoteBean;
import com.example.syncronote.logic.dao.ProfessorDAO;
import com.example.syncronote.logic.dao.RevisionDAO;
import com.example.syncronote.logic.graphic_controllers.ProfessorUsernamesBean;
import com.example.syncronote.logic.model.Note;
import com.example.syncronote.logic.model.Professor;
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

    public ProfessorUsernamesBean getProfessors(String selectedNote) {
        List<String> professors = null;
        try {
            professors = professorDAO.getRevisionProfessorUsernames(selectedNote);
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }

        return new ProfessorUsernamesBean(professors);
    }
}
