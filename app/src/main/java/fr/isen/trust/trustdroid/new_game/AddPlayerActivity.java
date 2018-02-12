package fr.isen.trust.trustdroid.new_game;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.isen.trust.trustdroid.R;
import fr.isen.trust.trustdroid.player.ListPlayer;
import fr.isen.trust.trustdroid.player.Player;

import static fr.isen.trust.trustdroid.util.Find.isUsernameNeverUsed;

public class AddPlayerActivity extends AppCompatActivity {

    private ListPlayer listPlayer;
    private Player newPlayer;
    private EditText username;
    private Intent commitAddPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        Bundle bundle = getIntent().getExtras();
        newPlayer = new Player();
        listPlayer = null;

        if (bundle != null)
            listPlayer = ((ListPlayer) getIntent().getExtras().getSerializable("list player"));

        username = findViewById(R.id.username);
        commitAddPlayer = new Intent(getApplicationContext(), NewGameActivity.class);
        commitAddPlayer.removeExtra("new player");
    }

    public void commitAddPlayer(View v) {
        String usernameResult = username.getText().toString();

        if (usernameResult.length() > 0) {
            if (isUsernameNeverUsed(listPlayer, usernameResult)) {
                newPlayer.setUsername(usernameResult);
                newPlayer.setPhoto("http://www.sefairepayer.com/images/profils-debiteur/profil-irreductible.png");
                listPlayer.addPlayer(newPlayer);
                Toast.makeText(getApplicationContext(), "Joueur ajouté !", Toast.LENGTH_SHORT).show();
                commitAddPlayer.putExtra("new list player", listPlayer);
                startActivity(commitAddPlayer);
            }
            else
                Toast.makeText(getApplicationContext(), "Nom déjà utilisé.", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(getApplicationContext(), "Aucun nom saisi.", Toast.LENGTH_LONG).show();
    }
}
