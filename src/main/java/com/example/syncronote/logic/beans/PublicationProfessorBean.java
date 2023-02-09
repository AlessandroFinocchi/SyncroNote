package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.exceptions.EmailSenderException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import com.example.syncronote.logic.patterns.Subject;
import com.example.syncronote.logic.utilities.EmailSenderBoundary;

import java.io.File;
import java.util.List;

public class PublicationProfessorBean extends Subject {
    private final int courseId;

    public PublicationProfessorBean(File file, boolean privateNote, String category, int courseId,
                                    SetupEmailSenderBean setupBean) throws InvalidFormatException, EmailSenderException {
        super(file, privateNote, category);
        this.courseId = courseId;

        EmailBean emailBean = new EmailBean(getTitle(), setupBean.getStudentEmails());
        EmailSenderBoundary emailSender = new EmailSenderBoundary(
                setupBean.getProfessor(),
                setupBean.getProfessorEmail(),
                emailBean);

        this.attach(emailSender);
    }

    public int getCourseId() {
        return courseId;
    }

    public void notifyPublication(){
        super.notifyObservers();
    }
}
