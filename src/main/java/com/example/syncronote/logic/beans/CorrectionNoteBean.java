package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.exceptions.InvalidFormatException;

import java.io.File;

public class CorrectionNoteBean extends NoteChosenBean{
    private final String newQuestion;

    public CorrectionNoteBean(String newQuestion, File file) throws InvalidFormatException {
        super(file);
        this.newQuestion = newQuestion;
    }

    public String getNewQuestion() {
        return newQuestion;
    }
}
