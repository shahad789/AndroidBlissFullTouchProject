package com.example.blissfulltouch;
import android.database.Cursor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyReservationsPage extends AppCompatActivity {

    private MyDatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private ReservationAdapter adapter;
    private ImageView homeicon;
    private ImageView carticon;
    private ImageView logouticon;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreservationspage);

        // Initialize database helper
        databaseHelper = new MyDatabaseHelper(this);

        // Retrieve user email from intent extras
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("userEmail");

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.reservationsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve reservations cursor from the database
        Cursor cursor = databaseHelper.getReservationsByEmail(userEmail);

        // Set up adapter with the cursor
        adapter = new ReservationAdapter(cursor);
        recyclerView.setAdapter(adapter);

        // Handle click events for navigation icons
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
                // Stay on the current page (MyReservationsPage)
            }
        });


///////////////
// Delete button click listener
        Button deleteButton = findViewById(R.id.delete2);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show delete confirmation dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MyReservationsPage.this);
                builder.setTitle("Delete Reservation");
                builder.setMessage("Are you sure you want to delete this reservation?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Retrieve user email from Intent
                        // String userEmail = getIntent().getStringExtra("userEmail");
// Get the user email from the current position of the cursor
                        int userEmailIndex = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_USER_EMAIL_FK);
                        String userEmail = cursor.getString(userEmailIndex);
                        // Call deleteReservation method with user email
                        boolean deleted = databaseHelper.deleteReservation(userEmail);

                        if (deleted) {
                            // If deletion successful, reload the activity
                            Toast.makeText(MyReservationsPage.this, "Reservation deleted", Toast.LENGTH_SHORT).show();
                            Intent intent = getIntent();
                            finish(); // Finish the current activity
                            startActivity(intent); // Start the activity again
                        } else {
                            Toast.makeText(MyReservationsPage.this, "Failed to delete reservation", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });



//////////////////////////////////


        // Handle logout icon click
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

        // Handle click event for the "Edit" button
        Button editButton = findViewById(R.id.edit2);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the registrationEditForm activity
                Intent editIntent = new Intent(MyReservationsPage.this, registrationEditForm.class);
                editIntent.putExtra("userEmail", userEmail);
                startActivity(editIntent);
            }
        });
    }
}


/* this goooooooooooood 44
package com.example.blissfulltouch;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
// Edit button click listener
        Button editButton = findViewById(R.id.edit2);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the edit page (registrationEditForm.class)
                Intent editIntent = new Intent(MyReservationsPage.this, registrationEditForm.class);
                startActivity(editIntent);
            }
        });
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

        // Navigation icons click listeners
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




        // Logout icon click listener
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

// Placeholder for registrationEditForm class

*/

/*
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
}*/