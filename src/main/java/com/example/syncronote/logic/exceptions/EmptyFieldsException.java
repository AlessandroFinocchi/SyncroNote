package com.example.syncronote.logic.exceptions;

public class EmptyFieldsException extends Exception{
    private static final long serialVersionUID = 1L;

    public EmptyFieldsException(){
        super("Fields are empty");
    }

    public EmptyFieldsException(Throwable cause){
        super(cause);
    }
}
