package fr.isen.android.trust.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.isen.android.trust.model.GameAdvancement;
import fr.isen.trust.trustdroid.R;

public class EventActivity extends AppCompatActivity {

    private TextView event_title, event_description;
    private ImageView event_image;
    private GameAdvancement gameAdvancement;
    private int idEvent, index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Bundle bundle = getIntent().getExtras();
        event_title = findViewById(R.id.event_title);
        event_description = findViewById(R.id.event_description);
        event_image = findViewById(R.id.event_image);

        if (bundle != null)
        gameAdvancement = ((GameAdvancement) bundle.getSerializable("game advancement"));

        index = isAllAreReady();
        idEvent = findEventPlayer();

        if (idEvent > -1) {
            event_title.setText(gameAdvancement.getEvent(idEvent).getTitle());
            event_description.setText(gameAdvancement.getEvent(idEvent).getDescription());
            event_image.setImageResource(gameAdvancement.getEvent(idEvent).getImage());
        }
    }

    public void nextStep(View v) {
        gameAdvancement.getPlayers().getPlayer(index).setReady(true);
        startActivity(new Intent(this, GameActivity.class).putExtra("game advancement", gameAdvancement));
        finish();
    }

    public int findEventPlayer() {
        for (int i = 0; i < gameAdvancement.getRounds().length; i++)
            if (gameAdvancement.getRounds()[i][0] == index)
                return gameAdvancement.getRounds()[i][1];

        return -1;
    }

    public int isAllAreReady() {
        for (int i = 0; i < gameAdvancement.getPlayers().getSize(); i++)
            if (!gameAdvancement.getPlayers().getPlayer(i).isReady())
                return i;

        return -1;
    }
}
