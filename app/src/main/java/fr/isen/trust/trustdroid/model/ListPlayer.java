package fr.isen.trust.trustdroid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListPlayer implements Serializable {

    private List<Player> listPlayer;

    public ListPlayer() {
        this.listPlayer = new ArrayList<>();
    }

    public int getSize() {
        return this.listPlayer.size();
    }

    public void removePlayer(int index) {
        this.listPlayer.remove(index);
    }

    public void addPlayer(Player player) {
        this.listPlayer.add(player);
    }

    public Player getPlayer(int index) {
        return this.listPlayer.get(index);
    }

    public List<Player> getListPlayer() {
        return listPlayer;
    }

    public void setListPlayer(List<Player> listPlayer) {
        this.listPlayer = listPlayer;
    }
}
