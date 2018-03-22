package fr.isen.android.trust.game;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.isen.android.trust.model.GameAdvancement;
import fr.isen.android.trust.util.CircleTransform;
import fr.isen.trust.trustdroid.R;

public class VchangeActivity extends AppCompatActivity {

    private GameAdvancement gameAdvancement;
    private TextView username;
    private ImageView photo;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vchange);
        Bundle bundle = getIntent().getExtras();
        username = findViewById(R.id.username);
        photo = findViewById(R.id.photo);
        gameAdvancement = null;

        if (bundle != null)
            gameAdvancement = ((GameAdvancement) bundle.getSerializable("game advancement"));

        if (gameAdvancement != null) {
            index = isAllAreReady();

            username.setText(gameAdvancement.getPlayers().getPlayer(index).getUsername());

            if (gameAdvancement.getPlayers().getPlayer(index).getPhoto().equals("default"))
                Picasso.with(this).load(gameAdvancement.getPlayers().getPlayer(index).getPhoto()).fit().centerCrop().transform(new CircleTransform()).into(photo);
            else
                photo.setImageURI(Uri.parse(gameAdvancement.getPlayers().getPlayer(index).getPhoto()));
        }
    }

    public int isAllAreReady() {
        for (int i = 0; i < gameAdvancement.getBattles().length; i++) {
            if (!gameAdvancement.getPlayers().getPlayer(gameAdvancement.getBattles()[i][0]).isReady())
                return gameAdvancement.getBattles()[i][0];
            if (!gameAdvancement.getPlayers().getPlayer(gameAdvancement.getBattles()[i][1]).isReady())
                return gameAdvancement.getBattles()[i][1];
        }

        return -1;
    }

    public void goVote(View v) {
        startActivity(new Intent(this, VoteActivity.class).putExtra("game advancement", gameAdvancement));
        finish();
    }
}
