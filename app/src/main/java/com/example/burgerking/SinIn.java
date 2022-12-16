package com.example.burgerking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SinIn extends AppCompatActivity {

    private EditText usernamein,passwordin;
    private Button signin;
    DBHelper DB;
    private TextView myregister;

    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_in);

        usernamein = (EditText)findViewById(R.id.usernamein);
        passwordin = (EditText)findViewById(R.id.passwordin);
        signin = (Button)findViewById(R.id.signin);
        DB = new DBHelper(this);



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernamein.getText().toString();
                String password = passwordin.getText().toString();
               if(username.equals("")|| password.equals("")){
                   Toast.makeText(signin.getContext(), "Please enter all the fields",Toast.LENGTH_SHORT).show();

               }
               else{
                   Boolean checkuserpass = DB.checkusernamepassword(username,password);
                       if(checkuserpass == true){
                           myUser.user = DB.getUser(username);
                           Toast.makeText(getApplicationContext(),"Sign In Successfull",Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(getApplicationContext(),Home.class);
                           startActivity(intent);
                       }else{
                           Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_SHORT).show();
                       }
                   }
               }

        });

        myregister = (TextView) findViewById(R.id.register);
        myregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navtoregister = new Intent(getApplicationContext(),signup.class);
                startActivity(navtoregister);
            }
        });
    }
}