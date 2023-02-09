package com.example.syncronote.logic.beans;

public class ResponseBean {
    private final String note;
    private final String answer;
    private final String response;

    public ResponseBean(String note, String answer, String response) {
        this.note = note;
        this.answer = answer;
        this.response = response;
    }

    public String getNote() {
        return note;
    }

    public String getResponse() {
        return response;
    }

    public String getAnswer() {
        return answer;
    }
}
