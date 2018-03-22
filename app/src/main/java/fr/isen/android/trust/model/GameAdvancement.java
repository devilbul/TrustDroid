package fr.isen.android.trust.model;

import java.io.Serializable;
import java.util.ArrayList;

public class GameAdvancement implements Serializable {

    private int[][] rounds;
    private int[][] battles;
    private int[] duo;
    private int ID_DUO = -1;
    private ListPlayer players;
    private ArrayList<Event> events;
    private int currentRound;
    private int[] currentBattles;
    private int allyReward;
    private int tie;
    private int betrayReward;
    private int betrayPenalty;
    private int goal = 10;

    public GameAdvancement(ListPlayer players, int allyReward, int tie, int betrayReward, int betrayPenalty) {
        this.players = players;
        this.allyReward = allyReward;
        this.tie = tie;
        this.betrayReward = betrayReward;
        this.betrayPenalty = betrayPenalty;
        this.rounds = new int[][]{};
        this.battles = new int[][]{};
        this.events = new ArrayList<>();
        this.currentBattles = new int[]{};
    }

    public int getBetrayPenalty() {
        return betrayPenalty;
    }

    public void setBetrayPenalty(int betrayPenalty) {
        this.betrayPenalty = betrayPenalty;
    }

    public int getBetrayReward() {
        return betrayReward;
    }

    public void setBetrayReward(int betrayReward) {
        this.betrayReward = betrayReward;
    }

    public int getTie() {
        return tie;
    }

    public void setTie(int tie) {
        this.tie = tie;
    }

    public int getAllyReward() {
        return allyReward;
    }

    public void setAllyReward(int allyReward) {
        this.allyReward = allyReward;
    }

    public int[] getCurrentBattles() {
        return currentBattles;
    }

    public void setCurrentBattles(int[] currentBattles) {
        this.currentBattles = currentBattles;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public Event getEvent(int index) {
        return events.get(index);
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public ListPlayer getPlayers() {
        return players;
    }

    public void setPlayers(ListPlayer players) {
        this.players = players;
    }

    public int[] getDuo() {
        return duo;
    }

    public void setDuo(int[] duo) {
        this.duo = duo;
    }

    public int getID_DUO() {
        return ID_DUO;
    }

    public int getGoal() {
        return goal;
    }

    public int[][] getBattles() {
        return battles;
    }

    public void setBattles(int[][] battles) {
        this.battles = battles;
    }

    public int[][] getRounds() {
        return rounds;
    }

    public void setRounds(int[][] rounds) {
        this.rounds = rounds;
    }
}
