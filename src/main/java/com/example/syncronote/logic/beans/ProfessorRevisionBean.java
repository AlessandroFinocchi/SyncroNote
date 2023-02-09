package com.example.syncronote.logic.beans;

public class ProfessorRevisionBean {
    private final String note;
    private final String student;
    private final String category;
    private final String studentQuestion;
    private final String filePath;

    public ProfessorRevisionBean(String note, String student, String category, String studentQuestion, String filePath) {
        this.note = note;
        this.student = student;
        this.category = category;
        this.studentQuestion = studentQuestion;
        this.filePath = filePath;
    }

    public String getNote() {
        return note;
    }

    public String getStudent() {
        return student;
    }

    public String getCategory() {
        return category;
    }

    public String getStudentQuestion() {
        return studentQuestion;
    }

    public String getFilePath() {
        return filePath;
    }
}
