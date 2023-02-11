package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.PublicationStudentBean;
import com.example.syncronote.logic.enums.UserTypes;
import com.example.syncronote.logic.enums.app_controllers.PublicationStudentController;
import com.example.syncronote.logic.model.User;
import com.example.syncronote.logic.session.SessionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class PublicationStudentControllerTest {

    @Test
    void publishNote() {
        PublicationStudentController controller = new PublicationStudentController();
        SessionManager sessionManager;
        try {
            sessionManager = SessionManager.getInstance();
            sessionManager.login( new User(
                            "Alex",
                            "Alessandro",
                            "Finocchi",
                            "a@a.it",
                            UserTypes.STUDENT)
                    );
            PublicationStudentBean bean = new PublicationStudentBean(
                    new File("C:\\Users\\aless\\IdeaProjects\\SyncroNote\\src\\main\\resources\\com\\example\\syncronote\\notes\\note3.pdf"),
                    true,
                    "Fun");
            controller.publishNote(bean);
        }
        catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}