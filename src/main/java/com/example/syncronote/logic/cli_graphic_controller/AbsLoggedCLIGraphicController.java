package com.example.syncronote.logic.cli_graphic_controller;

import com.example.syncronote.logic.enums.app_controllers.AbsController;
import com.example.syncronote.logic.enums.UserTypes;

public abstract class AbsLoggedCLIGraphicController extends AbsCLIGraphicController{
    UserTypes userType;

    protected static final String COURSE_COMPONENT = "CourseComponent.fxml";
    protected static final String NOTE_COMPONENT = "NoteComponent.fxml";
    protected static final String NOTE_ACTION_CHOICE = "NotesActionChoice.fxml";
    protected static final String PROFESSOR_COURSES = "ProfessorCourses.fxml";
    protected static final String PROFESSOR_REVISION = "ProfessorRevision.fxml";
    protected static final String PROFESSOR_REVISION_COMPONENT = "ProfessorRevisionComponent.fxml";
    protected static final String PUBLICATION = "Publication.fxml";
    protected static final String STUDENT_REVISION = "StudentRevision.fxml";
    protected static final String STUDENT_REVISION_COMPONENT = "StudentRevisionComponent.fxml";
    protected static final String USER_NOTES = "UserNotes.fxml";

    protected AbsLoggedCLIGraphicController(){
        super();
        userType = AbsController.getCurrentUserType();
    }
}
