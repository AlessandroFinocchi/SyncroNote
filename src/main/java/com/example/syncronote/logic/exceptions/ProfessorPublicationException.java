package com.example.syncronote.logic.exceptions;

public class ProfessorPublicationException extends Exception{
    private static final long serialVersionUID = 1L;

    public ProfessorPublicationException(){
        super("Cannot send email");
    }

    public ProfessorPublicationException(Throwable cause){
        super(cause);
    }

    public ProfessorPublicationException(String message) {
        super(message);
    }
}
