package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.exceptions.EmptyFieldsException;

public class LoginCredentialsBean {
    private final String username;
    private final String password;

    public LoginCredentialsBean(String username, String password) throws EmptyFieldsException {
        checkUsername(username);
        checkPassword(password);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private void checkUsername(String username) throws EmptyFieldsException{
        if(username.isEmpty() || username.isBlank())
            throw new EmptyFieldsException();
    }

    private void checkPassword(String password) throws EmptyFieldsException{
        if(password.isEmpty() || password.isBlank() || password.length() > 8)
            throw new EmptyFieldsException();
    }
}
