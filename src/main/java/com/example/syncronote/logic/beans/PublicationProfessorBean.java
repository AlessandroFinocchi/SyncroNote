package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.exceptions.InvalidFormatException;

import java.io.File;

public class PublicationProfessorBean extends PublicationStudentBean {
    private final String course;

    public PublicationProfessorBean(File file, boolean privateNote, String course) throws InvalidFormatException {
        super(file, privateNote);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }
}
