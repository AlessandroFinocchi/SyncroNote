package com.example.syncronote.logic.beans;

import java.util.List;

public class ProfessorUsernamesBean {
    private List<String> professorNames;

    public ProfessorUsernamesBean(List<String> professorNames) {
        this.professorNames = professorNames;
    }

    public List<String> getProfessorNames() {
        return professorNames;
    }
}
