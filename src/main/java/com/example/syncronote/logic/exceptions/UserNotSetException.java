package com.example.syncronote.logic.exceptions;

public class UserNotSetException extends Exception{
    private static final long serialVersionUID = 3L;

    public UserNotSetException(){
        super("The user hasn't been defined yet");
    }

    public UserNotSetException(Throwable cause){
        super(cause);
    }
}