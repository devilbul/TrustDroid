package fr.isen.android.trust.game;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.isen.android.trust.MainActivity;
import fr.isen.android.trust.model.GameAdvancement;
import fr.isen.android.trust.util.CircleTransform;
import fr.isen.trust.trustdroid.R;

public class VoteActivity extends AppCompatActivity {

    private GameAdvancement gameAdvancement;
    private TextView username;
    private ImageView photo;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        Bundle bundle = getIntent().getExtras();
        gameAdvancement = null;
        username = findViewById(R.id.name_adversaire);
        photo = findViewById(R.id.image_adversaire);

        if (bundle != null)
            gameAdvancement = ((GameAdvancement) bundle.getSerializable("game advancement"));

        if (gameAdvancement != null) {
            index = isAllAreReady();

            username.setText(gameAdvancement.getPlayers().getPlayer(getOpponent()).getUsername());

            if (gameAdvancement.getPlayers().getPlayer(index).getPhoto().equals("default"))
                Picasso.with(this).load(gameAdvancement.getPlayers().getPlayer(index).getPhoto()).fit().centerCrop().transform(new CircleTransform()).into(photo);
            else
                photo.setImageURI(Uri.parse(gameAdvancement.getPlayers().getPlayer(getOpponent()).getPhoto()));
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

    public int getOpponent() {
        for (int i = 0; i < gameAdvancement.getBattles().length; i++)
            if (gameAdvancement.getBattles()[i][0] == index)
                return gameAdvancement.getBattles()[i][1];
            else if (gameAdvancement.getBattles()[i][1] == index)
                return gameAdvancement.getBattles()[i][0];

        return -1;
    }

    public boolean isRoundCompleted() {
        for (int i = 0; i < gameAdvancement.getPlayers().getSize(); i++)
            if (!gameAdvancement.getPlayers().getPlayer(i).isReady())
                return false;

        return true;
    }

    public void voteAlliance(View v) {
        gameAdvancement.getPlayers().getPlayer(index).setVote("A");
        gameAdvancement.getPlayers().getPlayer(index).setReady(true);
        if (!isRoundCompleted())
            startActivity(new Intent(this, VchangeActivity.class).putExtra("game advancement", gameAdvancement));
        else
            startActivity(new Intent(this, MainActivity.class).putExtra("game advancement", gameAdvancement));
        finish();
    }

    public void voteTrahison(View v) {
        gameAdvancement.getPlayers().getPlayer(index).setVote("B");
        gameAdvancement.getPlayers().getPlayer(index).setReady(true);
        if (!isRoundCompleted())
            startActivity(new Intent(this, VchangeActivity.class).putExtra("game advancement", gameAdvancement));
        else
            startActivity(new Intent(this, MainActivity.class).putExtra("game advancement", gameAdvancement));
        finish();
    }
}
