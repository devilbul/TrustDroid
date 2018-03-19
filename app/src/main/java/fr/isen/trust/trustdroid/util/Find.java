package fr.isen.trust.trustdroid.util;

import fr.isen.trust.trustdroid.model.ListPlayer;
import fr.isen.trust.trustdroid.model.Player;

public class Find {

    public static boolean isUsernameNeverUsed(ListPlayer listPlayer, String username) {
        for (Player player : listPlayer.getListPlayer())
            if (player.getUsername().equals(username))
                return false;
        return true;
    }

    public static int findPlayer(ListPlayer listPlayer, String username) {
        for (int i = 0; i < listPlayer.getListPlayer().size(); i++)
            if (listPlayer.getPlayer(i).getUsername().equals(username))
                return i;
        return -1;
    }
}
