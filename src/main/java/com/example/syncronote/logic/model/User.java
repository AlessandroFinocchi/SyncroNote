package com.example.syncronote.logic.model;

import com.example.syncronote.logic.enums.UserTypes;

public class User {
    private String Nickname;
    private String Name;
    private String Surname;
    private String Email;
    //private ImageView ProfilePhoto;
    private final UserTypes userType;

    public User(String nickname, String name, String surname, String email, UserTypes userType) {
        Nickname = nickname;
        Name = name;
        Surname = surname;
        Email = email;
        this.userType = userType;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public UserTypes getUserType() {
        return userType;
    }
}
