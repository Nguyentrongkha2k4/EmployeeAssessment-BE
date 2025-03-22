package com.brainnotfound.employeeassessmentbe.models;

public class Identity {
    private String name;
    private boolean isHuman;
    private boolean isDev;

    // Constructor
    public Identity(String name, boolean isHuman, boolean isDev) {
        this.name = name;
        this.isHuman = isHuman;
        this.isDev = isDev;
    }

    // Getters (required for JSON serialization)
    public String getName() {
        return name;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public boolean isDev() {
        return isDev;
    }
}
