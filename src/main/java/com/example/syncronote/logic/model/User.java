package com.example.syncronote.logic.model;

import com.example.syncronote.logic.enums.UserTypes;

public class User {
    private String username;
    private String name;
    private String surname;
    private String email;
    //private ImageView ProfilePhoto;
    private final UserTypes userType;

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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserTypes getUserType() {
        return userType;
    }
}
