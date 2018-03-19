package fr.isen.trust.trustdroid.new_game;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import fr.isen.trust.trustdroid.model.ListPlayer;
import fr.isen.trust.trustdroid.model.Player;
import fr.isen.trust.trustdroid.util.CircleTransform;

import static fr.isen.trust.trustdroid.util.Find.findPlayer;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Pair<String, Player>> players = new ArrayList<>();
    private ListPlayer listPlayer;
    private Context context;

    public MyAdapter(ListPlayer listPlayer, Context context) {
        this.listPlayer = listPlayer;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return listPlayer.getSize();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.player, parent, false);


        for (int i = 0; i < listPlayer.getSize(); i++)
            players.add(Pair.create(listPlayer.getPlayer(i).getUsername(), listPlayer.getPlayer(i)));

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pair<String, Player> pair = players.get(position);
        holder.display(pair);
        holder.setOnClick(pair, this);
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

        public void setOnClick(final Pair<String, Player> pair, final MyAdapter adapter) {
            final Player player = pair.second;
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = findPlayer(listPlayer, player.getUsername());
                    listPlayer.removePlayer(index);
                    Toast.makeText(context, "Joueur supprimé !", Toast.LENGTH_SHORT).show();
                    adapter.notifyItemRemoved(index);

                    if (listPlayer.getSize() == 1) {
                        Intent refresh = new Intent(context, NewGameActivity.class);
                        refresh.putExtra("new list player", listPlayer);
                        context.startActivity(refresh);
                    }
                }
            });
        }

        public void display(Pair<String, Player> pair) {
            usernamePlayer.setText(pair.first);

            if (pair.second.getPhoto().equals("defaut")) {
                Picasso.with(context)
                        .load(R.mipmap.defaut)
                        .fit().centerCrop()
                        .transform(new CircleTransform())
                        .into(photoPlayer);
            }
            else
                photoPlayer.setImageURI(Uri.parse(pair.second.getPhoto()));
        }
    }
}