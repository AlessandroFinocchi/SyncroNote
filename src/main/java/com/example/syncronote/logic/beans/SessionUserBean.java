package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.enums.UserTypes;

import java.util.List;

public class SessionUserBean {

    private final String currentUser;
    private final UserTypes type;
    private final List<String> loggedUsers;

    public String getUsername() {
        return currentUser;
    }

    public UserTypes getType() {
        return type;
    }

    public List<String> getLoggedUsers() {
        return loggedUsers;
    }

    public SessionUserBean(String currentUser, UserTypes type, List<String> loggedUsers) {
        this.currentUser = currentUser;
        this.type = type;
        this.loggedUsers = loggedUsers;
    }
}
