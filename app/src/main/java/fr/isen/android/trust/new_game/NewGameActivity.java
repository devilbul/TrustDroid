package fr.isen.android.trust.new_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

import fr.isen.android.trust.MainActivity;
import fr.isen.android.trust.game.GameActivity;
import fr.isen.android.trust.model.Event;
import fr.isen.android.trust.model.GameAdvancement;
import fr.isen.android.trust.model.ListPlayer;
import fr.isen.trust.trustdroid.R;

public class NewGameActivity extends AppCompatActivity {

    private final int maxPlayer = 8;
    private final int minPlayer = 3;
    private RecyclerView mRecyclerView;
    public ListPlayer listPlayer;
    public Button startGame;
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
            listPlayer = ((ListPlayer) bundle.getSerializable("new list player"));

        assert listPlayer != null;
        if (listPlayer.getSize() > minPlayer)
            startGame.setVisibility(View.VISIBLE);
        if (listPlayer.getSize() == maxPlayer)
            addPlayer.setVisibility(View.INVISIBLE);

        mRecyclerView.setAdapter(new MyAdapter(listPlayer, getApplicationContext()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void addPlayer(View v) {
        Intent addPlayer = new Intent(getApplicationContext(), AddPlayerActivity.class);
        addPlayer.putExtra("list player", listPlayer);
        startActivity(addPlayer);
        finish();
    }

    public void startGame(View v) {
        Intent startGame = new Intent(getApplicationContext(), GameActivity.class);
        GameAdvancement gameAdvancement = new GameAdvancement(listPlayer, 2, 0, 3, -1);
        initFirstRound(gameAdvancement);
        initEvents(gameAdvancement);
        startGame.putExtra("game advancement", gameAdvancement);
        startActivity(startGame);
        finish();
    }

    public void initFirstRound(GameAdvancement gameAdvancement) {
        int[][] rounds = new int[gameAdvancement.getPlayers().getSize()][2];
        int random = new Random().nextInt(gameAdvancement.getPlayers().getSize() + 1);

        for (int i = 0; i < gameAdvancement.getPlayers().getSize(); i++)
            if (i == random)
                rounds[i] = new int[]{i, 1};
            else
                rounds[i] = new int[]{i, 0};

        gameAdvancement.setRounds(rounds);
    }

    public void initEvents(GameAdvancement gameAdvancement) {
        ArrayList<Event> events = new ArrayList<>();

        events.add(new Event("Blanc comme neige", "Vous êtes un Innocent\nAccumulez 10 points et\nenfuyez vous avec les autres Innocents",
                R.mipmap.innocent, new int[]{}));
        events.add(new Event("Veste retournée", "Vous êtes maintenant un Traître\nAccumulez 10 points et enfuyez vous seul\nVous ne faites pas équipe avec l’(es) autre(s) traître(s)",
                R.mipmap.traitre, new int[]{1, 6, 9, 10}));
        events.add(new Event("Le calme avant la tempête", "Rien à signaler",
                R.mipmap.ras, new int[]{}));
        events.add(new Event("Regain", "Si vous avez moins de 9 points,\nVous en gagnez un",
                R.mipmap.regain, new int[]{3, 4}));
        events.add(new Event("Dépréciation", "Si vous avez plus de 2 points\nVous en perdez un",
                R.mipmap.down, new int[]{3, 4}));
        events.add(new Event("Double peine", "Pour ce tour\nLa trahison retire 2 points au joueur trahi\nCela affecte tous les joueurs mais libre à vous de partager ou non cette info",
                R.mipmap.doublepain, new int[]{5, 7, 11}));
        events.add(new Event("A ses crochets", "Vous ne pouvez vous enfuire que s’il/elle s’enfuit\nVous n’avez plus à accumuler de points\nVous perdez si les Innocents ou un autre traître s’enfuient\nSi ce joueur disparait, vous disparaissez avec",
                R.mipmap.link, new int[]{1, 6, 9, 10}));
        events.add(new Event("L’union fait la force", "Pour ce tour\nLe gain de point en cas d'alliance passe à 3\nCela affecte tous les joueurs mais libre à vous de partager ou non cette info",
                R.mipmap.union, new int[]{5, 7, 11}));
        events.add(new Event("A l'aveugle", "Vous ne pourrez pas choisir ce que vous allez voter lors de votre prochain face à face",
                R.mipmap.random, new int[]{8}));
        events.add(new Event("Folie Meurtrière", "Vous êtes maintenant un Assassin\nVous pourrez retirer un point à une joueur par tour\nVous gagnez en atteignant 15 points ou si tous les Innocents disparraissent",
                R.mipmap.assassin, new int[]{1, 6, 9, 11}));
        events.add(new Event("Elémentaire mon cher", "Vous êtes maintenant un Détective\nUne fois par tour, vous pourrez tenter de désigner le rôle d’un joueur\nSi vous avez raison, ce joueur perdra 3 points, si vous avez tort, c’est vous qui perdrez 3 points\nVous devez toujours vous enfuir avec les autres Innocents\nVous disparaissez si tous les Innocents disparaissent.",
                R.mipmap.sherlock, new int[]{1, 6, 9, 10}));
        events.add(new Event("Coopération forcée", "Pour ce tour\nEn cas de double trahison, les deux joueurs perdent 1 point\nCela affecte tous les joueurs mais libre à vous de partager ou non cette info",
                R.mipmap.uni, new int[]{5, 7, 11}));

        gameAdvancement.setEvents(events);
    }
}
