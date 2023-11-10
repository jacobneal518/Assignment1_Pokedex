package com.example.assignment130;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {


    ListView pokemonListView;
    Uri pokemonURI = PokemonContentProvider.CONTENT_URI;

    List<DataDisplay> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        //display data in fancy shmancy way
        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(pokemonURI, PokemonContentProvider.dbColumns, null, null, null);
        Log.i("OnCreate", "About to start while loop");
        while(cursor.moveToNext()){
            if(cursor.getColumnIndex(PokemonContentProvider.COLUMN_ID) != -1){
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(PokemonContentProvider.COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(PokemonContentProvider.COLUMN_NAME));
                @SuppressLint("Range") String number = cursor.getString(cursor.getColumnIndex(PokemonContentProvider.COLUMN_NATIONAL_NUMBER));
                @SuppressLint("Range") String gender = cursor.getString(cursor.getColumnIndex(PokemonContentProvider.COLUMN_GENDER));

                DataDisplay display = new DataDisplay(id, name, number, gender);
                data.add(display);
            }
        }

        ArrayAdapter<DataDisplay> adapter = new ArrayAdapter<DataDisplay>(this, android.R.layout.simple_list_item_1, data){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.row_layout, parent, false);
                }

                DataDisplay dataModel = getItem(position);

                // Populate your custom layout with data from YourDataModel
                TextView textView1 = convertView.findViewById(R.id.textView1);
                TextView textView2 = convertView.findViewById(R.id.textView2);

                TextView textView3 = convertView.findViewById(R.id.textView3);
                TextView textView4 = convertView.findViewById(R.id.textView4);
                // Find additional TextViews for additional columns

                textView1.setText(dataModel.getId());
                textView2.setText("Name: " + dataModel.getName());
                textView3.setText("National Number: " + dataModel.getNumber());
                textView4.setText("Gender: " + dataModel.getGender());
                // Set text for additional columns

                return convertView;
            }
        };

        pokemonListView = findViewById(R.id.pokemonListView);

        Log.i("OnCreate", "Set Adapter");
        pokemonListView.setAdapter(adapter);
    }




}