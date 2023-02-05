package com.example.syncronote.logic.session;

import com.example.syncronote.logic.exceptions.SessionUserException;
import com.example.syncronote.logic.model.User;
import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private static SessionManager instance = null;

    protected List<User> loggedUsers;

    protected User currentUser;

    public User getCurrentUser(){
        return currentUser;
    }

    public List<User> getLoggedUsers(){
        return loggedUsers;
    }

    protected SessionManager() {
        loggedUsers = new ArrayList<>();
        currentUser = null;
    }

    public static synchronized SessionManager getInstance(){
        if(SessionManager.instance == null)
            SessionManager.instance = new SessionManager();

        return instance;
    }

    public synchronized void login(User user) throws SessionUserException{
        try{
            for (User u: loggedUsers) {
                if (u.getUsername().equals(user.getUsername())){
                    throw new SessionUserException("User was already logged in");
                }
            }
            loggedUsers.add(user);
        }
        finally {
            currentUser = user;
        }
    }

    public synchronized void logout(){
        loggedUsers.remove(currentUser);
        currentUser = null;
    }

    public synchronized User changeCurrentUser(String username){
        for (User u: loggedUsers) {
            if(u.getUsername().equals(username)){
                currentUser = u;
                return u;
            }
        }
        return null;
    }
}
