package com.example.syncronote.logic.exceptions;

public class NoCoursesException extends Exception{
    private static final long serialVersionUID = 1L;

    public NoCoursesException(){
        super("Fields are empty");
    }

    public NoCoursesException(Throwable cause){
        super(cause);
    }

    public NoCoursesException(String message) {
        super(message);
    }
}
