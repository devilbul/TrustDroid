package fr.isen.trust.trustdroid.player;

import android.net.Uri;

import java.io.Serializable;

public class Player implements Serializable {

    private String username;

    private String photo;

    public Player(String username, String photo) {
        this.username = username;
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }
}
