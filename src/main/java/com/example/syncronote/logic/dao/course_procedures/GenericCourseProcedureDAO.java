package com.example.syncronote.logic.dao.course_procedures;

import com.example.syncronote.logic.dao.GenericProcedureDAO;

public abstract class GenericCourseProcedureDAO<P> implements GenericProcedureDAO<P> {

    protected GenericCourseProcedureDAO(){}

    protected static final String ID_COURSE = "IdCourse";
    protected static final String NAME = "Name";
    protected static final String GRADE = "Grade";
    protected static final String PROFESSOR = "Professor";
    protected static final String CATEGORY = "Category";
}
