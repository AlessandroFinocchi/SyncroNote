package com.example.syncronote.logic.dao.revision_procedures;

import com.example.syncronote.logic.model.Note;
import com.example.syncronote.logic.session.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Finds every note that a specific professor has to revise
public class SelectNotesToReviseProcedureDAO extends GenericRevisionProcedureDAO<List<Note>> {

    protected static final String NAME = "Name";
    protected static final String MACRO_AREA = "Macroarea";
    protected static final String GRADE = "Grade";

    @Override
    public List<Note> execute(Object... params) throws SQLException {
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
}
