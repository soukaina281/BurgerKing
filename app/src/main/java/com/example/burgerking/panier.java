package com.example.burgerking;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class panier extends AppCompatActivity {
    private ListView list;
    DataAdapterCart adapterCart;
    DBHelper db = new DBHelper(this);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);
        list = findViewById(R.id.view);
        ArrayList<cartmodel> d = new ArrayList<>(db.GetsoppingCart(this));
        adapterCart = new DataAdapterCart(this ,d);
        list.setAdapter(adapterCart);







    }
}