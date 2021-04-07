package com.ammar.mubadraproject;

public class Note {


    private String title;
    private String description;
    private int priority;
    private String image;



    public Note() {
        //empty constructor needed
    }

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}