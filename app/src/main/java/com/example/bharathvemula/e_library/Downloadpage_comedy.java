package com.example.bharathvemula.e_library;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import static android.R.attr.value;

public class Downloadpage_comedy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_downloadpage_comedy );
        Button logout;

        Button download_comedy = (Button)findViewById( R.id.download_comedy );

       final int booknumber = (getIntent().getIntExtra( "position" ,value)) ;

        RatingBar comedyrating = (RatingBar)findViewById(R.id.ratingbar_comedy);

        comedyrating.setRating(load());



        comedyrating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                save(rating);
            }
        });
        logout = (Button)findViewById( R.id.logout1);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Downloadpage_comedy.this,LoginActivity.class);
                startActivity(i);
            }
        });



        download_comedy.setOnClickListener( new View.OnClickListener() {
      @Override
      public void onClick(View v) {

          String servicestring = Context.DOWNLOAD_SERVICE;
          DownloadManager downloadmanager;
          downloadmanager = (DownloadManager)getApplicationContext().getSystemService( servicestring );



          //int booknumber = Integer.parseInt( getIntent().getStringExtra( "position" ) );


          if(booknumber == 0 )
          {

              Uri uri = Uri
                      .parse("http://153.91.191.175/Educations/android_tutorial.pdf"); //creates a new Uri object from a properly formated String .
              DownloadManager.Request request = new DownloadManager.Request(uri);
              downloadmanager.enqueue(request);


              Toast.makeText(getApplicationContext(), "Downloading...", Toast.LENGTH_LONG ).show();

          }
          else if (booknumber== 1)

          {

              Uri uri = Uri
                      .parse("http://192.168.0.31/Educations/Computer_Networkig.pdf");
              DownloadManager.Request request = new DownloadManager.Request(uri);
              downloadmanager.enqueue(request);

              Toast.makeText( getApplicationContext(), "Downloading...", Toast.LENGTH_SHORT ).show();

          }



          else if (booknumber==2)

          {
              Uri uri = Uri
                      .parse("http://192.168.0.31/Educations/database_fundamentals.pdf");

              DownloadManager.Request request = new DownloadManager.Request(uri);

              downloadmanager.enqueue(request);

              Toast.makeText( getApplicationContext(), "Downloading...", Toast.LENGTH_SHORT ).show();
          }


          else if (booknumber==3)

          {

              Uri uri = Uri
                      .parse("http://192.168.0.31/Educations/ios_tutorial.pdf");

              DownloadManager.Request request = new DownloadManager.Request(uri);

              downloadmanager.enqueue(request);

              Toast.makeText( getApplicationContext(), "Downloading...", Toast.LENGTH_SHORT ).show();

          }

          else if (booknumber==4)

          {
              Uri uri = Uri
                      .parse("http://192.168.0.31/Educations/java_tutorial.pdf");

              DownloadManager.Request request = new DownloadManager.Request(uri);

              downloadmanager.enqueue(request);

              Toast.makeText( getApplicationContext(), "Downloading...", Toast.LENGTH_SHORT ).show();


          }

      }


  } );




    }


//    int number = (getIntent().getIntExtra( "position" ,value));
    public void save(float f)
    {
        SharedPreferences sharedPreferences =getSharedPreferences("default",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


            editor.putFloat("book0",f);
            editor.commit();


        Toast.makeText(getApplicationContext(),"your rating"+ f,Toast.LENGTH_SHORT ).show();

    }

    public float load()
    {

            SharedPreferences sharedPreferences = getSharedPreferences("default",MODE_PRIVATE);
            float f= sharedPreferences.getFloat("book0", 1);
            return f;


    }



}
