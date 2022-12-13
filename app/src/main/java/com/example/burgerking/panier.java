package com.example.burgerking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.burgerking.model.produit;

import java.util.ArrayList;

public class panier extends AppCompatActivity {
    private RecyclerView list;
    ImageView menu , home;
    ArrayList <produit> produitList = new ArrayList<>();



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        menu = findViewById(R.id.menu);
        home = findViewById(R.id.home);
        list = findViewById(R.id.view);
        list.setLayoutManager(new LinearLayoutManager(this));

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(panier.this, Menu.class));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(panier.this, Home.class));
            }
        });
    }
}