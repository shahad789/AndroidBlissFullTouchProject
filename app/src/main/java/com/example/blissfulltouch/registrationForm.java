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

public class registrationForm extends AppCompatActivity {
    private String userEmail;
    private String serviceName; // Added field to store the service name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        // Retrieve user email and service name from intent extras
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("userEmail");
        serviceName = intent.getStringExtra("service");

        // Set click listener for add button
        Button addButton = findViewById(R.id.Add);
        addButton.setOnClickListener(v -> addReservation());
    }

    // Method to handle adding reservation
    private void addReservation() {
        String date = ((EditText) findViewById(R.id.enterdate)).getText().toString().trim();
        String time = ((EditText) findViewById(R.id.entertaime)).getText().toString().trim();

        if (date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please enter all data", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton atHomeRadioButton = findViewById(R.id.AtHome);
        RadioButton atSpaRadioButton = findViewById(R.id.Atspa);
        String location = atHomeRadioButton.isChecked() ? "At Home" : "At Spa";

        // Save reservation data to SharedPreferences
        saveReservationDataToSharedPreferences(date, time, location);

        // Save reservation data to the database
        saveReservationDataToDatabase(date, time, location);

        // Redirect to homepage after adding reservation
        Intent homeIntent = new Intent(registrationForm.this, homepage.class);
        startActivity(homeIntent);
        finish();
    }

    // Method to save reservation data to SharedPreferences
    private void saveReservationDataToSharedPreferences(String date, String time, String location) {
        SharedPreferences preferences = getSharedPreferences("reservation_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("date", date);
        editor.putString("time", time);
        editor.putString("location", location);
        editor.apply();
    }

    // Method to save reservation data to the database
    private void saveReservationDataToDatabase(String date, String time, String location) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        dbHelper.addReservation(location, time, date, userEmail, serviceName);
    }
}



/*goood2
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

public class registrationForm extends AppCompatActivity {
    private String userEmail;
    private String serviceName; // Added field to store the service name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        // Retrieve user email and service name from intent extras
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("userEmail");
        serviceName = intent.getStringExtra("service");

        // Set click listener for add button
        Button addButton = findViewById(R.id.Add);
        addButton.setOnClickListener(v -> addReservation());
    }

    // Method to handle adding reservation
    private void addReservation() {
        String date = ((EditText) findViewById(R.id.enterdate)).getText().toString().trim();
        String time = ((EditText) findViewById(R.id.entertaime)).getText().toString().trim();

        if (date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please enter all data", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton atHomeRadioButton = findViewById(R.id.AtHome);
        RadioButton atSpaRadioButton = findViewById(R.id.Atspa);
        String location = atHomeRadioButton.isChecked() ? "At Home" : "At Spa";

        // Save reservation data to SharedPreferences
        saveReservationDataToSharedPreferences(date, time, location);

        // Save reservation data to the database
        saveReservationDataToDatabase(date, time, location);

        // Redirect to homepage after adding reservation
        Intent homeIntent = new Intent(registrationForm.this, homepage.class);
        startActivity(homeIntent);
        finish();
    }

    // Method to save reservation data to SharedPreferences
    private void saveReservationDataToSharedPreferences(String date, String time, String location) {
        SharedPreferences preferences = getSharedPreferences("reservation_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("date", date);
        editor.putString("time", time);
        editor.putString("location", location);
        editor.apply();
    }

    // Method to save reservation data to the database
    private void saveReservationDataToDatabase(String date, String time, String location) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        dbHelper.addReservation(location, time, date, userEmail, serviceName);
    }
}
*/

/* gooooooooooood33
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
                // Redirect to MyReservationsPage
                Intent intent = new Intent(registrationForm.this, MyReservationsPage.class);
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

        // Add reservation to the database
        boolean success = dbHelper.addReservation(location, time, date, userEmail, serviceName);

        if (success) {
            Toast.makeText(this, "Reservation added successfully!", Toast.LENGTH_SHORT).show();
            // Redirect to homepage
            Intent intent = new Intent(registrationForm.this, homepage.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Failed to add reservation!", Toast.LENGTH_SHORT).show();
        }
    }
}

*/

/*
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
}*/