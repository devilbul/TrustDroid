package fr.isen.trust.trustdroid.model;

import java.io.Serializable;

public class Player implements Serializable {

    private String username;
    private String photo;
    private int[] score;
    private String role;
    private int following;
    private String vote;
    private String buff;
    private String win;

    public Player() {
        this.username = "";
        this.photo = "";
    }

    public Player(String username, String photo) {
        this.username = username;
        this.photo = photo;
        this.score = new int[]{0, 3};
        this.role = "";
        this.following = -1;
        this.vote = "";
        this.buff = "";
        this.win = "";
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

    public int getOldScore() {
        return score[0];
    }

    public int getCurrentScore() {
        return score[1];
    }

    public int[] getScore() {
        return score;
    }

    public void udpateScore(int newScore) {
        this.score = new int[]{this.score[1], newScore};
    }

    public void setScore(int[] score) {
        this.score = score;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getBuff() {
        return buff;
    }

    public void setBuff(String buff) {
        this.buff = buff;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }
}
