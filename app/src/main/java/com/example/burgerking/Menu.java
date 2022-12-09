package com.example.burgerking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.burgerking.Adapter.MealAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Menu extends AppCompatActivity {

    private ListView mListView;
    private RecyclerView burgers, fries, drinks;
    private RecyclerView.Adapter adapter1, adapter2, adapter3;
    ArrayList<HashMap<String, String>> burgersList = null;
    ArrayList<HashMap<String, String>> friesList = null;
    ArrayList<HashMap<String, String>> drinksList = null;
    ImageView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        user = findViewById(R.id.user);
        burgers = findViewById(R.id.burgersRecycler);
        fries = findViewById(R.id.friesRecycler);
        drinks = findViewById(R.id.drinksRecycler);
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray burgers = obj.getJSONArray("burgers");
            burgersList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> mBurgersList;

            for (int i = 0; i < burgers.length(); i++) {
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

            for (int i = 0; i < fries.length(); i++) {
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

            for (int i = 0; i < drinks.length(); i++) {
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


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        burgers.setLayoutManager(mLayoutManager);
        adapter1 = new MealAdapter(this, burgersList);
        burgers.setAdapter(adapter1);

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        fries.setLayoutManager(mLayoutManager1);
        adapter2 = new MealAdapter(this, friesList);
        fries.setAdapter(adapter2);

        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        drinks.setLayoutManager(mLayoutManager2);
        adapter3 = new MealAdapter(this, drinksList);
        drinks.setAdapter(adapter3);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, UserDetails.class));
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