package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.exceptions.InvalidFormatException;

public class StartNewRevisionBean {
    private final String note;
    private final String professor;
    private final String question;

    public StartNewRevisionBean(String note, String professor, String question)  throws InvalidFormatException{
        checkQuestion(question);
        this.note = note;
        this.professor = professor;
        this.question = question;
    }

    private void checkQuestion(String question) throws InvalidFormatException {
        if(question == null || question.isEmpty())
            throw new InvalidFormatException("Ask a question about your note to the professor");
    }

    public String getNote() {
        return note;
    }

    public String getProfessor() {
        return professor;
    }

    public String getQuestion() {
        return question;
    }
}
