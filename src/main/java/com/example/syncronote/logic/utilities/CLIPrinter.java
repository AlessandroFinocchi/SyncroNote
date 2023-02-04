package com.example.syncronote.logic.utilities;

import com.example.syncronote.logic.exceptions.NotImplementedException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CLIPrinter {

    private CLIPrinter(){
        throw new IllegalStateException("Utility class");
    }

    public static void printMessage(String s) throws NotImplementedException{
        //System.out.println(s);
        Logger.getAnonymousLogger().log(Level.INFO, "Not implemented PrintMessage!");
        throw new NotImplementedException();
    }
    public static void printError(String s) throws NotImplementedException{
        //System.err.println(s);
        Logger.getAnonymousLogger().log(Level.INFO, "Not implemented PrintError!");
        throw new NotImplementedException();
    }
}
