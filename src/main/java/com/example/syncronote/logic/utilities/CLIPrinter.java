package com.example.syncronote.logic.utilities;

import com.example.syncronote.logic.exceptions.NotImplementedException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.logging.Logger;

public class CLIPrinter {
    public static void PrintMessage(String s) throws NotImplementedException{
        Logger.getAnonymousLogger().log(Level.INFO, "Not implemented PrintMessage!");
        throw new NotImplementedException();
        //System.out.println(s);
    }
    public static void PrintError(String s) throws NotImplementedException{
        Logger.getAnonymousLogger().log(Level.INFO, "Not implemented PrintError!");
        throw new NotImplementedException();
        //System.err.println(s);
    }
}
