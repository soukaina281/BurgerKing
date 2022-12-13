package com.example.burgerking.Adapter;

import static com.example.burgerking.Adapter.panierAdapter.produitHolder.img;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.burgerking.DBHelper;
import com.example.burgerking.R;
import com.example.burgerking.SinIn;
import com.example.burgerking.model.Meal;
import com.example.burgerking.panier;
import com.example.burgerking.productdetails;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MealAdapter extends RecyclerView.Adapter {
    public ArrayList<HashMap<String, String>> meals;
    public static Context context;


    public MealAdapter(Context context, ArrayList<HashMap<String, String>> meals){
        this.context = context;
        this.meals = meals;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        onBindViewHolder((ViewHolder) holder, i, true);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int i, boolean b) {
        HashMap<String,String> meal = meals.get(i);
        holder.icon.setImageDrawable(ContextCompat.getDrawable(context, context.getResources().getIdentifier(meal.get("icon"), "drawable", context.getPackageName())));
        holder.name.setText(meal.get("name"));
        holder.price.setText(meal.get("price"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,productdetails.class);

                i.putExtra("name",meal.get("name"));
                i.putExtra("price",meal.get("price"));
                i.putExtra("image",meal.get("icon"));
                context.startActivity(i);

            }
        });



    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView name;
        public final TextView price;
        public final ImageView icon;
        public final Button add;
        HashMap<String,String> meal;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.name);
            price = view.findViewById(R.id.price);
            icon = view.findViewById(R.id.icon);
            add = view.findViewById(R.id.add);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(context, productdetails.class);
                    context.startActivity(myIntent);

                }
            });


           /* add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    context.startActivity(new Intent(context, panier.class));

                    //Log.i("this", "onClick: done");
                }
            });*/
        }

    }


}
