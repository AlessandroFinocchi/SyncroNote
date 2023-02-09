package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.model.Note;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoteDAO extends AbsNoteDAO{

    public Integer publishNote(String title, String username, String filePath, boolean isPrivate, String category) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "INSERT INTO Note (" + TITLE + ", " + AUTHOR +", " + FILEPATH + ", " + VISIBILITY + ", " + CATEGORY + ")"
                + " VALUES(?, ?, ?, ?, ?)";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, title);
        stmt.setString(2, username);
        stmt.setString(3, filePath);
        stmt.setString(4, getVisibility(isPrivate).getId());
        stmt.setString(5, category);

        result = stmt.executeUpdate();

        if (result > 0) {
            Logger.getAnonymousLogger().log(Level.INFO, "ROW INSERTED");
        } else {
            Logger.getAnonymousLogger().log(Level.INFO, "ROW NOT INSERTED");
        }

        stmt.close();

        return result;
    }

    public Integer updatePublishedNote(String noteName, String filePath) throws SQLException, DAOException {
        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "UPDATE Note " +
                "SET File = ? WHERE Title = ?"; //ON CASCADE for the Note in Publication
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, filePath);
        stmt.setString(2, noteName);

        result = stmt.executeUpdate();

        if (result > 0) {
            Logger.getAnonymousLogger().log(Level.INFO, "ROW UPDATED");
        } else {
            throw new DAOException("Couldn't update note");
        }

        stmt.close();

        return result;
    }

    public List<Note> findUserNotes(String username) throws SQLException {
        List<Note> notesList = new ArrayList<>();

        PreparedStatement stmt = null;
        Connection conn = null;

        conn = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM Note WHERE " + AUTHOR + " = ?";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();

        notesList = getNotes(rs);

        // Closing ResultSet and freeing resources
        rs.close();
        stmt.close();

        return notesList;
    }

    public Integer deleteNote(String title) throws SQLException, DAOException {
        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "DELETE FROM Note WHERE Title = ?"; //ON CASCADE for the Note in Publication
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, title);

        result = stmt.executeUpdate();

        if (result > 0) {
            Logger.getAnonymousLogger().log(Level.INFO, "ROW DELETED");
        } else {
            throw new DAOException("Couldn't delete note");
        }

        stmt.close();

        return result;
    }
}
