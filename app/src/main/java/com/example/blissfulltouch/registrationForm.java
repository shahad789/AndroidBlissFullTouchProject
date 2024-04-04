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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        enterDateEditText = findViewById(R.id.enterdate);
        enterTimeEditText = findViewById(R.id.entertaime);
        atHomeRadioButton = findViewById(R.id.AtHome);
        atSpaRadioButton = findViewById(R.id.Atspa);
        dbHelper = new MyDatabaseHelper(this);

        Button addButton = findViewById(R.id.Add);
        addButton.setOnClickListener(v -> addReservation());

        Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch HomeActivity and finish the current activity
                Intent intent = new Intent(registrationForm.this, homepage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addReservation() {
        String date = enterDateEditText.getText().toString().trim();
        String time = enterTimeEditText.getText().toString().trim();

        // Check if date or time is empty
        if (date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please enter all data ", Toast.LENGTH_SHORT).show();
            return; // Stop further execution
        }

        if (atHomeRadioButton.isChecked() && atSpaRadioButton.isChecked()) {
            // Both options are selected, show error message
            Toast.makeText(this, "Invalid: Please choose only one location", Toast.LENGTH_SHORT).show();
            return; // Stop further execution
        }

        String location = atHomeRadioButton.isChecked() ? "At Home" : "At Spa";
        String serviceName = "Your desired service name"; // You need to set the service name here

        boolean success = dbHelper.addReservation(location, time, date, serviceName);
        if (success) {
            Toast.makeText(this, "Reservation added successfully!", Toast.LENGTH_SHORT).show();

            // Redirect to home page
            Intent intent = new Intent(registrationForm.this, homepage.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Failed to add reservation!", Toast.LENGTH_SHORT).show();
        }
    }
}