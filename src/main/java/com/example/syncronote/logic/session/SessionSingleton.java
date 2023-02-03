package com.example.syncronote.logic.session;

import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.UserNotSetException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionSingleton {
    private static SessionSingleton instance = null;

    protected String username;
    protected String name;
    protected String surname;
    protected String email;
    protected final UserTypes userType;

    protected SessionSingleton(String username, String name, String surname, String email, UserTypes userType) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.userType = userType;
    }

    public static synchronized SessionSingleton getInstance(String username, String name, String surname, String email, UserTypes userType){
        if(SessionSingleton.instance == null){
            SessionSingleton.instance = new SessionSingleton(username, name, surname, email, userType);

            String message = String.format("Session singleton instance = %s %s %s %s",
                    username,
                    name,
                    surname,
                    userType);
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.INFO, message);
        }

        return instance;
    }

    public static synchronized SessionSingleton getInstance() throws UserNotSetException{
        if(SessionSingleton.instance == null){
            throw new UserNotSetException();
        }

        return instance;
    }
}
