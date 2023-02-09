package com.example.syncronote.logic.model;

public class Revision {
    private final String title;
    private final String question;
    private final String response;

    public Revision(String title, String question, String response) {
        this.title = title;
        this.question = question;
        this.response = response;
    }

    public String getTitle() {
        return title;
    }

    public String getQuestion() {
        return question;
    }

    public String getResponse() {
        return response;
    }
}
