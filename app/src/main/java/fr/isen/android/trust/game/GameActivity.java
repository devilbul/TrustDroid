package fr.isen.android.trust.game;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import fr.isen.android.trust.model.GameAdvancement;
import fr.isen.android.trust.util.CircleTransform;
import fr.isen.trust.trustdroid.R;

public class GameActivity extends AppCompatActivity {

    private GameAdvancement gameAdvancement;
    private TextView username;
    private ImageView imageView;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle bundle = getIntent().getExtras();
        username = findViewById(R.id.username);
        imageView = findViewById(R.id.imageView);
        gameAdvancement = null;

        if (bundle != null)
            gameAdvancement = ((GameAdvancement) bundle.getSerializable("game advancement"));

        if (gameAdvancement != null) {
            index = isAllAreReady();
            if (index > -1) {
                username.setText(gameAdvancement.getPlayers().getPlayer(index).getUsername());
                if (gameAdvancement.getPlayers().getPlayer(index).getPhoto().equals("default"))
                    Picasso.with(this).load(gameAdvancement.getPlayers().getPlayer(index).getPhoto()).fit().centerCrop().transform(new CircleTransform()).into(imageView);
                else
                    imageView.setImageURI(Uri.parse(gameAdvancement.getPlayers().getPlayer(index).getPhoto()));
            } else {
                initBattles();
                startActivity(new Intent(this, DebatActivity.class).putExtra("game advancement", gameAdvancement));
                finish();
            }
        }
    }

    public int isAllAreReady() {
        for (int i = 0; i < gameAdvancement.getPlayers().getSize(); i++)
            if (!gameAdvancement.getPlayers().getPlayer(i).isReady())
                return i;

        return -1;
    }

    public void commitReady(View v) {
        startActivity(new Intent(this, EventActivity.class).putExtra("game advancement", gameAdvancement));
        finish();
    }

    public void initBattles() {
        ArrayList<Integer> buffer = new ArrayList<>();

        for (int i = 0; i < gameAdvancement.getPlayers().getSize(); i++) buffer.add(i);
        Collections.shuffle(buffer);

        int[][] battles;
        int[] duo;
        int i, j = 2;

        if (gameAdvancement.getPlayers().getSize() % 2 == 0)
            i = gameAdvancement.getPlayers().getSize() / 2;
        else i = (gameAdvancement.getPlayers().getSize() - 1) / 2;

        battles = new int[i][j];

        if (gameAdvancement.getPlayers().getSize() % 2 == 0) {
            for (int k = 0; k < battles.length; k += 2)
                battles[k] = new int[]{buffer.get(k), buffer.get(k + 1)};
        } else {
            while (Objects.equals(gameAdvancement.getPlayers().getPlayer(buffer.get(0)).getBuff(), "blind") || gameAdvancement.getPlayers().getPlayer(buffer.get(1)).getBuff().equals("buff")) {
                Collections.shuffle(buffer);
            }

            duo = new int[]{buffer.get(0), buffer.get(1)};
            battles[1] = new int[]{buffer.get(2), gameAdvancement.getID_DUO()};

            for (int k = 3; k < battles.length; k += 2)
                battles[k] = new int[]{buffer.get(k), buffer.get(k + 1)};

            gameAdvancement.setDuo(duo);
        }

        gameAdvancement.setBattles(battles);
    }
}
