package com.example.syncronote.logic.dao.note_procedures;

import com.example.syncronote.logic.enums.VisibilityTypes;
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

        String sql = "SELECT " + TITLE + "," + AUTHOR + "," + DESCRIPTION + "," + FILEPATH + "," + VISIBILITY + "," + CATEGORY +
                " FROM Note WHERE " + AUTHOR + " = ?";
        // TYPE_SCROLL_INSENSITIVE: ResultSet can be slided but is sensible to db data variations
        stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Note note = new Note(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    VisibilityTypes.fromString(rs.getString(5)),
                    rs.getString(6));
            notesList.add(note);
        }

        // Closing ResultSet and freeing resources
        rs.close();
        stmt.close();

        return notesList;
    }
}