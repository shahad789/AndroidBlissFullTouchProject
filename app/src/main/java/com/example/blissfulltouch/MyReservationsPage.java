package com.example.blissfulltouch;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.content.Intent;
import android.view.MenuItem;



import com.example.blissfulltouch.MyDatabaseHelper;
import com.example.blissfulltouch.ReservationAdapter;
import java.util.ArrayList;

public class MyReservationsPage extends AppCompatActivity {

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
    }
    private void deleteAllReservations() {
        databaseHelper.deleteAllReservations();
        // Optionally, refresh the RecyclerView or update the UI
    }

    @Override
    protected void onDestroy() {
        // Close the cursor when the activity is destroyed to release resources
        if (cursor != null) {
            cursor.close();
        }
        super.onDestroy();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle home item click
        if (item.getItemId() == R.id.home) {
            // Go to the home page
            Intent homeIntent = new Intent(this, homepage.class);
            startActivity(homeIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}
