package com.example.burgerking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.burgerking.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MealAdapter extends RecyclerView.Adapter {
    private ArrayList<HashMap<String, String>> meals;
    Context context;

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

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.name);
            price = view.findViewById(R.id.price);
            icon = view.findViewById(R.id.icon);
        }
    }

}
