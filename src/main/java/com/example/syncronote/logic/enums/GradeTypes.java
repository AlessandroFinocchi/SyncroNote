package com.example.syncronote.logic.enums;

public enum GradeTypes {STUDENT("Student"),
    PRIMARY("Professor"),
    SECONDARY("Admin"),
    UNIVERSITY("Admin");

    private final String id;

    private GradeTypes(String id) {
        this.id = id;
    }

    public static GradeTypes fromString(String id) {
        for (GradeTypes type : values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }
}
