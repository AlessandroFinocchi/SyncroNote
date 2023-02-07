package com.example.syncronote.logic.model;

import com.example.syncronote.logic.enums.GradeTypes;

public class Category {
    private final String name;
    private final String macroArea;
    private final GradeTypes grade;

    public Category(String name, String macroArea, GradeTypes grade) {
        this.name = name;
        this.macroArea = macroArea;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getMacroArea() {
        return macroArea;
    }

    public GradeTypes getGrade() {
        return grade;
    }
}
