package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.NoteFilePathBean;
import com.example.syncronote.logic.beans.ResponseBean;
import com.example.syncronote.logic.dao.RevisionDAO;
import com.example.syncronote.logic.utilities.NavigatorSingleton;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessorRevisionComponentController {
    private RevisionDAO revisionDAO;

    public ProfessorRevisionComponentController(){
        revisionDAO = new RevisionDAO();
    }

    public void openNote(NoteFilePathBean noteFilePathBean) throws IOException {
        Runtime r = Runtime.getRuntime();
        r.exec("explorer.exe " + noteFilePathBean.getFilePath());
    }

    public void correctNote(ResponseBean responseBean) {
        try {
            revisionDAO.updateRevision(responseBean.getNote(), responseBean.getAnswer(), responseBean.getResponse());
            NavigatorSingleton.getInstance().gotoPage("ProfessorRevision.fxml");
        } catch (SQLException | IOException e) {
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }
}
