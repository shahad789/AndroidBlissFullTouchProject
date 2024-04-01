package com.example.blissfulltouch;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signUp extends AppCompatActivity {

    EditText signupEnteredName, signupEnteredAge, signupEnteredEmail, signupEnteredPassword;

    MyDatabaseHelper DB;

    Button signupButton;

    TextView signininstead;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        signupEnteredName = (EditText) findViewById(R.id.nameSignUp);
        signupEnteredEmail = (EditText) findViewById(R.id.emailSignUp);
        signupEnteredAge = (EditText) findViewById(R.id.ageSignUp);
        signupEnteredPassword = (EditText) findViewById(R.id.passSignUp);


        signupButton = (Button) findViewById(R.id.btn_signUp);

        DB = new MyDatabaseHelper(this);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signupEnteredEmail.getText().toString();
                String pass = signupEnteredPassword.getText().toString();
                String user = signupEnteredName.getText().toString();
                String age = signupEnteredAge.getText().toString();


                if (user.equals("") || pass.equals("") || email.equals("") || age.equals(""))
                    Toast.makeText(signUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean check = DB.checkemail(email);
                    if (!check) {
                        Boolean insert = DB.addUser(email, user,age,pass);
                        if (insert) {
                            Toast.makeText(signUp.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(signUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(signUp.this, "Already exists! please sign in", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });



        signininstead = (TextView) findViewById(R.id.logininstead);

        signininstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signUp.this, login.class);
                intent.putExtra("PreviousPage", "Sign-up");
                startActivity(intent);
            }
        });
        ImageView backbutton = findViewById(R.id.backArrow);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getIntent().getStringExtra("PreviousPage") != null || getIntent().getStringExtra("PreviousPage") == "Sign-in")
                    startActivity(new Intent(signUp.this, login.class));
                else
                    startActivity(new Intent(signUp.this, MainActivity.class));
            }

        });


    }//on creare
}//close big
