package com.example.syncronote.logic.beans;

public class EmailBean {
    private final String student;
    private final String studentEmail;
    private final String noteName;
    private final String studentAnswer;
    private final String professorResponse;

    public EmailBean(String student, String studentEmail, String noteName, String studentAnswer, String professorResponse) {
        this.student = student;
        this.studentEmail = studentEmail;
        this.noteName = noteName;
        this.studentAnswer = studentAnswer;
        this.professorResponse = professorResponse;
    }


    public String getStudent() {
        return student;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public String getProfessorResponse() {
        return professorResponse;
    }
}
