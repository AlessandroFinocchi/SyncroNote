package com.example.syncronote.logic.utilities;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CLIPrinter {

    private CLIPrinter(){
        throw new IllegalStateException("Utility class");
    }

    public static void printMessage(String s){
        //Run the out printLine of s
        Logger.getAnonymousLogger().log(Level.INFO, "Not implemented PrintMessage!");
        throw new UnsupportedOperationException();
    }
    public static void printError(String s){
        //Run the error printLine of s
        Logger.getAnonymousLogger().log(Level.INFO, "Not implemented PrintError!");
        throw new UnsupportedOperationException();
    }
}
