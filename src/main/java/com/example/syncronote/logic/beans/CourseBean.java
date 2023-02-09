package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.enums.GradeTypes;
import com.example.syncronote.logic.exceptions.InvalidFormatException;

public class CourseBean {
    private final String name;
    private final GradeTypes grade;
    private final String category;

    public CourseBean(String name, String grade, String category) throws InvalidFormatException {
        checkName(name);
        checkGrade(grade);
        checkCategory(category);
        this.name = name;
        this.grade = GradeTypes.fromString(grade);
        this.category = category;
    }

    public CourseBean(String name){
        this.name = name;
        this.grade = null;
        this.category = null;
    }

    private void checkName(String name) throws InvalidFormatException {
        if(name == null || name.isEmpty())
            throw new InvalidFormatException("Course name cannot be empty");
    }
    private void checkGrade(String grade) throws InvalidFormatException {
        if(grade == null)
            throw new InvalidFormatException("Please select a grade");
    }
    private void checkCategory(String category) throws InvalidFormatException {
        if(category == null)
            throw new InvalidFormatException("Please select a category");
    }

    public String getName() {
        return name;
    }

    public GradeTypes getGrade() {
        return grade;
    }

    public String getCategory() {
        return category;
    }

}
