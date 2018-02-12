package fr.isen.trust.trustdroid.player;

import java.util.ArrayList;
import java.util.List;

public class ListPlayer {

    private List<Player> listPlayer;

    public ListPlayer() {
        this.listPlayer = new ArrayList<>();
    }

    public int getSize() {
        return this.listPlayer.size();
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
