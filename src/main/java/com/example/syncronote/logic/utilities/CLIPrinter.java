package com.example.syncronote.logic.utilities;

public class CLIPrinter {

    private CLIPrinter(){
        throw new IllegalStateException("Utility class");
    }

    public static void printMessage(String s){
        System.out.print(s);
    }
}
