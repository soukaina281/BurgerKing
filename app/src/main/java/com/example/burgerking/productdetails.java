package com.example.burgerking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.burgerking.model.Meal;

import java.io.ByteArrayOutputStream;

public class productdetails extends AppCompatActivity {
   TextView name,price,nombre;
   ImageView img,minus,plus;
   String pname,pprice;
   Button addtocart;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);
        plus=findViewById(R.id.plus);
        minus = findViewById(R.id.moin);
        nombre=findViewById(R.id.nombre);
        name = this.findViewById(R.id.name);
        price = findViewById(R.id.price);
        img = findViewById(R.id.imagep);
        addtocart= findViewById(R.id.addtocart);

        Intent i = getIntent();
        pname = i.getStringExtra("name");
        pprice = i.getStringExtra("price");

        name.setText(pname);
        price.setText(pprice);
        img.setImageDrawable(ContextCompat.getDrawable(this,this.getResources().getIdentifier(getIntent().getStringExtra("image"),"drawable" ,this.getPackageName())));

       plus.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int quantity = Integer.parseInt(nombre.getText().toString());
               quantity++;
               nombre.setText(String.valueOf(quantity));
           }
       });
       minus.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             int quantity = Integer.parseInt(nombre.getText().toString());
             if(quantity>1){
                 quantity--;
                 nombre.setText(String.valueOf(quantity));
             }
           }
       });
     /*  addtocart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
           DBHelper db= new DBHelper(productdetails.this);
           Bitmap bb = ((BitmapDrawable)img.getDrawable()).getBitmap() ;
           byte[] myimage = imgproduct(bb);
           db.AddshoopingCart(meal.getmName(),meal.getmPrice(),myimage);
               Toast.makeText(getApplicationContext(),"Product has been save in shopping cart",Toast.LENGTH_SHORT).show();
           }
       });*/




    }
    private byte[] imgproduct (Bitmap b){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG,0,bos);
        return bos.toByteArray();
    }
}