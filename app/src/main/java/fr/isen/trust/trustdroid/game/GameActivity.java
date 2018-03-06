package fr.isen.trust.trustdroid.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import fr.isen.trust.trustdroid.R;
import fr.isen.trust.trustdroid.player.ListPlayer;
import fr.isen.trust.trustdroid.player.Player;

public class GameActivity extends AppCompatActivity {

    private ListPlayer listPlayer;
    private TextView out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        out = findViewById(R.id.out);
        Bundle bundle = getIntent().getExtras();
        listPlayer = null;
        StringBuilder list = new StringBuilder();

        if (bundle != null)
            listPlayer = ((ListPlayer) getIntent().getExtras().getSerializable("list player"));

        if (listPlayer.getSize() > 0) {
            for (Player player : listPlayer.getListPlayer())
                list.append(player.getUsername()).append("\n");

            out.setText(list.toString());
        }
    }
}
