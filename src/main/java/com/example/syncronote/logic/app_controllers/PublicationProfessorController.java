package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.CourseIdMapBean;
import com.example.syncronote.logic.beans.PublicationProfessorBean;
import com.example.syncronote.logic.beans.PublicationStudentBean;
import com.example.syncronote.logic.beans.SetupEmailSenderBean;
import com.example.syncronote.logic.dao.CourseDAO;
import com.example.syncronote.logic.dao.ProfessorDAO;
import com.example.syncronote.logic.dao.PublicationDAO;
import com.example.syncronote.logic.dao.StudentDAO;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.model.Course;
import com.example.syncronote.logic.model.Professor;
import com.example.syncronote.logic.model.Student;
import com.example.syncronote.logic.session.SessionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PublicationProfessorController extends PublicationStudentController {

    public List<CourseIdMapBean> getCourses() throws SQLException {
        SessionManager sessionManager = SessionManager.getInstance();
        List<Course> courseList = new CourseDAO().findProfessorCourse(
                sessionManager.getCurrentUser().getUsername()
        );

        List<CourseIdMapBean> courseIdMapBean = new ArrayList<>();

        for (Course course: courseList) {
            courseIdMapBean.add(new CourseIdMapBean(
                    course.getId(),
                    course.getName()
            ));
        }

        return courseIdMapBean;
    }

    @Override
    public void publishNote(PublicationStudentBean publicationProfessorBean) throws DAOException, SQLException {
        super.publishNote(publicationProfessorBean);

        new PublicationDAO().insertPublication(
                publicationProfessorBean.getTitle(),
                ((PublicationProfessorBean)publicationProfessorBean).getCourseId()
        );

        ((PublicationProfessorBean)publicationProfessorBean).notifyPublication();
    }

    public SetupEmailSenderBean getEmailInfos(CourseIdMapBean courseIdMapBean) throws DAOException {
        SessionManager sessionManager = SessionManager.getInstance();
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students;

        SetupEmailSenderBean setupBean = new SetupEmailSenderBean(
                sessionManager.getCurrentUser().getUsername(),
                sessionManager.getCurrentUser().getEmail()
        );

        try {
            students = studentDAO.getSubscribed(courseIdMapBean.getCourseId());
        } catch (SQLException e) {
            throw new DAOException("Database error: couldn't get subscribed to the course");

        }

        for (Student student: students) {
            setupBean.addEmail(student.getEmail());
        }

        return setupBean;
    }
}
