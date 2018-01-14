package com.example.bharathvemula.e_library;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by bharathvemula on 3/15/17.
 */

public class Tab2Fiction extends Fragment
{


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2fiction, container, false);

         final int REQUEST_RATE = 1;

        String[] listitems = {"Sherlockholmes" , "Gameofthrones","The Riddick", "Lord of the rings","Harry potter"};

        ListView listView = (ListView)rootView.findViewById( R.id.listfiction );
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_list_item_1,listitems
        );


        listView.setAdapter( listViewAdapter );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0)
                {
                    Intent Downloadpage_fiction = new Intent(getContext(), Downloadpage_fiction.class );


                    Downloadpage_fiction.putExtra("position",position);
                    startActivityForResult(Downloadpage_fiction, REQUEST_RATE);
                }
                else if(position ==1)
                {
                    Intent Downloadpage_fiction = new Intent(getContext(), Downloadpage_fiction.class );


                    Downloadpage_fiction.putExtra("position",position);
                    startActivityForResult(Downloadpage_fiction, REQUEST_RATE);
                }
                else if(position ==2)
                {
                    Intent Downloadpage_fiction = new Intent(getContext(), Downloadpage_fiction.class );


                    Downloadpage_fiction.putExtra("position",position);
                    startActivityForResult(Downloadpage_fiction, REQUEST_RATE);
                }
                else if(position ==3)
                {
                    Intent Downloadpage_fiction = new Intent(getContext(), Downloadpage_fiction.class );


                    Downloadpage_fiction.putExtra("position",position);
                    startActivityForResult(Downloadpage_fiction, REQUEST_RATE);
                }
                else if(position ==4)
                {
                    Intent Downloadpage_fiction = new Intent(getContext(), Downloadpage_fiction.class );


                    Downloadpage_fiction.putExtra("position",position);
                    startActivityForResult(Downloadpage_fiction, REQUEST_RATE);
                }
            }
        } );




        return rootView;
    }


}
