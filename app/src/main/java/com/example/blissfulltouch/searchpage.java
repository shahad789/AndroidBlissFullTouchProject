package com.example.blissfulltouch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class searchpage extends AppCompatActivity {

    private EditText editTextSearch;
    private Button buttonSearch;
    private MyDatabaseHelper dbHelper;

    private ImageView homeicon;
    private ImageView carticon;
    private ImageView logouticon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);

        dbHelper = new MyDatabaseHelper(this); // Initialize DbHelper

        // Insert sample data into the database
        insertSampleData();

        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editTextSearch.getText().toString().trim();
                search(query);
            }
        });


        // Intent initialization and click listeners
        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");

        // Home icon click listener
        homeicon = findViewById(R.id.homeicon);
        homeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(searchpage.this, homepage.class);
                i1.putExtra("userEmail", userEmail);
                startActivity(i1);
            }
        });

        // Cart icon click listener
        carticon = findViewById(R.id.carticon);
        carticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(searchpage.this, MyReservationsPage.class); // Replace MyReservationsPage.class with your desired destination
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

    private void search(String query) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Define the columns you want to retrieve
            String[] projection = {MyDatabaseHelper.SPA_SERVICES_NAME, MyDatabaseHelper.SPA_SERVICES_DESCRIPTION};

            // Define the selection criteria
            String selection = MyDatabaseHelper.SPA_SERVICES_NAME + " LIKE ? OR " + MyDatabaseHelper.SPA_SERVICES_DESCRIPTION + " LIKE ?";

            // Define the selection arguments
            String[] selectionArgs = {"%" + query + "%", "%" + query + "%"};

            // Query the database
            cursor = db.query(
                    MyDatabaseHelper.TABLE_SPA_SERVICES,  // Table name
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            // Process the results
            List<String> results = new ArrayList<>();
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.SPA_SERVICES_NAME));
                    @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.SPA_SERVICES_DESCRIPTION));

                    // Add the result to the list
                    results.add("Name: " + name + "\nDescription: " + description);
                } while (cursor.moveToNext());
            }

            // Display the results in a ListView
            ListView listView = findViewById(R.id.listview);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, results);
            listView.setAdapter(adapter);

        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
        } finally {
            // Close the cursor and database connection
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    private void insertSampleData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Insert "Manicure" service if it doesn't already exist
        if (!MyDatabaseHelper.isServiceExists(db, "Manicure and pedicure")) {
            ContentValues values = new ContentValues();
            values.put(MyDatabaseHelper.SPA_SERVICES_NAME, "Manicure and pedicure");
            values.put(MyDatabaseHelper.SPA_SERVICES_DESCRIPTION, "Nail care for hands");
            db.insert(MyDatabaseHelper.TABLE_SPA_SERVICES, null, values);
        }

        // Insert "Facial" service if it doesn't already exist
        if (!MyDatabaseHelper.isServiceExists(db, "Facial")) {
            ContentValues values2 = new ContentValues();
            values2.put(MyDatabaseHelper.SPA_SERVICES_NAME, "Facial");
            values2.put(MyDatabaseHelper.SPA_SERVICES_DESCRIPTION, "Deep cleansing facial");
            db.insert(MyDatabaseHelper.TABLE_SPA_SERVICES, null, values2);
        }

        // Insert "Moroccan Bath" service if it doesn't already exist
        ContentValues values3 = new ContentValues();
        if (!MyDatabaseHelper.isServiceExists(db, "Moroccan Bath")) {
            values3.put(MyDatabaseHelper.SPA_SERVICES_NAME, "Moroccan Bath");
            values3.put(MyDatabaseHelper.SPA_SERVICES_DESCRIPTION, "Traditional Moroccan bath experience");
            db.insert(MyDatabaseHelper.TABLE_SPA_SERVICES, null, values3);
        }

        // Insert "Hot Stone Massage" service if it doesn't already exist
        ContentValues values4 = new ContentValues();
        if (!MyDatabaseHelper.isServiceExists(db, "Hot Stone Massage")) {
            values4.put(MyDatabaseHelper.SPA_SERVICES_NAME, "Hot Stone Massage");
            values4.put(MyDatabaseHelper.SPA_SERVICES_DESCRIPTION, "Massage using heated stones");
            db.insert(MyDatabaseHelper.TABLE_SPA_SERVICES, null, values4);
        }


        // Insert "Manicure" service if it doesn't already exist


        db.close();
    }
}