package fr.isen.android.trust.model;

import java.io.Serializable;

public class Event implements Serializable {

    private String title;
    private String description;
    private int image;
    private int[] banEvent;

    public Event(String title, String description, int image, int[] banEvent) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.banEvent = banEvent;
    }

    public int[] getBanEvent() {
        return banEvent;
    }

    public void setBanEvent(int[] banEvent) {
        this.banEvent = banEvent;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.getTitle() + "\n" + this.description + "\n" + this.banEvent;
    }
}
