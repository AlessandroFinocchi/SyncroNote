package com.example.syncronote.logic.beans;

public class CourseMapBean {
    private final int courseId;
    private final String courseName;

    public CourseMapBean(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }
}
