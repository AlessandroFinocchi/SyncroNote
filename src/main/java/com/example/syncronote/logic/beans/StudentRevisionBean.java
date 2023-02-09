package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.enums.RevisionState;

public class StudentRevisionBean {
    private final String note;
    private final RevisionState state;
    private final String response;

    public StudentRevisionBean(String note, RevisionState state, String response) {
        this.note = note;
        this.state = state;
        this.response = response;
    }

    public String getNote() {
        return note;
    }

    public RevisionState getState() {
        return state;
    }

    public String getResponse() {
        return response;
    }
}
