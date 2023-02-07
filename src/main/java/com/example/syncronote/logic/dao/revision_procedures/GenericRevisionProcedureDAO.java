package com.example.syncronote.logic.dao.revision_procedures;

import com.example.syncronote.logic.dao.note_procedures.GenericNoteProcedureDAO;

public abstract class GenericRevisionProcedureDAO<P> extends GenericNoteProcedureDAO<P> {
    protected static final String NOTE = "Note";
    protected static final String PROFESSOR = "Professor";
    protected static final String STUDENT_QUESTION = "StudentQuestion";
    protected static final String PROFESSOR_RESPONSE = "ProfessorResponse";

    /**
     * Table Professor
     */
    protected static final String USERNAME = "Username";

}
