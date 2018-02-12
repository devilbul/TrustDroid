package fr.isen.trust.trustdroid.new_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import fr.isen.trust.trustdroid.R;
import fr.isen.trust.trustdroid.player.ListPlayer;

public class NewGameActivity extends AppCompatActivity {

    private final int maxPlayer = 8;
    private final int minPlayer = 1;
    private RecyclerView mRecyclerView;
    public static ListPlayer listPlayer;
    private Button startGame;
    private Button addPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        startGame = findViewById(R.id.button_start);
        addPlayer = findViewById(R.id.button_add);
        startGame.setVisibility(View.INVISIBLE);
        listPlayer = new ListPlayer();
        mRecyclerView = findViewById(R.id.players);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
            listPlayer = ((ListPlayer) getIntent().getExtras().getSerializable("new list player"));
        if (listPlayer.getSize() > minPlayer)
            startGame.setVisibility(View.VISIBLE);
        if (listPlayer.getSize() == maxPlayer) addPlayer.setVisibility(View.INVISIBLE);

        mRecyclerView.setAdapter(new MyAdapter(getApplicationContext()));
    }

    public void addPlayer(View v) {
        Intent addPlayer = new Intent(getApplicationContext(), AddPlayerActivity.class);
        addPlayer.putExtra("list player", listPlayer);
        startActivity(addPlayer);
    }
}
