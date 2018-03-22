package fr.isen.android.trust.util;

import fr.isen.android.trust.model.ListPlayer;
import fr.isen.android.trust.model.Player;

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
