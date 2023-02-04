package com.example.syncronote.logic.enums;

public enum UserTypes {
    STUDENT("Student"),
    PROFESSOR("Professor"),
    ADMIN("Admin");

    private final String id;

    private UserTypes(String id) {
        this.id = id;
    }

    public static UserTypes fromString(String id) {
        for (UserTypes type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }
}
