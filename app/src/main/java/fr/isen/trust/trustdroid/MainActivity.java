package fr.isen.trust.trustdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fr.isen.trust.trustdroid.regle.RegleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        Intent goToRegle = new Intent(getApplicationContext(), RegleActivity.class);
        startActivity(goToRegle);
    }
}
