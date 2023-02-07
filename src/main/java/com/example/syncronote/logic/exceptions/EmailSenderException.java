package com.example.syncronote.logic.exceptions;

public class EmailSenderException extends Exception{
    private static final long serialVersionUID = 1L;

    public EmailSenderException(){
        super("Cannot send email");
    }

    public EmailSenderException(Throwable cause){
        super(cause);
    }

    public EmailSenderException(String message) {
        super(message);
    }
}