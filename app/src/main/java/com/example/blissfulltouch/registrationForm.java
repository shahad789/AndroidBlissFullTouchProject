package com.example.blissfulltouch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class registrationForm extends AppCompatActivity {
    private EditText enterDateEditText, enterTimeEditText;
    private RadioButton atHomeRadioButton, atSpaRadioButton;
    private MyDatabaseHelper dbHelper;
    private String userEmail;
    private String serviceName; // Added field to store the service name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        // Initialize views and database helper
        enterDateEditText = findViewById(R.id.enterdate);
        enterTimeEditText = findViewById(R.id.entertaime);
        atHomeRadioButton = findViewById(R.id.AtHome);
        atSpaRadioButton = findViewById(R.id.Atspa);
        dbHelper = new MyDatabaseHelper(this);

        // Retrieve user email and service name from intent extras
        userEmail = getIntent().getStringExtra("userEmail");
        serviceName = getIntent().getStringExtra("service");

        // Set click listener for add button
        Button addButton = findViewById(R.id.Add);
        addButton.setOnClickListener(v -> addReservation());

        // Set click listener for cancel button
        Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registrationForm.this, homepage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Method to handle adding reservation
    private void addReservation() {
        String date = enterDateEditText.getText().toString().trim();
        String time = enterTimeEditText.getText().toString().trim();

        if (date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please enter all data ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (atHomeRadioButton.isChecked() && atSpaRadioButton.isChecked()) {
            Toast.makeText(this, "Invalid: Please choose only one location", Toast.LENGTH_SHORT).show();
            return;
        }

        String location = atHomeRadioButton.isChecked() ? "At Home" : "At Spa";

        boolean success = dbHelper.addReservation(location, time, date, userEmail, serviceName); // Pass service name to addReservation method

        if (success) {
            Toast.makeText(this, "Reservation added successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(registrationForm.this, homepage.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Failed to add reservation!", Toast.LENGTH_SHORT).show();
        }
    }
}


