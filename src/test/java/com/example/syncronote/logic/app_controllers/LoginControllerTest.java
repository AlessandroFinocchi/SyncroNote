package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.LoginCredentialsBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoginControllerTest {

    @Test
    void login() {
        LoginController loginController = new LoginController();
        String testUsername = "Alex";
        String testPassword = "1234";

        try{
            LoginCredentialsBean loginCredentialsBean = new LoginCredentialsBean(
                    testUsername,
                    testPassword
            );

        }
        catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }
}