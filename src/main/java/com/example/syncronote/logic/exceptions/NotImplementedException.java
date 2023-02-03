package com.example.syncronote.logic.exceptions;

public class NotImplementedException extends Exception{
    private static final long serialVersionUID = 4L;

    public NotImplementedException(){
        super("This function was not implemented");
    }

    public NotImplementedException(Throwable cause){
        super(cause);
    }
}
