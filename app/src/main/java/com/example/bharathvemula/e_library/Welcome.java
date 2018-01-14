package com.example.bharathvemula.e_library;

import android.*;
import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.bharathvemula.e_library.R.id.email;
//import static com.example.bharathvemula.e_library.R.id.etEmail;
import static com.example.bharathvemula.e_library.R.id.etemail;
import static com.example.bharathvemula.e_library.R.id.spinner;

public class Welcome  extends AppCompatActivity   implements AdapterView.OnItemSelectedListener {

    Uri path; //As the path can contains various datatypes we are using URI

    String genre;

    String url_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_welcome );

//Initializing the buttons and spinner
        Button browse = (Button) findViewById( R.id.bChoosefile );
        Button upload = (Button) findViewById( R.id.bupload );
        Spinner spinner = (Spinner) findViewById( R.id.spinner );


        Bundle extras = getIntent().getExtras();

           final String message = extras.getString( "e-mail" );


        browse.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.setType( "application/pdf" );  //Set the type of file

                intent.setAction( Intent.ACTION_GET_CONTENT ); //set the action to get the file

                startActivityForResult( Intent.createChooser( intent, "Select PDF" ), 1 );

            }


        }

        );



        //writing on click method for upload button
        upload.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(message.equals("admin@gmail.com"))
                {
                    Intent moderatorpage = new Intent(getApplicationContext(),moderator.class);
                    startActivity(moderatorpage);
                }


                else
                {
                    (new Upload( Welcome.this, path )).execute();
                }







            }
        } );


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this,
                R.array.Genre_array, android.R.layout.simple_spinner_item );
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        // Apply the adapter to the spinner
        spinner.setAdapter( adapter );
        spinner.setOnItemSelectedListener( this );


        //Asking for permission to read external storage


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ActivityCompat.checkSelfPermission( this, Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions( new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100 );

                return;
            }

        }



        Button bgenre = (Button) findViewById( R.id.bGenre );
        bgenre.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotogenre = new Intent( Welcome.this, Genre.class );

                startActivity( gotogenre );

            }
        } );


    }

    //On result we get the path where the file is saved


    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                path = result.getData();
                Toast.makeText( this, "File selected",Toast.LENGTH_LONG ).show();
            }
        }
    }


    //Creating a Async task as to use a new thread to stop overloading the main thread and to work in background

    class Upload extends AsyncTask<Void, Void, Void> {
        private ProgressDialog pd;
        private Context c;
        private Uri path;

        public Upload(Context c, Uri path) {
            this.c = c;
            this.path = path;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show( c, "Uploading", "Please Wait" );

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute( result );
            pd.dismiss();
            Toast.makeText( getApplicationContext(),"File Uploaded successfully",Toast.LENGTH_SHORT ).show();


        }

            //do in background method
        @Override
        protected Void doInBackground(Void... params)
        {
            if(genre.equalsIgnoreCase( "Education" ) )
            {
                 url_path = "http://192.168.0.19/savedata.php";
            }

            else if (genre.equalsIgnoreCase( "Comedy" ) )
            {
                url_path = "http://192.168.0.19/savecomedy.php";
            }

            else if (genre.equalsIgnoreCase( "Fiction" ) )
            {
                url_path = "http://192.168.0.19/savefiction.php";
            }
            HttpURLConnection conn = null;

            int maxBufferSize = 1024;
            try {

                URL url = new URL( url_path );
                conn = (HttpURLConnection) url.openConnection();        //Setting http connection
                conn.setDoOutput( true );
                conn.setUseCaches( false );
                conn.setDoInput( true );
                conn.setChunkedStreamingMode( 1024 );//Number of bytes transmitted
                conn.setRequestMethod( "POST" );                //Using post method to post data
                conn.setRequestProperty( "Connection", "Keep-Alive" );
                conn.setRequestProperty( "Content-Type", "multipart/form-data" ); //encoding the data of the file

                OutputStream outputStream = conn.getOutputStream();  //connection for output stream
                InputStream inputStream = c.getContentResolver().openInputStream( path );

                int bytesAvailable = inputStream.available();
                int bufferSize = Math.min( bytesAvailable, maxBufferSize );
                byte[] buffer = new byte[bufferSize];

                int bytesRead;
                while ((bytesRead = inputStream.read( buffer, 0, bufferSize )) != -1) {
                    outputStream.write( buffer, 0, bytesRead );
                }
                outputStream.flush();
                inputStream.close();

                BufferedReader reader = new BufferedReader( new InputStreamReader(
                        conn.getInputStream() ) ); //Reads the characters in the input stream
                String line;
                while ((line = reader.readLine()) != null) {
                    Log.i( "result", line );
                }
                reader.close();
                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();

                }
            }
            return null;
        }



    }

 //Dropdown menu item spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        genre = parent.getItemAtPosition( position ).toString();

       // genre = parent.getItemAtPosition( position ).toString();

       Toast.makeText( this, genre,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
