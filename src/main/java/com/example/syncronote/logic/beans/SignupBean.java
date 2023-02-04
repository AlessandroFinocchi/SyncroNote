package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.exceptions.InvalidFormatException;

public class SignupBean {
    private final String username;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final String role;
    private final String userTypeAttr;

    public SignupBean(String username, String name, String surname, String email, String password, String role, String userTypeAttr) throws InvalidFormatException{
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.userTypeAttr = userTypeAttr;
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

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getUserTypeAttr() {
        return userTypeAttr;
    }

    private void checkEmailFormat() throws InvalidFormatException{
        if(!email.matches("^.+@\\w+\\.\\w+$"))
            throw new InvalidFormatException("Invalid email format");
    }

    private void checkAttributesAreNotEmpty(String username, String name, String surname, String email, String password, String role, String userTypeAttr) throws InvalidFormatException{
        if(username.isEmpty() || name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || role.isEmpty() || userTypeAttr.isEmpty())
            throw new InvalidFormatException("Empty field");
    }
}
