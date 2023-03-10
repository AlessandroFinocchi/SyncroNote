package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.NoteComponentBean;
import com.example.syncronote.logic.beans.NoteFilePathBean;
import com.example.syncronote.logic.dao.NoteDAO;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.model.Note;
import com.example.syncronote.logic.session.SessionManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserNotesController extends AbsLoggedController {

    public List<NoteComponentBean> getUserNotes() {
        List<NoteComponentBean> noteBeansList = new ArrayList<>();
        List<Note> notesList;
        String username;

        try {
            username = SessionManager.getInstance().getCurrentUser().getUsername();
            notesList = new NoteDAO().findUserNotes(username);

            for (Note note : notesList) {
                NoteComponentBean noteComponentBean = new NoteComponentBean(
                        note.getTitle(),
                        note.getCategory(),
                        note.getDescription(),
                        note.getVisibility(),
                        note.getFilePath()
                );

                noteBeansList.add(noteComponentBean);
            }
        }
        catch (SQLException e){
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }

        return noteBeansList;
    }

    public void deleteNote(NoteComponentBean noteComponentBean) throws DAOException {
        try {
            new NoteDAO().deleteNote(noteComponentBean.getTitle());
        }
        catch (SQLException e){
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }

    public void openNote(NoteFilePathBean noteFilePathBean) throws IOException {
        Runtime r = Runtime.getRuntime();
        r.exec("explorer.exe " + noteFilePathBean.getFilePath());
    }
}
