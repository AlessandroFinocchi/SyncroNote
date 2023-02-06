package com.example.syncronote.logic.dao.note_procedures;

import com.example.syncronote.logic.dao.GenericProcedureDAO;
import com.example.syncronote.logic.enums.VisibilityTypes;

public abstract class GenericNoteProcedureDAO<P> implements GenericProcedureDAO<P>{

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

}
