package fr.isen.trust.trustdroid.new_game;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fr.isen.trust.trustdroid.R;
import fr.isen.trust.trustdroid.player.Player;

import static fr.isen.trust.trustdroid.new_game.NewGameActivity.listPlayer;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private int nb = listPlayer.getSize();
    private List<Pair<String, Player>> characters = new ArrayList<>();
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
            characters.add(Pair.create(listPlayer.getPlayer(i).getUsername(), listPlayer.getPlayer(i)));

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pair<String, Player> pair = characters.get(position);
        holder.display(pair);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView photoPlayer;
        private TextView usernamePlayer;

        public MyViewHolder(final View itemView) {
            super(itemView);
            photoPlayer = itemView.findViewById(R.id.photo_player);
            usernamePlayer = itemView.findViewById(R.id.username_player);
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