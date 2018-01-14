package com.example.bharathvemula.e_library;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class Aboutus extends AppCompatActivity
{

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

       /* MediaPlayer.create( Aboutus.this, R.raw.tweety05 );
        try {
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }

   /* public void onbuttonclick(View v)
    {
        if(v==findViewById( R.id.play ))
        {
            mp.start();
        }

        if(v==findViewById( R.id.pause ))
        {
            mp.start();
        }

        if(v==findViewById( R.id.reset ))
        {
            mp.seekTo(0);
        }

    }*/


}
