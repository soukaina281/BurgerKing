package com.example.burgerking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.burgerking.Adapter.MealAdapter;
import com.example.burgerking.Adapter.MealAdapter2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Home extends AppCompatActivity {
    SearchView Sview;
    ImageView menu , cart;
    private RecyclerView burgers, fries, drinks;
    private RecyclerView.Adapter adapter1, adapter2, adapter3;
    ArrayList<HashMap<String, String>> burgersList = null;
    ArrayList<HashMap<String, String>> friesList = null;
    ArrayList<HashMap<String, String>> drinksList = null;
    ConstraintLayout item1, item2, item3;
    ImageView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menu = findViewById(R.id.menu);
        cart = findViewById(R.id.cart);
        Sview = findViewById(R.id.SV);
        user = findViewById(R.id.user);

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);

        burgers = findViewById(R.id.burgersRecycler);
        fries = findViewById(R.id.friesRecycler);
        drinks = findViewById(R.id.drinksRecycler);
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray burgers = obj.getJSONArray("burgers");
            burgersList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> mBurgersList;

            for (int i = 0; i < 5; i++) {
                JSONObject burger = burgers.getJSONObject(i);
                String name = burger.getString("name");
                String icon = burger.getString("icon");
                String price = burger.getString("price");

                mBurgersList = new HashMap<String, String>();
                mBurgersList.put("name", name);
                mBurgersList.put("icon", icon);
                mBurgersList.put("price", price);

                burgersList.add(mBurgersList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray fries = obj.getJSONArray("fries");
            friesList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> mFriesList;

            for (int i = 0; i < 5; i++) {
                JSONObject frie = fries.getJSONObject(i);
                String name = frie.getString("name");
                String icon = frie.getString("icon");
                String price = frie.getString("price");

                mFriesList = new HashMap<String, String>();
                mFriesList.put("name", name);
                mFriesList.put("icon", icon);
                mFriesList.put("price", price);

                friesList.add(mFriesList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray drinks = obj.getJSONArray("drinks");
            drinksList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> mDrinksList;

            for (int i = 0; i < 5; i++) {
                JSONObject drink = drinks.getJSONObject(i);
                String name = drink.getString("name");
                String icon = drink.getString("icon");
                String price = drink.getString("price");

                mDrinksList = new HashMap<String, String>();
                mDrinksList.put("name", name);
                mDrinksList.put("icon", icon);
                mDrinksList.put("price", price);

                drinksList.add(mDrinksList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                burgers.setVisibility(View.VISIBLE);
                fries.setVisibility(View.GONE);
                drinks.setVisibility(View.GONE);
                item1.setBackground(getDrawable(R.drawable.orange_background));
                item2.setBackground(getDrawable(R.drawable.white_background));
                item3.setBackground(getDrawable(R.drawable.white_background));
            }
        });

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                burgers.setVisibility(View.GONE);
                fries.setVisibility(View.VISIBLE);
                drinks.setVisibility(View.GONE);
                item1.setBackground(getDrawable(R.drawable.white_background));
                item2.setBackground(getDrawable(R.drawable.orange_background));
                item3.setBackground(getDrawable(R.drawable.white_background));
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                burgers.setVisibility(View.GONE);
                fries.setVisibility(View.GONE);
                drinks.setVisibility(View.VISIBLE);
                item1.setBackground(getDrawable(R.drawable.white_background));
                item2.setBackground(getDrawable(R.drawable.white_background));
                item3.setBackground(getDrawable(R.drawable.orange_background));
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        burgers.setLayoutManager(mLayoutManager);
        adapter1 = new MealAdapter2(this, burgersList);
        burgers.setAdapter(adapter1);
        burgers.setNestedScrollingEnabled(false);

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        fries.setLayoutManager(mLayoutManager1);
        adapter2 = new MealAdapter2(this, friesList);
        fries.setAdapter(adapter2);
        fries.setNestedScrollingEnabled(false);

        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        drinks.setLayoutManager(mLayoutManager2);
        adapter3 = new MealAdapter2(this, drinksList);
        drinks.setAdapter(adapter3);
        drinks.setNestedScrollingEnabled(false);

        fries.setVisibility(View.GONE);
        drinks.setVisibility(View.GONE);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Menu.class));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, panier.class));
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, UserDetails.class));
            }
        });
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("menu.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}