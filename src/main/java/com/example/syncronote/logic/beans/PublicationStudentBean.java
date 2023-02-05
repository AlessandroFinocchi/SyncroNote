package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.exceptions.InvalidFormatException;

import java.io.File;

public class PublicationStudentBean extends NoteChosenBean{
    protected boolean privateNote;

    public PublicationStudentBean(File file, boolean privateNote) throws InvalidFormatException {
        super(file);
        this.privateNote = privateNote;
    }

    public boolean isPrivateNote() {
        return privateNote;
    }
}
