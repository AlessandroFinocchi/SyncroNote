package com.example.syncronote.logic.beans;

import java.util.ArrayList;
import java.util.List;

public class SetupEmailSenderBean {
    private final String professor;
    private final String professorEmail;
    private List<String> studentEmails;

    public SetupEmailSenderBean(String professor, String professorEmail) {
        this.professor = professor;
        this.professorEmail = professorEmail;
        this.studentEmails = new ArrayList<>();
    }

    public String getProfessor() {
        return professor;
    }

    public String getProfessorEmail() {
        return professorEmail;
    }

    public List<String> getStudentEmails() {
        return studentEmails;
    }

    public void addEmail(String email){
        studentEmails.add(email);
    }
}
