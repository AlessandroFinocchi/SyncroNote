package com.example.syncronote.logic.model;

import com.example.syncronote.logic.enums.UserTypes;

public class Professor extends User{
    private String university;

    public Professor(String username, String name, String surname, String email, UserTypes userType, String university) {
        super(username, name, surname, email, userType);
        this.university = university;
    }

    public String getUniversity() {
        return university;
    }
}
