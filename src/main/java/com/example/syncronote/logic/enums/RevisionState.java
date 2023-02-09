package com.example.syncronote.logic.enums;

public enum RevisionState {
    IN_REVISION("In revision"),
    REVISED("Revised"),
    COMPLETED("Completed");

    private final String id;

    private RevisionState(String id) {
        this.id = id;
    }

    public static RevisionState fromString(String id) {
        for (RevisionState type : values()) {
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
