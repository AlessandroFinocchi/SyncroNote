package com.example.syncronote.logic.dao.revision_procedures;

import com.example.syncronote.logic.model.Note;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Finds every revisionable note of a student, which are the ones that are not already in a revision process
public class SelectRevisionableNotesProcedureDAO extends GenericRevisionProcedureDAO<List<Note>> {

    @Override
    public List<Note> execute(Object... params) throws SQLException {
        String author = (String) params[0];
        List<Note> notes = new ArrayList<>();

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
}
