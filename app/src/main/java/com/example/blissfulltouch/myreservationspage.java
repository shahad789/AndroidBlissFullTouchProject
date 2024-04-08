package com.example.blissfulltouch;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.blissfulltouch.MyDatabaseHelper;

public class myreservationspage extends AppCompatActivity {

    private MyDatabaseHelper databaseHelper;
    private TextView serviceNameTextView, dateTextView, timeTextView, locationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreservationspage);

        // Initialize database helper
        databaseHelper = new MyDatabaseHelper(this);

        // Initialize TextViews
        serviceNameTextView = findViewById(R.id.thirdser);
        dateTextView = findViewById(R.id.thirddes);
        timeTextView = findViewById(R.id.thirdtime);
        locationTextView = findViewById(R.id.thirdtype);

        // Retrieve reservations from the database
        Cursor cursor = databaseHelper.getAllReservations();

// Check if cursor is not null and contains data
        if (cursor != null && cursor.moveToFirst()) {
            // Loop through the cursor to retrieve all reservations
            do {
                // Retrieve reservation details from the cursor
                int serviceNameIndex = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_SERVICE_NAME_FK);
                int dateIndex = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_DATE);
                int timeIndex = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_TIME);
                int locationIndex = cursor.getColumnIndex(MyDatabaseHelper.COLUMN_LOCATION);

                // Check if column indexes are valid
                if (serviceNameIndex != -1 && dateIndex != -1 && timeIndex != -1 && locationIndex != -1) {
                    String serviceName = cursor.getString(serviceNameIndex);
                    String date = cursor.getString(dateIndex);
                    String time = cursor.getString(timeIndex);
                    String location = cursor.getString(locationIndex);

                    // Populate TextViews with reservation details
                    serviceNameTextView.setText(serviceName);
                    dateTextView.setText(date);
                    timeTextView.setText(time);
                    locationTextView.setText(location);
                } else {
                    // Handle invalid column indexes
                    // This usually happens if column names are incorrect
                    Toast.makeText(this, "Invalid column indexes", Toast.LENGTH_SHORT).show();
                }
            } while (cursor.moveToNext());

            // Close the cursor after use
            cursor.close();
        } else {
            // No bookings to view, display a message
            Toast.makeText(this, "No bookings to view", Toast.LENGTH_SHORT).show();
        }

    }}