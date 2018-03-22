package fr.isen.android.trust.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import fr.isen.trust.trustdroid.R;
import fr.isen.android.trust.model.ListPlayer;
import fr.isen.android.trust.model.Player;

public class EndGameActivity extends AppCompatActivity {

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

        /**===========================================================================================================================================*/
        /*Bitmap image = getBitmapFromLocalPath(listPlayer.getPlayer(0).getPhoto(), 4);

        Canvas barreau = new Canvas(image.copy(Bitmap.Config.ARGB_8888, true));
        Paint p = new Paint();
        p.setStrokeWidth(5);

        //barreau.drawBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.barreau), image.getWidth() / 2, image.getHeight() / 2, p);
        barreau.drawBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.barreau), new Matrix(), p);
        barreau.drawPicture(new Picture());*/

        /**===========================================================================================================================================*/

        Bitmap bmp = getBitmapFromLocalPath(listPlayer.getPlayer(0).getPhoto(), 4);
        Bitmap alteredBitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        Canvas canvas = new Canvas(alteredBitmap);

        Paint paint = new Paint();
        canvas.drawBitmap(bmp, new Matrix(), paint);

        Bitmap barreau = BitmapFactory.decodeResource(this.getResources(), R.mipmap.barreau);
        canvas.drawBitmap(Bitmap.createScaledBitmap(barreau, alteredBitmap.getWidth(), alteredBitmap.getHeight(), false), new Matrix(), paint);

        Bitmap assassin = BitmapFactory.decodeResource(this.getResources(), R.mipmap.assassin);
        canvas.drawBitmap(Bitmap.createScaledBitmap(assassin, assassin.getWidth()/2, assassin.getHeight()/2, false), alteredBitmap.getWidth() - 256, 0, paint);

        image1.setImageBitmap(alteredBitmap);
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
