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

    public synchronized static SessionSingleton getInstance(User user){
        if(SessionSingleton.instance == null){
            Logger logger = Logger.getAnonymousLogger();
            SessionSingleton.instance = new SessionSingleton(user);
            logger.log(Level.INFO, "Session singleton instance = " +
                    user.getUsername() + " " +
                    user.getName() + " " +
                    user.getSurname() + " " +
                    user.getUserType());
        }

        return instance;
    }

    public synchronized static SessionSingleton getInstance() throws UserNotSetException{
        if(SessionSingleton.instance == null){
            throw new UserNotSetException();
        }

        return instance;
    }
}
