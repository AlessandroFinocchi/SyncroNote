package com.example.syncronote.logic.exceptions;

public class SessionUserException extends Exception{
    private static final long serialVersionUID = 3L;

    public SessionUserException(){
        super("The user hasn't been defined yet");
    }

    public SessionUserException(Throwable cause){
        super(cause);
    }

    public SessionUserException(String message) {
        super(message);
    }
}