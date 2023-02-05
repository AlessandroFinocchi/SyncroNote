package com.example.syncronote.logic.enums;

public enum VisibilityTypes {
    PRIVATE("Private"),
    PUBLIC("Public");

    private final String id;

    private VisibilityTypes(String id) {
        this.id = id;
    }

    public static VisibilityTypes fromString(String id) {
        for (VisibilityTypes type : values()) {
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
