package com.example.bharathvemula.e_library;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class Share extends AppCompatActivity {

    VideoView vw;
    MediaController mc;
    String Videopath="android.resource://com.example.bharathvemula.e_library/"+R.raw.elibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_share );

           //access to video view by id


        vw= (VideoView)findViewById( R.id.video );

        //creating instance object for media
        mc= new MediaController( this );

        //set the seekbar from media controller to video view
        mc.setAnchorView( vw );

        //attach the media controller to Video view
        vw.setMediaController( mc );

        //set the source path
        vw.setVideoPath( Videopath );
        vw.start();



    }

}
