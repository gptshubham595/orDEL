package com.shubham.ordel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.surprise);
        mp.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mp.release();
                Intent i=new Intent(getApplicationContext(), Pizza.class);
                startActivity(i);
            }
        }, 3000);

    }
}
