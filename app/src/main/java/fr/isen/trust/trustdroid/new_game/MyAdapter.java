package fr.isen.trust.trustdroid.new_game;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fr.isen.trust.trustdroid.R;
import fr.isen.trust.trustdroid.player.Player;

import static fr.isen.trust.trustdroid.new_game.NewGameActivity.listPlayer;
import static fr.isen.trust.trustdroid.util.Find.findPlayer;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private int nb = listPlayer.getSize();
    private List<Pair<String, Player>> players = new ArrayList<>();
    private Context context;

    public MyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return nb;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.player, parent, false);

        for (int i = 0; i < nb; i++)
            players.add(Pair.create(listPlayer.getPlayer(i).getUsername(), listPlayer.getPlayer(i)));

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pair<String, Player> pair = players.get(position);
        holder.display(pair);
        holder.setOnClick(pair);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView photoPlayer;
        private TextView usernamePlayer;
        private ImageButton delete;

        public MyViewHolder(final View itemView) {
            super(itemView);
            photoPlayer = itemView.findViewById(R.id.photo_player);
            usernamePlayer = itemView.findViewById(R.id.username_player);
            delete = itemView.findViewById(R.id.delete_player);
        }

        public void setOnClick(final Pair<String, Player> pair) {
            final Player player = pair.second;
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listPlayer.removePlayer(findPlayer(listPlayer, player.getUsername()));
                    Toast.makeText(context, "Joueur supprimé !", Toast.LENGTH_SHORT).show();
                    Intent refresh = new Intent(context, NewGameActivity.class);
                    refresh.putExtra("new list player", listPlayer);
                    context.startActivity(refresh);
                }
            });
        }

        public void display(Pair<String, Player> pair) {
            usernamePlayer.setText(pair.first);
            Picasso.with(context)
                    .load(pair.second.getPhoto())
                    .fit().centerCrop()
                    .transform(new CircleTransform())
                    .into(photoPlayer);
        }
    }
}