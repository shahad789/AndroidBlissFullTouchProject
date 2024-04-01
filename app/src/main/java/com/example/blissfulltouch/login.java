package com.example.blissfulltouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class login extends AppCompatActivity {

    EditText Email, Password;


    MyDatabaseHelper DB;
    Button loginButton;
    TextView signupinstd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        Email = findViewById(R.id.emailLogIn);
        Password = findViewById(R.id.passLogIn);

        loginButton = findViewById(R.id.btn_logIn);
        DB = new MyDatabaseHelper(this);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String pass = Password.getText().toString();

                if(email.equals("")||pass.equals(""))
                    Toast.makeText(login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean check = DB.checkEmailPassword(email, pass);
                    if (check){
                        Intent intent = new Intent(getApplicationContext(), homepage.class);
                        intent.putExtra("userEmail",email);
                        startActivity(intent);
                    } else {
                        Toast.makeText(login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        ImageView backbutton = findViewById(R.id.backArrow);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getIntent().getStringExtra("PreviousPage")!=null || getIntent().getStringExtra("PreviousPage")=="Sign-up")
                    startActivity(new Intent(login.this, signUp.class));
                else
                    startActivity(new Intent(login.this, MainActivity.class));
            }
        });



        signupinstd=(TextView)findViewById(R.id.signupinstead);
        signupinstd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this, signUp.class);
                intent.putExtra("PreviousPage", "Sign-in");
                startActivity(intent);
            }
        });



    }//oncreates
}//end