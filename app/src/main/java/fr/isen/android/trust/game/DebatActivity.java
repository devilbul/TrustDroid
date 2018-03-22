package fr.isen.android.trust.game;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;

import fr.isen.android.trust.model.GameAdvancement;
import fr.isen.android.trust.util.CircleTransform;
import fr.isen.trust.trustdroid.R;

public class DebatActivity extends AppCompatActivity {

    private GameAdvancement gameAdvancement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        gameAdvancement = null;

        if (bundle != null)
            gameAdvancement = (GameAdvancement) bundle.getSerializable("game advancement");

        if (gameAdvancement != null) {
            if (gameAdvancement.getPlayers().getSize() == 4) {
                ImageView imageP1, imageP2, imageP3, imageP4;
                TextView userP1, userP2, userP3, userP4, scoreP1, scoreP2, scoreP3, scoreP4;
                setContentView(R.layout.activity_debat_4);
                imageP1 = findViewById(R.id.image_j1); imageP2 = findViewById(R.id.image_j2); imageP3 = findViewById(R.id.image_j3); imageP4 = findViewById(R.id.image_j4);
                userP1 = findViewById(R.id.username_j1); userP2 = findViewById(R.id.username_j2); userP3 = findViewById(R.id.username_j3); userP4 = findViewById(R.id.username_j4);
                scoreP1 = findViewById(R.id.score_j1); scoreP2 = findViewById(R.id.score_j2); scoreP3 = findViewById(R.id.score_j3); scoreP4 = findViewById(R.id.score_j4);

                if (gameAdvancement.getPlayers().getPlayer(0).getPhoto().equals("default")) Picasso.with(this).load(gameAdvancement.getPlayers().getPlayer(0).getPhoto()).fit().centerCrop().transform(new CircleTransform()).into(imageP1);
                else imageP1.setImageBitmap(getBitmapFromLocalPath(gameAdvancement.getPlayers().getPlayer(0).getPhoto(), 8));
                if (gameAdvancement.getPlayers().getPlayer(1).getPhoto().equals("default")) Picasso.with(this).load(gameAdvancement.getPlayers().getPlayer(1).getPhoto()).fit().centerCrop().transform(new CircleTransform()).into(imageP2);
                else imageP2.setImageBitmap(getBitmapFromLocalPath(gameAdvancement.getPlayers().getPlayer(1).getPhoto(), 8));
                if (gameAdvancement.getPlayers().getPlayer(2).getPhoto().equals("default")) Picasso.with(this).load(gameAdvancement.getPlayers().getPlayer(2).getPhoto()).fit().centerCrop().transform(new CircleTransform()).into(imageP3);
                else imageP3.setImageBitmap(getBitmapFromLocalPath(gameAdvancement.getPlayers().getPlayer(2).getPhoto(), 8));
                if (gameAdvancement.getPlayers().getPlayer(3).getPhoto().equals("default")) Picasso.with(this).load(gameAdvancement.getPlayers().getPlayer(3).getPhoto()).fit().centerCrop().transform(new CircleTransform()).into(imageP4);
                else imageP4.setImageBitmap(getBitmapFromLocalPath(gameAdvancement.getPlayers().getPlayer(3).getPhoto(), 8));

                userP1.setText(gameAdvancement.getPlayers().getPlayer(0).getUsername());
                userP2.setText(gameAdvancement.getPlayers().getPlayer(1).getUsername());
                userP3.setText(gameAdvancement.getPlayers().getPlayer(2).getUsername());
                userP4.setText(gameAdvancement.getPlayers().getPlayer(3).getUsername());
                scoreP1.setText(Arrays.toString(gameAdvancement.getPlayers().getPlayer(0).getScore()));
                scoreP2.setText(Arrays.toString(gameAdvancement.getPlayers().getPlayer(1).getScore()));
                scoreP3.setText(Arrays.toString(gameAdvancement.getPlayers().getPlayer(2).getScore()));
                scoreP4.setText(Arrays.toString(gameAdvancement.getPlayers().getPlayer(3).getScore()));
            } else if (gameAdvancement.getPlayers().getSize() == 5) {
                ImageView imageP1, imageP2, imageP3, imageP4;
                TextView userP1, userP2, userP3, userP4, scoreP1, scoreP2, scoreP3, scoreP4;
                setContentView(R.layout.activity_debat_4);

            } else if (gameAdvancement.getPlayers().getSize() == 6) {
                ImageView imageP1, imageP2, imageP3, imageP4;
                TextView userP1, userP2, userP3, userP4, scoreP1, scoreP2, scoreP3, scoreP4;
                setContentView(R.layout.activity_debat_4);

            } else if (gameAdvancement.getPlayers().getSize() == 7) {
                ImageView imageP1, imageP2, imageP3, imageP4;
                TextView userP1, userP2, userP3, userP4, scoreP1, scoreP2, scoreP3, scoreP4;
                setContentView(R.layout.activity_debat_4);

            } else if (gameAdvancement.getPlayers().getSize() == 8) {
                ImageView imageP1, imageP2, imageP3, imageP4;
                TextView userP1, userP2, userP3, userP4, scoreP1, scoreP2, scoreP3, scoreP4;
                setContentView(R.layout.activity_debat_4);

            }
        }
    }

    public void readyToFight(View v) {
        for (int i = 0; i < gameAdvancement.getPlayers().getSize(); i++)
            gameAdvancement.getPlayers().getPlayer(i).setReady(false);
        startActivity(new Intent(this, VchangeActivity.class).putExtra("game advancement", gameAdvancement));
        finish();
    }

    public static Bitmap getBitmapFromLocalPath(String path, int sampleSize) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = sampleSize;
            return BitmapFactory.decodeFile(path, options);
        } catch (Exception e) {
            Log.i("TAG", "Some exception " + e);
            return null;
        }
    }
}
