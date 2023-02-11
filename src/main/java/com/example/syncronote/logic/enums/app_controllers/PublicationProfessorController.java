package com.example.syncronote.logic.enums.app_controllers;

import com.example.syncronote.logic.beans.CourseIdMapBean;
import com.example.syncronote.logic.beans.PublicationProfessorBean;
import com.example.syncronote.logic.beans.PublicationStudentBean;
import com.example.syncronote.logic.dao.CourseDAO;
import com.example.syncronote.logic.dao.PublicationDAO;
import com.example.syncronote.logic.dao.StudentDAO;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.model.Course;
import com.example.syncronote.logic.model.Student;
import com.example.syncronote.logic.session.SessionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublicationProfessorController extends PublicationStudentController {

    @Override
    public void publishNote(PublicationStudentBean publicationProfessorBean) throws DAOException, SQLException {
        SessionManager sessionManager = SessionManager.getInstance();
        List<Student> students = getEmailInfos(((PublicationProfessorBean)publicationProfessorBean).getCourseId());

        ((PublicationProfessorBean)publicationProfessorBean).setProfessor(sessionManager.getCurrentUser().getUsername());
        ((PublicationProfessorBean)publicationProfessorBean).setProfessorEmail(sessionManager.getCurrentUser().getEmail());

        for (Student student: students){
            ((PublicationProfessorBean)publicationProfessorBean).addSubscribedEmail(student.getEmail());
        }

        super.publishNote(publicationProfessorBean);

        new PublicationDAO().insertPublication(
                publicationProfessorBean.getTitle(),
                ((PublicationProfessorBean)publicationProfessorBean).getCourseId()
        );

        ((PublicationProfessorBean)publicationProfessorBean).notifyPublication();
    }

    private List<Student> getEmailInfos(int courseId) throws DAOException {
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students;

        try {
            students = studentDAO.getSubscribed(courseId);
            return students;
        } catch (SQLException e) {
            throw new DAOException("Database error: couldn't get subscribed to the course");
        }
    }

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
}
