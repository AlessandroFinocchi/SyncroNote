package com.example.syncronote.logic.beans;

import java.util.List;

public class EmailBean {
    private List<String> studentEmails;
    private final String noteName;

    public EmailBean(String noteName,  List<String> emails) {
        studentEmails = emails;
        this.noteName = noteName;
    }

    public List<String> getStudentEmails() {
        return studentEmails;
    }

    public String getNoteName() {
        return noteName;
    }
}
