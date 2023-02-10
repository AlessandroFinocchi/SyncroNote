package com.example.syncronote.logic.utilities;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CLIPrinter {

    private CLIPrinter(){
        throw new IllegalStateException("Utility class");
    }

    public static void printMessage(String s){
        System.out.print(s);
    }
}
