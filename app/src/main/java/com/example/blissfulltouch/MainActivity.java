package com.example.blissfulltouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonlogin;
    Button buttonsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonsignup = findViewById(R.id.buttonSignUp);
        buttonsignup.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, signUp.class)));

        buttonlogin = findViewById(R.id.buttonLogIn);
        buttonlogin.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, login.class)));
    }
}
