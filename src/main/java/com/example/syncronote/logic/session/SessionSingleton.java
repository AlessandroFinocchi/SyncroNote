package com.example.syncronote.logic.session;

import com.example.syncronote.logic.exceptions.UserNotSetException;
import com.example.syncronote.logic.model.User;

public class SessionSingleton {
    private static SessionSingleton instance = null;

    private User sessionUser;

    protected SessionSingleton(User user){
        this.sessionUser = user;
    }

    public synchronized static SessionSingleton getInstance(User user){
        if(SessionSingleton.instance == null){
            SessionSingleton.instance = new SessionSingleton(user);
            System.out.println("Session singleton instance = " +
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
