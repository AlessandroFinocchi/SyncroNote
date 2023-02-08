package com.example.syncronote.logic.beans;

public class RevisionableNoteBean {
    private final String noteName;

    public RevisionableNoteBean(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteName() {
        return noteName;
    }
}
