package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.LoginCredentialsBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

            assertEquals(loginController.login(loginCredentialsBean).getUsername(), testUsername);

        }
        catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }
}