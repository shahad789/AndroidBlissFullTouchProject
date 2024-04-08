package com.example.blissfulltouch;

import static com.example.blissfulltouch.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import com.example.blissfulltouch.R;


import java.util.ArrayList;

public class searchpage extends AppCompatActivity {

    SearchView searchView;
    ListView myListView;

    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_searchpage);




        searchView = findViewById(id.searchView);
        myListView = findViewById(id.listview);

        arrayList = new ArrayList<>();
        arrayList.add("manicure and pedicur");
        arrayList.add("moroccan bath");
        arrayList.add("hot stone massage");
        arrayList.add("organ relaxing massage");
        arrayList.add("swedish massage");
        arrayList.add("balinese massage");
        arrayList.add("french manicure");
        arrayList.add("hand nail polish");


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        myListView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });




        }
    }



