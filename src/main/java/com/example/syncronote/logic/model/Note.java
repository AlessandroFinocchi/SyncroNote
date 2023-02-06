package com.example.syncronote.logic.model;

import com.example.syncronote.logic.enums.VisibilityTypes;

public class Note {
    private final String title;
    private final String author;
    private final String description;
    private final String filePath;
    private final VisibilityTypes visibility;
    private final String category;

    public Note(String title, String author, String description, String filePath, VisibilityTypes visibility, String category) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.filePath = filePath;
        this.visibility = visibility;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getFilePath() {
        return filePath;
    }

    public VisibilityTypes getVisibility() {
        return visibility;
    }

    public String getCategory() {
        return category;
    }
}
