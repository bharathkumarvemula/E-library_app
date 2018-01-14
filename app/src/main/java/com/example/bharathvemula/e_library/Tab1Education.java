package com.example.bharathvemula.e_library;

/**
 * Created by bharathvemula on 3/15/17.
 */
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;

public class Tab1Education extends Fragment {

    private static final int REQUEST_RATE = 1;

    public Tab1Education() {


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate( R.layout.tab1educational, container, false );


        String[] listitems = {"android_tutorial", "Computer_Networking", "Database_fundamentals", "Java_tutorial", "Ios_tutorial"};

        ListView listView = (ListView) rootView.findViewById( R.id.listeducation );
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, listitems
        );




        listView.setAdapter( listViewAdapter );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (position == 0) {



                    Intent Downloadpage = new Intent(getContext(), Downloadpage.class );


                   Downloadpage.putExtra("position",position);

                    startActivityForResult(Downloadpage, REQUEST_RATE);


                } else if (position == 1) {


                    Intent Downloadpage  = new Intent(getContext(),Downloadpage.class);

                    Downloadpage.putExtra("position",position);

                    startActivity(Downloadpage);


                } else if (position == 2) {

                    Intent Downloadpage  = new Intent(getContext(),Downloadpage.class);

                    Downloadpage.putExtra("position",position);

                    startActivity(Downloadpage);

                    }

                else if (position == 3)

                    {

                    Intent Downloadpage  = new Intent(getContext(),Downloadpage.class);

                    Downloadpage.putExtra("position",position);

                    startActivity(Downloadpage);


                    }
                else if (position == 4)
                    {

                    Intent Downloadpage  = new Intent(getContext(),Downloadpage.class);

                    Downloadpage.putExtra("position",position);

                    startActivity(Downloadpage);

                     }
            }
        } );

        return rootView;


    }


}
