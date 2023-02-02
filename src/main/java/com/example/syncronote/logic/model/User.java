package com.example.syncronote.logic.model;

import com.example.syncronote.logic.enums.UserTypes;

public class User {
    protected String username;
    protected String name;
    protected String surname;
    protected String email;
    protected final UserTypes userType;

    public User(String username, String name, String surname, String email, UserTypes userType) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public UserTypes getUserType() {
        return userType;
    }
}
