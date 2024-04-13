package com.example.blissfulltouch;

import static com.example.blissfulltouch.R.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import com.example.blissfulltouch.R;


import java.util.ArrayList;

public class searchpage extends AppCompatActivity {

    SearchView searchView;
    ListView myListView;

    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    ImageView homeicon;
    ImageView carticon;
    ImageView logouticon;



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

        // Intent initialization and click listeners should be placed here
        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");

        homeicon = findViewById(R.id.homeicon);
        homeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(searchpage.this, homepage.class);
                i1.putExtra("userEmail", userEmail);
                startActivity(i1);
            }
        });

        carticon = findViewById(R.id.carticon);
        carticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Replace MyReservationsPage.class with your desired destination
                Intent i2 = new Intent(searchpage.this, MyReservationsPage.class);
                i2.putExtra("userEmail", userEmail);
                startActivity(i2);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Log out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", null);

        logouticon = findViewById(R.id.logouticon);
        logouticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.show();
            }
        });


    }
}