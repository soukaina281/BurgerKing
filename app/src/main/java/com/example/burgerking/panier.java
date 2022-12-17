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
        ArrayList<cartmodel> d = new ArrayList<>(db.GetsoppingCart(this));
        adapterCart = new DataAdapterCart(this ,d);
        list.setAdapter(adapterCart);



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