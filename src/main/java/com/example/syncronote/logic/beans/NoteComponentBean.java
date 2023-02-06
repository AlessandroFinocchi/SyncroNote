package com.example.syncronote.logic.beans;

import com.example.syncronote.logic.enums.VisibilityTypes;

public class NoteComponentBean {
    private final String title;
    private final String category;
    private final String description;
    private final String visibility;
    private final String filePath;

    public NoteComponentBean(String title, String category, String description, VisibilityTypes visibility, String filePath) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.visibility = visibility.getId();
        this.filePath = filePath;
    }


    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getFilePath() {
        return filePath;
    }
}
