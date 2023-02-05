package com.example.syncronote.logic.model;

import com.example.syncronote.logic.enums.GradeTypes;

public class Course {
    private final int id;
    private final String name;
    private final GradeTypes grade;
    private final String professor;
    private final String category;

    public Course(int id, String name, GradeTypes grade, String professor, String category) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.professor = professor;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GradeTypes getGrade() {
        return grade;
    }

    public String getProfessor() {
        return professor;
    }

    public String getCategory() {
        return category;
    }

}
