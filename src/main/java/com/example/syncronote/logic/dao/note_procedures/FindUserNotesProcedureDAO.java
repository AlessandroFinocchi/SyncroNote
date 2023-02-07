package com.example.syncronote.logic.dao.note_procedures;

import com.example.syncronote.logic.model.Note;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindUserNotesProcedureDAO extends GenericNoteProcedureDAO<List<Note>>{

    @Override
    public List<Note> execute(Object... params) throws SQLException {
        List<Note> notesList = new ArrayList<>();
        String username = (String) params[0];

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
}