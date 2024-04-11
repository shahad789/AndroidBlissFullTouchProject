package com.example.blissfulltouch;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class homepage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ///\\\
// Find the reservationButton and attach a click listener to it
        ImageButton reservationButton = findViewById(R.id.reservationButton);
        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the MyReservationsPage activity
                Intent intent = new Intent(homepage.this, MyReservationsPage.class);
                startActivity(intent);
            }
        });
///\\\


        Button addButton = findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the registration form activity
                Intent intent = new Intent(homepage.this, registrationForm.class);
                startActivity(intent);
            }
        });

        // Add1 Button
        Button addButton1 = findViewById(R.id.Add1);
        addButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the registration form activity
                Intent intent = new Intent(homepage.this, registrationForm.class);
                startActivity(intent);
            }
        });

        // Add2 Button
        Button addButton2 = findViewById(R.id.add2);
        addButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the registration form activity
                Intent intent = new Intent(homepage.this, registrationForm.class);
                startActivity(intent);
            }
        });
        Button editTextText = findViewById(R.id.editTextTextn);
        editTextText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the registration form activity
                Intent intent = new Intent(homepage.this, searchpage.class);
                startActivity(intent);
            }
        });

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