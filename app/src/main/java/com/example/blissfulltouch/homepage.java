package com.example.blissfulltouch;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;



import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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


public class homepage extends AppCompatActivity {
    private String userEmail;
    private MyDatabaseHelper dbHelper;
    ImageView homeicon;
    ImageView carticon;
    ImageView logouticon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        dbHelper = new MyDatabaseHelper(this);

        userEmail = getUserEmailFromDatabase();

        // Set up click listeners for service buttons
        Button serviceButton1 = findViewById(R.id.add);
        serviceButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceName = "manicure and pedicure";
                addServiceAndProceed(serviceName);
            }
        });

        Button serviceButton2 = findViewById(R.id.Add1);
        serviceButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceName = "moroccan_bath";
                addServiceAndProceed(serviceName);
            }
        });

        Button serviceButton3 = findViewById(R.id.add2);
        serviceButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceName = "hot stone massage";
                addServiceAndProceed(serviceName);
            }
        });

        // Set up click listener for reservation button
        Button editTextText = findViewById(R.id.editTextTextn);
        editTextText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the search page activity
                Intent intent = new Intent(homepage.this, searchpage.class);
                startActivity(intent);
            }
        });

        // Set up click listeners for icons
        homeicon = findViewById(R.id.homeicon);
        homeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle home icon click
                Intent i1 = new Intent(homepage.this, homepage.class);
                i1.putExtra("userEmail", userEmail);
                startActivity(i1);
            }
        });

        carticon = findViewById(R.id.carticon);
        carticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle cart icon click
                Intent i2 = new Intent(homepage.this, MyReservationsPage.class);
                i2.putExtra("userEmail", userEmail);
                startActivity(i2);
            }
        });

        logouticon = findViewById(R.id.logouticon);
        logouticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle logout icon click
                showLogoutDialog();
            }
        });
    }

    // Method to show logout confirmation dialog
    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Log out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Perform logout action
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    // Method to add service and proceed to registration form activity
    private void addServiceAndProceed(String serviceName) {
        boolean success = dbHelper.addService(serviceName);
        if (success) {
            Toast.makeText(homepage.this, "Service added successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(homepage.this, "Failed to add service!", Toast.LENGTH_SHORT).show();
        }
        // Proceed to registration form activity
        Intent intent = new Intent(homepage.this, registrationForm.class);
        intent.putExtra("userEmail", userEmail);
        intent.putExtra("service", serviceName);
        startActivity(intent);
    }

    // Method to retrieve user email from database
    private String getUserEmailFromDatabase() {
        return "user@example.com"; // Just a placeholder, replace it with actual logic to fetch user email
    }
}



    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Button addButton = findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the registration form activity
                Intent intent = new Intent(homepage.this, registrationForm.class);
                startActivity(intent);
            }
        });
    }
}*/

/*
public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }
}

 */