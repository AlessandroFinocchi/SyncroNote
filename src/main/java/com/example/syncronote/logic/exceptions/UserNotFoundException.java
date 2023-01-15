package com.example.syncronote.logic.exceptions;

public class UserNotFoundException extends Exception{
    private static final long serialVersionUID = 2L;

    public UserNotFoundException(){
        super("User not found in the database");
    }

    public UserNotFoundException(Throwable cause){
        super(cause);
    }

}
