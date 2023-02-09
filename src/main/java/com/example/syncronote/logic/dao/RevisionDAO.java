package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.enums.VisibilityTypes;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.model.Note;
import com.example.syncronote.logic.model.Revision;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RevisionDAO extends AbsNoteDAO{
    protected static final String NOTE = "Note";
    protected static final String PROFESSOR = "Professor";
    protected static final String STUDENT_QUESTION = "StudentQuestion";
    protected static final String PROFESSOR_RESPONSE = "ProfessorResponse";

    /**
     * Table Professor
     */
    protected static final String USERNAME = "Username";

    public Integer finalizationRevision(String note) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "DELETE FROM Revision WHERE " + NOTE + " = ?";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, note);

        result = stmt.executeUpdate();

        if (result == 1) {
            Logger.getAnonymousLogger().log(Level.INFO, "ROW DELETE");
        } else {
            Logger.getAnonymousLogger().log(Level.INFO, "ROW NOT CORRECTLY DELETE");
        }

        stmt.close();

        return result;
    }

    public Integer insertNewRevision(String note, String professor, String studentQuestion) throws SQLException, DAOException {

        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "INSERT INTO Revision (" + NOTE + ", " + PROFESSOR +", " + STUDENT_QUESTION + ")"
                + " VALUES(?, ?, ?)";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, note);
        stmt.setString(2, professor);
        stmt.setString(3, studentQuestion);

        result = stmt.executeUpdate();

        if (result == 1) {
            Logger.getAnonymousLogger().log(Level.INFO, "NEW REVISION INSERTED");
        } else {
            throw new DAOException("New revision not correctly inserted");
        }

        stmt.close();

        return result;
    }

    public List<Note> getNotesToRevise(Object... params) throws SQLException {
        String professor = (String) params[0];
        List<Note> notes = new ArrayList<>();

        PreparedStatement stmt = null;
        Connection conn = null;

        conn = ConnectionFactory.getConnection();

        String sql = "SELECT n." + TITLE + ", n." + AUTHOR + ", n." + DESCRIPTION + ", n." + FILEPATH + ", n." + VISIBILITY + ", n." + CATEGORY +
                " FROM Note n JOIN Revision ON r ON n." + TITLE + " = r." + NOTE +
                " WHERE r." + PROFESSOR_RESPONSE + " IS NULL AND r." + PROFESSOR + " = ?";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, professor);

        ResultSet rs = stmt.executeQuery();

        notes = getNotes(rs);

        // Closing ResultSet and freeing resources
        rs.close();
        stmt.close();

        return notes;
    }

    public List<Note> getRevisableNotes(Object... params) throws SQLException {
        String author = (String) params[0];
        List<Note> notes;

        PreparedStatement stmt = null;
        Connection conn = null;

        conn = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM Note " +
                "WHERE " + AUTHOR + " = ? AND " + TITLE + " NOT IN (SELECT "  + NOTE + " FROM Revision)";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, author);

        ResultSet rs = stmt.executeQuery();

        notes = getNotes(rs);

        // Closing ResultSet and freeing resources
        rs.close();
        stmt.close();

        return notes;
    }

    public List<Revision> getCurrentRevisions(String author) throws SQLException {
        List<Revision> revisions;

        PreparedStatement stmt = null;
        Connection conn = null;

        conn = ConnectionFactory.getConnection();

        String sql = "SELECT r." + NOTE + ", r." + STUDENT_QUESTION + ", r." + PROFESSOR_RESPONSE +
                " FROM Revision r JOIN Note n ON r.Note = n.Title" +
                " WHERE " + AUTHOR + " = ?";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, author);

        ResultSet rs = stmt.executeQuery();

        revisions = getRevisions(rs);

        // Closing ResultSet and freeing resources
        rs.close();
        stmt.close();

        return revisions;
    }

    public Integer updateRevision(Object... params) throws SQLException {
        String note = (String) params[0];
        String professorResponse = (String) params[0];

        PreparedStatement stmt = null;
        Connection conn = null;
        Integer result = -1;

        conn = ConnectionFactory.getConnection();

        String sql = "UPDATE Revision SET " + PROFESSOR_RESPONSE +  " = ? WHERE " + NOTE + " = ?";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, professorResponse);
        stmt.setString(2, note);

        result = stmt.executeUpdate();

        if (result == 1) {
            Logger.getAnonymousLogger().log(Level.INFO, "ROW UPDATE");
        } else {
            Logger.getAnonymousLogger().log(Level.INFO, "ROW NOT CORRECTLY UPDATED");
        }

        stmt.close();

        return result;
    }

    protected List<Revision> getRevisions(ResultSet rs) throws SQLException {
        List<Revision> revisions = new ArrayList<>();
        while (rs.next()) {
            Revision revision = new Revision(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3));
            revisions.add(revision);
        }

        return revisions;
    }

}
