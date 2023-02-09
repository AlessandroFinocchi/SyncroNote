package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.ProfessorRevisionBean;
import com.example.syncronote.logic.beans.StudentRevisionBean;
import com.example.syncronote.logic.dao.RevisionDAO;
import com.example.syncronote.logic.enums.RevisionState;
import com.example.syncronote.logic.model.Note;
import com.example.syncronote.logic.model.Revision;
import com.example.syncronote.logic.model.UnansweredRevisableNote;
import com.example.syncronote.logic.session.SessionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessorRevisionController extends AbsLoggedController{
    RevisionDAO revisionDAO;

    public ProfessorRevisionController(){
        super();
        revisionDAO = new RevisionDAO();
    }

    public List<ProfessorRevisionBean> getInRevisionNotes() {
        List<UnansweredRevisableNote> inRevisionNotes;
        List<ProfessorRevisionBean> professorRevisionBeans = new ArrayList<>();

        try {
            inRevisionNotes = revisionDAO.getNotesToRevise(
                    SessionManager.getInstance().getCurrentUser().getUsername());

            for (UnansweredRevisableNote note : inRevisionNotes) {
                ProfessorRevisionBean revisionableNoteBean = new ProfessorRevisionBean(
                        note.getTitle(),
                        note.getAuthor(),
                        note.getCategory(),
                        note.getStudentQuestion(),
                        note.getFilePath());
                professorRevisionBeans.add(revisionableNoteBean);
            }
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }

        return professorRevisionBeans;
    }
}
