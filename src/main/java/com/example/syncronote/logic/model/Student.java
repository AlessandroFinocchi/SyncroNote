package com.example.syncronote.logic.model;

import com.example.syncronote.logic.enums.UserTypes;

public class Student extends User{
    private String freshman;

    public Student(String username, String name, String surname, String email, UserTypes userType, String freshman) {
        super(username, name, surname, email, userType);
        this.freshman = freshman;
    }

    public String getFreshman() {
        return freshman;
    }
}
