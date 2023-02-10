package com.example.syncronote.logic.app_controllers;

import com.example.syncronote.logic.beans.CourseBean;
import com.example.syncronote.logic.dao.CourseDAO;
import com.example.syncronote.logic.exceptions.DAOException;
import com.example.syncronote.logic.exceptions.InvalidFormatException;
import com.example.syncronote.logic.model.Course;
import com.example.syncronote.logic.session.SessionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessorCoursesController extends AbsLoggedController {

    public void createCourse(CourseBean courseBean) throws DAOException {
        try{
            CourseDAO dao = new CourseDAO();
            dao.createCourse(
                    courseBean.getName(),
                    courseBean.getGrade(),
                    SessionManager.getInstance().getCurrentUser().getUsername(),
                    courseBean.getCategory()
            );
        }
        catch (SQLException e){
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }

    public void deleteCourse(CourseBean courseBean) throws DAOException {
        try{
            CourseDAO dao = new CourseDAO();
            dao.deleteCourse(
                    courseBean.getName(),
                    SessionManager.getInstance().getCurrentUser().getUsername()
            );
        }
        catch (SQLException e){
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }

    public List<CourseBean> getCurrentProfessorCourses() throws SQLException {
        List<Course> courseList = new CourseDAO().findProfessorCourse(
                SessionManager.getInstance().getCurrentUser().getUsername()
        );

        List<CourseBean> courseBeanList = new ArrayList<>();

        try {
            for (Course course : courseList) {
                CourseBean courseBean = new CourseBean(
                        course.getName(),
                        course.getGrade().getId(),
                        course.getCategory()
                );

                courseBeanList.add(courseBean);
            }
        }
        catch (InvalidFormatException e){
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }

        return courseBeanList;

    }
}
    
