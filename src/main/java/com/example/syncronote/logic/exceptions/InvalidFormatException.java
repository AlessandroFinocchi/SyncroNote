package com.example.syncronote.logic.exceptions;

public class InvalidFormatException extends Exception{
    private static final long serialVersionUID = 1L;

    public InvalidFormatException(){
        super("Fields are empty");
    }

    public InvalidFormatException(Throwable cause){
        super(cause);
    }

    public InvalidFormatException(String message) {
        super(message);
    }
}
