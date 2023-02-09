package com.example.syncronote.logic.model;

public class UnansweredRevisableNote {
    private final String title;
    private final String author;
    private final String category;
    private final String studentQuestion;
    private final String description;
    private final String filePath;

    public UnansweredRevisableNote(String title, String author, String category, String studentQuestion, String description, String filePath) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.studentQuestion = studentQuestion;
        this.description = description;
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getStudentQuestion() {
        return studentQuestion;
    }

    public String getDescription() {
        return description;
    }

    public String getFilePath() {
        return filePath;
    }
}
