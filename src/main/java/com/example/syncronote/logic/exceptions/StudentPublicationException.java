package com.example.syncronote.logic.exceptions;

public class StudentPublicationException extends Exception{
    private static final long serialVersionUID = 1L;

    public StudentPublicationException(){
        super("Cannot send email");
    }

    public StudentPublicationException(Throwable cause){
        super(cause);
    }

    public StudentPublicationException(String message) {
        super(message);
    }
}
