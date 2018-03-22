package fr.isen.android.trust.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fr.isen.android.trust.model.ListPlayer;
import fr.isen.trust.trustdroid.R;

public class GameActivity extends AppCompatActivity {

    private ListPlayer listPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle bundle = getIntent().getExtras();
        listPlayer = null;
        StringBuilder list = new StringBuilder();

        if (bundle != null)
            listPlayer = ((ListPlayer) getIntent().getExtras().getSerializable("list player"));

    }
}
