package com.example.burgerking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {
    private EditText usernameup,email,phone,passwordup;
    private Button signup;
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameup = (EditText) findViewById(R.id.usernameup);
        email=(EditText) findViewById(R.id.email);
        phone =(EditText) findViewById(R.id.phone);
        passwordup =(EditText) findViewById(R.id.passwordup);
        signup=(Button) findViewById(R.id.signup);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameup.getText().toString();
                String password = passwordup.getText().toString();
                String myphone = phone.getText().toString();
                String myemail = email.getText().toString();

                if(username.equals("") || password.equals("")|| myphone.equals("") || myemail.equals("")){
                    Toast.makeText(signup.getContext(), "Please enter all the fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuser = DB.checkusername(username);
                    if(checkuser == false){
                        Boolean insert =DB.insertData(username,myemail,Integer.parseInt(myphone),password);
                        if(insert == true){
                           Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                           Intent myintent = new Intent(getApplicationContext(),SinIn.class);
                           startActivity(myintent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Registration failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"User already exist. Please Sign In",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
}