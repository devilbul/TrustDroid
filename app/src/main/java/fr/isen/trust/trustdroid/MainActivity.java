package fr.isen.trust.trustdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fr.isen.trust.trustdroid.new_game.NewGameActivity;
import fr.isen.trust.trustdroid.regle.RegleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayRegle(View v) {
        startActivity(new Intent(getApplicationContext(), RegleActivity.class));
    }

    public void createLobby(View v) {
        startActivity(new Intent(getApplicationContext(), NewGameActivity.class));
    }
}
