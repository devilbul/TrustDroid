package fr.isen.trust.trustdroid.new_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fr.isen.trust.trustdroid.R;

public class AddPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
    }

    public void commitAddPlayer(View v) {
        startActivity(new Intent(getApplicationContext(), NewGameActivity.class));
    }
}
