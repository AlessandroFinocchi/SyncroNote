package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.HomeInfosBean;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.exceptions.SessionUserException;
import com.example.syncronote.logic.model.User;
import com.example.syncronote.logic.session.SessionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    void getHomepageInfos() {
        SessionManager sessionManager = SessionManager.getInstance();
        HomeController homeController = new HomeController();
        HomeInfosBean bean;

        try {
            sessionManager.login(new User(
                    "Alex",
                    "Alessandro",
                    "Finocchi",
                    "a.a@a.it",
                    UserTypes.STUDENT));

            bean = homeController.getHomepageInfos();

            Assertions.assertAll(
                    ()->assertEquals("Alessandro Finocchi", bean.getName()),
                    ()->assertEquals(UserTypes.STUDENT.getId(), bean.getUserType())
            );

        } catch (SessionUserException e) {
            Assertions.fail("SessionManager login failed");
        }
    }
}