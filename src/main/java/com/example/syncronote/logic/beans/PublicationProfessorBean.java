package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.exceptions.EmailSenderException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import com.example.syncronote.logic.patterns.PublicationProfessorSubject;
import com.example.syncronote.logic.utilities.EmailSenderBoundary;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PublicationProfessorBean extends PublicationProfessorSubject {
    private final int courseId;
    private String professor;
    private String professorEmail;
    private List<String> studentEmails;

    public PublicationProfessorBean(File file, boolean privateNote, String category, int courseId) throws InvalidFormatException, EmailSenderException {
        super(file, privateNote, category);
        this.courseId = courseId;
        this.studentEmails = new ArrayList<>();

        EmailSenderBoundary emailSender = new EmailSenderBoundary(this);

        this.attach(emailSender);
    }

    public int getCourseId() {
        return courseId;
    }

    public void notifyPublication(){
        super.notifyObservers();
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getProfessorEmail() {
        return professorEmail;
    }

    public void setProfessorEmail(String professorEmail) {
        this.professorEmail = professorEmail;
    }

    public List<String> getSubscribedEmails() {
        return studentEmails;
    }

    public void addSubscribedEmail(String email){
        studentEmails.add(email);
    }
}
