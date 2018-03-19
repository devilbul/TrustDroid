package fr.isen.trust.trustdroid.new_game;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import fr.isen.trust.trustdroid.R;
import fr.isen.trust.trustdroid.model.ListPlayer;
import fr.isen.trust.trustdroid.model.Player;

import static fr.isen.trust.trustdroid.util.Find.isUsernameNeverUsed;

public class AddPlayerActivity extends AppCompatActivity {

    private ListPlayer listPlayer;
    private Player newPlayer;
    private EditText username;
    private ImageButton photo;
    private Intent commitAddPlayer;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private String fileName;
    private String mCurrentPhotoPath = null;
    private Uri uriPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        username = findViewById(R.id.username);
        photo = findViewById(R.id.photo);
        Bundle bundle = getIntent().getExtras();
        newPlayer = new Player();
        listPlayer = null;

        if (bundle != null)
            listPlayer = ((ListPlayer) getIntent().getExtras().getSerializable("list player"));

        fileName = "user" + listPlayer.getSize() + ".jpg";

        commitAddPlayer = new Intent(getApplicationContext(), NewGameActivity.class);
        commitAddPlayer.removeExtra("new player");
    }

    public void commitAddPlayer(View v) {
        String usernameResult = username.getText().toString();

        if (usernameResult.length() > 0) {
            if (isUsernameNeverUsed(listPlayer, usernameResult)) {
                newPlayer.setUsername(usernameResult);

                if (mCurrentPhotoPath == null)
                    newPlayer.setPhoto("defaut");
                else
                    newPlayer.setPhoto(mCurrentPhotoPath);

                listPlayer.addPlayer(newPlayer);
                Toast.makeText(getApplicationContext(), "Joueur ajouté !", Toast.LENGTH_SHORT).show();
                commitAddPlayer.putExtra("new list player", listPlayer);
                startActivity(commitAddPlayer);
                finish();
            } else
                Toast.makeText(getApplicationContext(), "Nom déjà utilisé.", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(getApplicationContext(), "Aucun nom saisi.", Toast.LENGTH_LONG).show();
    }

    public void setUserPhoto(View v) {
        final CharSequence[] items = {"Prendre une photo", "Image par défaut", "Annuler"};
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Ajouter une photo !");
        alertDialog.setItems(items,     new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (items[i].equals("Prendre une photo")) {
                    takePhoto();
                } else if (items[i].equals("Image par défaut")) {
                    Bitmap image = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.defaut);
                    Bitmap resizeImage = Bitmap.createScaledBitmap(image, 512, 512, false);
                    photo.setImageBitmap(resizeImage);
                    newPlayer.setPhoto("defaut");
                } else if (items[i].equals("Annuler")) {
                    dialog.dismiss();
                }
            }
        });

        alertDialog.show();
    }

    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String filePath = "/storage/emulated/0/DCIM/trust";
        File dir = new File(filePath);

        if (!dir.exists()) dir.mkdir();

        uriPhoto = Uri.withAppendedPath(FileProvider.getUriForFile(getApplicationContext(), getPackageName()+".fileprovider", dir), fileName);
        mCurrentPhotoPath = filePath + "/" + fileName;

        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriPhoto);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            try {
                Bitmap resizeImage = null;
                Bitmap image = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uriPhoto);

                if (image.getWidth() > 400) {
                    int newHeight = image.getHeight() * 400 / image.getWidth();
                    resizeImage = Bitmap.createScaledBitmap(image, 400, newHeight, false);
                }

                photo.setImageBitmap((resizeImage != null) ? resizeImage : image);
            } catch (IOException e) {
                Log.i("TAG", "Some exception " + e);
            }
        }
    }
}
