package com.example.syncronote.logic.dao;

import com.example.syncronote.logic.enums.VisibilityTypes;
import com.example.syncronote.logic.model.Note;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbsNoteDAO {
    protected static final String TITLE = "Title";
    protected static final String AUTHOR = "Author";
    protected static final String DESCRIPTION = "Description";
    protected static final String FILEPATH = "File";
    protected static final String VISIBILITY = "Visibility";
    protected static final String CATEGORY = "Category";

    protected VisibilityTypes getVisibility(boolean isPrivate){
        if(isPrivate)
            return VisibilityTypes.PRIVATE;

        return VisibilityTypes.PUBLIC;
    }

    protected List<Note> getNotes(ResultSet rs) throws SQLException {
        List<Note> notes = new ArrayList<>();
        while (rs.next()) {
            Note note = new Note(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    VisibilityTypes.fromString(rs.getString(5)),
                    rs.getString(6));
            notes.add(note);
        }

        return notes;
    }
}
