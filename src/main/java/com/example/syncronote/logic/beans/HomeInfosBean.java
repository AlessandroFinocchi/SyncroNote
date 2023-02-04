package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.enums.UserTypes;

public class HomeInfosBean {
    private final String userType;
    private final String name;

    public HomeInfosBean(UserTypes userType, String name, String surname) {
        this.userType = userType.getId().substring(0,1).toUpperCase() +
                userType.getId().substring(1).toLowerCase();
        this.name = name.substring(0,1).toUpperCase() +
                name.substring(1).toLowerCase() + " " +
                surname.substring(0,1).toUpperCase() +
                surname.substring(1).toLowerCase();
    }

    public String getUserType() {
        return userType;
    }

    public String getName() {
        return name;
    }
}
