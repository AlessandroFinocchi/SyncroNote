package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.exceptions.InvalidFormatException;

import java.io.File;

public class PublicationProfessorBean extends PublicationStudentBean {
    private final int courseId;

    public PublicationProfessorBean(File file, boolean privateNote, int courseId) throws InvalidFormatException {
        super(file, privateNote);
        this.courseId = courseId;
    }

    public int getCourseId() {
        return courseId;
    }
}
