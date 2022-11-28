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
import com.example.burgerking.model.produit;

import java.util.ArrayList;

public class panierAdapter extends RecyclerView.Adapter {
    public panierAdapter(Context context, ArrayList<produit> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    static Context context;
    ArrayList<produit> dataset;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.activity_viewholder_cart,parent,false);

        return new produitHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        produit p = dataset.get(position);
        produitHolder.setdetails(p);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class produitHolder extends RecyclerView.ViewHolder{
       public static TextView name;
        public static TextView price;
        static ImageView img;
        public produitHolder( View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.titletext);
            price =itemView.findViewById(R.id.price);
            img=itemView.findViewById(R.id.img);

     }

     static void setdetails(produit p){

            name.setText(p.getName());
            price.setText(p.getPrice());
            img.setImageDrawable(ContextCompat.getDrawable(context,context.getResources().getIdentifier(p.getImage(), "drawable", context.getPackageName())));
     }
    }
}
