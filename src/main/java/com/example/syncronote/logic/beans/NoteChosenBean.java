package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.exceptions.InvalidFormatException;

import java.io.File;

public class NoteChosenBean {
    protected final String title;
    protected final File file;

    public NoteChosenBean(File file) throws InvalidFormatException{
        checkFile(file);
        this.title = file.getName().split("\\.")[0];
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public File getFile() {
        return file;
    }

    private void checkFile(File file) throws InvalidFormatException{
        if(file == null)
            throw new InvalidFormatException("File not selected");
    }
}
