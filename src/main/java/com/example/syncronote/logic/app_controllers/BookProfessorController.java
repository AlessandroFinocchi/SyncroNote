package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.CourseMapBean;
import com.example.syncronote.logic.beans.PublicationStudentBean;
import com.example.syncronote.logic.dao.course_procedures.FindProfessorCourseProcedureDAO;
import com.example.syncronote.logic.dao.publication_procedures.InsertPublicationProcedureDAO;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.model.Course;
import com.example.syncronote.logic.session.SessionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookProfessorController extends BookStudentController{

    public void publishProfessorNote(PublicationStudentBean publicationStudentBean, CourseMapBean courseBean) throws DAOException, SQLException {
        super.publishStudentNote(publicationStudentBean);

        new InsertPublicationProcedureDAO().execute(
                publicationStudentBean.getTitle(),
                courseBean.getCourseId()
        );
    }

    public List<CourseMapBean> getCourses() throws DAOException, SQLException {
        SessionManager sessionManager = SessionManager.getInstance();
        List<Course> courseList = new FindProfessorCourseProcedureDAO().execute(
                sessionManager.getCurrentUser().getUsername()
        );

        List<CourseMapBean> courseMapBean = new ArrayList<>();

        for (Course course: courseList) {
            courseMapBean.add(new CourseMapBean(
                    course.getId(),
                    course.getName()
            ));
        }

        return courseMapBean;
    }
}
