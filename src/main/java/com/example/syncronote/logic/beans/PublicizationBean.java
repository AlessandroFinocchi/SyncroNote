package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.exceptions.InvalidFormatException;

import java.io.File;

public class PublicizationBean extends NoteChosenBean{
    private boolean privateNote;

    public PublicizationBean(File file, boolean privateNote) throws InvalidFormatException {
        super(file);
        this.privateNote = privateNote;
    }

    public boolean isPrivateNote() {
        return privateNote;
    }
}
