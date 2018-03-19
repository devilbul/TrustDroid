package fr.isen.trust.trustdroid.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import fr.isen.trust.trustdroid.R;
import fr.isen.trust.trustdroid.model.ListPlayer;
import fr.isen.trust.trustdroid.model.Player;

public class GameActivity extends AppCompatActivity {

    private ListPlayer listPlayer;
    private TextView out;
    private ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        out = findViewById(R.id.out);
        Bundle bundle = getIntent().getExtras();
        listPlayer = null;
        StringBuilder list = new StringBuilder();

        image1 = findViewById(R.id.imageView);

        if (bundle != null)
            listPlayer = ((ListPlayer) getIntent().getExtras().getSerializable("list player"));

        if (listPlayer.getSize() > 0) {
            for (Player player : listPlayer.getListPlayer())
                list.append(player.getUsername()).append("\n");

            out.setText(list.toString());
        }

    }

    public void test() {
        try {
            Bitmap image = getBitmapFromUri(this, Uri.parse(listPlayer.getPlayer(0).getPhoto()));

            BitmapFactory editedImage ;

            image1.setImageBitmap(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getBitmapFromUri(Context context, Uri uri)
            throws FileNotFoundException {
        final InputStream imageStream = context.getContentResolver()
                .openInputStream(uri);
        try {
            return BitmapFactory.decodeStream(imageStream);
        } finally {
            try {
                if (imageStream != null)
                    imageStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
