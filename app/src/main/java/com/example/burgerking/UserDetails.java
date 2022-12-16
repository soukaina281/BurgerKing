package com.example.burgerking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserDetails extends AppCompatActivity {

    private TextView name, email, changePassword, logout;
    private ImageView changeName, changeEmail;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        db = new DBHelper(this);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        changePassword = findViewById(R.id.changePassword);
        changeName = findViewById(R.id.changeName);
        changeEmail = findViewById(R.id.changeEmail);
        logout = findViewById(R.id.logOut);

        name.setText(myUser.user.getmName());
        email.setText(myUser.user.getmEmail());

        changeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserDetails.this);
                View input = getLayoutInflater().inflate(R.layout.alert_dialog_input, null);
                EditText editText = input.findViewById(R.id.myName);
                editText.setText(myUser.user.getmName());

                builder.setMessage("Enter your name")
                        .setView(input)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.changeUserName(myUser.user.getId(), editText.getText().toString());
                                myUser.user.setmName(editText.getText().toString());
                                onResume();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });
        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserDetails.this);
                View input = getLayoutInflater().inflate(R.layout.alert_dialog_input, null);
                EditText editText = input.findViewById(R.id.myName);
                editText.setText(myUser.user.getmEmail());

                builder.setMessage("Enter your email")
                        .setView(input)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.changeUserEmail(myUser.user.getId(), editText.getText().toString());
                                myUser.user.setmEmail(editText.getText().toString());
                                onResume();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserDetails.this);
                View input = getLayoutInflater().inflate(R.layout.password_input, null);
                EditText oldPass = input.findViewById(R.id.oldPassword);
                EditText newPass = input.findViewById(R.id.newPassword);

                builder.setView(input)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Boolean update = db.changeUserPassword(myUser.user.getId(), oldPass.getText().toString(), newPass.getText().toString());
                                if(update){
                                    Toast.makeText(UserDetails.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(UserDetails.this, "Old password is wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent logout = new Intent(getApplicationContext(),SinIn.class);
                 startActivity(logout);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        name.setText(myUser.user.getmName());
        email.setText(myUser.user.getmEmail());
    }
}