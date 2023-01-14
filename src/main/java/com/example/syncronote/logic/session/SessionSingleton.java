package com.example.syncronote.logic.session;

import com.example.syncronote.logic.exceptions.UserNotSetException;
import com.example.syncronote.logic.model.User;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionSingleton {
    private static SessionSingleton instance = null;

    private User sessionUser;

    protected SessionSingleton(User user){
        this.sessionUser = user;
    }

    public static synchronized SessionSingleton getInstance(User user){
        if(SessionSingleton.instance == null){
            SessionSingleton.instance = new SessionSingleton(user);

            String message = String.format("Session singleton instance = %s %s %s %s",
                    user.getUsername(),
                    user.getName(),
                    user.getSurname(),
                    user.getUserType());
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
