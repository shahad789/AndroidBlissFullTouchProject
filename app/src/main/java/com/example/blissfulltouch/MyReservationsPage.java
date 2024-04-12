package com.example.blissfulltouch;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyReservationsPage extends AppCompatActivity {

    ImageView homeicon;
    ImageView carticon;
    ImageView logouticon;


    private MyDatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private ReservationAdapter adapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreservationspage);

        // Initialize database helper
        databaseHelper = new MyDatabaseHelper(this);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.reservationsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve reservations cursor from the database
        cursor = databaseHelper.getAllReservationsCursor();

        // Set up adapter with the cursor
        adapter = new ReservationAdapter(cursor);
        recyclerView.setAdapter(adapter);

        // Intent initialization and click listeners should be placed here
        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");

        homeicon = findViewById(R.id.homeicon);
        homeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MyReservationsPage.this, homepage.class);
                i1.putExtra("userEmail", userEmail);
                startActivity(i1);
            }
        });

        carticon = findViewById(R.id.carticon);
        carticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Replace MyReservationsPage.class with your desired destination
                Intent i2 = new Intent(MyReservationsPage.this, MyReservationsPage.class);
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