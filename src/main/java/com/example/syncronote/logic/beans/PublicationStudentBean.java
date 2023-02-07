package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.exceptions.InvalidFormatException;

import java.io.File;

public class PublicationStudentBean extends NoteChosenBean{
    protected final boolean privateNote;
    protected final String category;

    public PublicationStudentBean(File file, boolean privateNote, String category) throws InvalidFormatException {
        super(file);
        this.privateNote = privateNote;
        this.category = category;
    }

    public boolean isPrivateNote() {
        return privateNote;
    }

    public String getCategory(){ return category; }
}
