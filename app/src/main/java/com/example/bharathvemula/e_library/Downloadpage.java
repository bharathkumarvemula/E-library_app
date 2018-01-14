package com.example.bharathvemula.e_library;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Downloadpage extends AppCompatActivity {

    public static final String saveIt="saveKey";
    public static final String preference = "pref";
    Button submit,logout;
    RatingBar bookrating;
    int booknumber;
    SharedPreferences r1,r2,r3,r4,r5;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState)


        {


        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_downloadpage );


            bookrating = (RatingBar)findViewById(R.id.rb_ratingbar) ;

            bookrating.setRating(load());

    Button download = (Button)findViewById( R.id.download) ;
        int value = 0;
        booknumber = (getIntent().getIntExtra( "position" ,  value)) ;


                bookrating.setOnRatingBarChangeListener( new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
                    {

                        save(rating);

                    }
                } );






       logout = (Button)findViewById( R.id.logout);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent (Downloadpage.this,LoginActivity.class);
                    startActivity(i);
                }
            });







        download.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v)
         {

           //  userrating.setText( "rating is"+ myrating);
             String servicestring = Context.DOWNLOAD_SERVICE;
             DownloadManager downloadmanager;
             downloadmanager = (DownloadManager)getApplicationContext().getSystemService( servicestring );



             //int booknumber = Integer.parseInt( getIntent().getStringExtra( "position" ) );

             int value =0;

             booknumber = (getIntent().getIntExtra( "position" ,  value)) ;

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


    public void save(float f)
    {
        SharedPreferences sharedPreferences =getSharedPreferences("default",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("rating",f);
        editor.commit();
        Toast.makeText(getApplicationContext(),"your rating"+ f,Toast.LENGTH_SHORT ).show();

    }

    public float load()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("default",MODE_PRIVATE);
        float f= sharedPreferences.getFloat("rating", 1);
        return f;
    }



}
