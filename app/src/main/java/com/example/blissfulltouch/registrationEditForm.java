// registrationEditForm.java
package com.example.blissfulltouch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class registrationEditForm extends AppCompatActivity {

    private EditText dateEditText, timeEditText;
    private RadioButton atHomeRadioButton, atSpaRadioButton;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_edit_form);

        // Initialize views
        dateEditText = findViewById(R.id.enterdate);
        timeEditText = findViewById(R.id.entertaime);
        atHomeRadioButton = findViewById(R.id.AtHome);
        atSpaRadioButton = findViewById(R.id.Atspa);

        // Retrieve user email from intent extras
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("userEmail");

        // Retrieve and display saved reservation data
        displaySavedReservationData();

        // Set click listener for update button
        Button updateButton = findViewById(R.id.Add);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateReservation();
            }
        });
    }

    // Method to retrieve and display saved reservation data
    private void displaySavedReservationData() {
        SharedPreferences preferences = getSharedPreferences("reservation_data", MODE_PRIVATE);
        String date = preferences.getString("date", "");
        String time = preferences.getString("time", "");
        String location = preferences.getString("location", "");

        // Set retrieved values to the UI elements
        dateEditText.setText(date);
        timeEditText.setText(time);
        if (location.equals("At Home")) {
            atHomeRadioButton.setChecked(true);
        } else {
            atSpaRadioButton.setChecked(true);
        }
    }

    // Method to update reservation information
    // Method to update reservation information
    private void updateReservation() {
        String date = dateEditText.getText().toString().trim();
        String time = timeEditText.getText().toString().trim();
        String location = atHomeRadioButton.isChecked() ? "At Home" : "At Spa";

        // Update reservation in the database
        MyDatabaseHelper databaseHelper = new MyDatabaseHelper(this);
        boolean updated = databaseHelper.updateReservationByEmail(userEmail, date, time, location);

        if (updated) {
            // If the reservation is successfully updated in the database
            Toast.makeText(this, "Reservation updated successfully!", Toast.LENGTH_SHORT).show();

            // Redirect to MyReservationsPage
            Intent intent = new Intent(registrationEditForm.this, MyReservationsPage.class);
            startActivity(intent);
            finish();
        } else {
            // If there was an error updating the reservation
            Toast.makeText(this, "Failed to update reservation", Toast.LENGTH_SHORT).show();
        }
    }}


/* edite gooooood
package com.example.blissfulltouch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class registrationEditForm extends AppCompatActivity {

    private EditText dateEditText, timeEditText;
    private RadioButton atHomeRadioButton, atSpaRadioButton;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_edit_form);

        // Initialize views
        dateEditText = findViewById(R.id.enterdate);
        timeEditText = findViewById(R.id.entertaime);
        atHomeRadioButton = findViewById(R.id.AtHome);
        atSpaRadioButton = findViewById(R.id.Atspa);

        // Retrieve user email from intent extras
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("userEmail");

        // Retrieve and display saved reservation data
        displaySavedReservationData();

        // Set click listener for update button
        Button updateButton = findViewById(R.id.Add);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateReservation();
            }
        });
    }

    // Method to retrieve and display saved reservation data
    private void displaySavedReservationData() {
        SharedPreferences preferences = getSharedPreferences("reservation_data", MODE_PRIVATE);
        String date = preferences.getString("date", "");
        String time = preferences.getString("time", "");
        String location = preferences.getString("location", "");

        // Set retrieved values to the UI elements
        dateEditText.setText(date);
        timeEditText.setText(time);
        if (location.equals("At Home")) {
            atHomeRadioButton.setChecked(true);
        } else {
            atSpaRadioButton.setChecked(true);
        }
    }

    // Method to update reservation information
    private void updateReservation() {
        String date = dateEditText.getText().toString().trim();
        String time = timeEditText.getText().toString().trim();
        String location = atHomeRadioButton.isChecked() ? "At Home" : "At Spa";

        // Update reservation in the database or storage
        // You can implement this part to update the reservation in your database
        // For demonstration purpose, I'll just show a toast message indicating successful update
        Toast.makeText(this, "Reservation updated successfully!", Toast.LENGTH_SHORT).show();

        // Redirect to MyReservationsPage
        Intent intent = new Intent(registrationEditForm.this, MyReservationsPage.class);
        startActivity(intent);
        finish();
    }
}


*/
/*
package com.example.blissfulltouch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class registrationEditForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_edit_form);

        // Retrieve service name from intent extras
        String serviceName = getIntent().getStringExtra("service");

        // Find the update button
        Button updateButton = findViewById(R.id.Add);

        // Set click listener for the update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform update action here
                // For example, you can update the reservation details in the database

                // After updating, redirect the user to the MyReservationsPage
                Intent reservationIntent = new Intent(registrationEditForm.this, MyReservationsPage.class);
                startActivity(reservationIntent);
            }
        });

        // Find the cancel button
        Button cancelButton = findViewById(R.id.cancel_button);

        // Set click listener for the cancel button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect the user to the MyReservationsPage
                Intent reservationIntent = new Intent(registrationEditForm.this, MyReservationsPage.class);
                startActivity(reservationIntent);
            }
        });
    }
}
*/