package com.example.burgerking;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;


public class DataAdapterCart extends ArrayAdapter<cartmodel> {

    Context c;
    ArrayList<cartmodel> ass;


    public DataAdapterCart( Context context, ArrayList<cartmodel> cont) {
        super(context, R.layout.activity_viewholder_cart, cont);
        c= context;
        ass=cont;

    }

    static class  holder{
        ImageView imag;
        TextView name,price;
    }
    public View getView(int position , View convertView, ViewGroup parent){
        cartmodel data = getItem(position);

        holder viewHolder;


        if (convertView == null) {

            viewHolder = new holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.activity_viewholder_cart,parent,false);

            viewHolder.name= (TextView) convertView.findViewById(R.id.titletext);
            viewHolder.price= (TextView) convertView.findViewById(R.id.price);
            viewHolder.imag= (ImageView) convertView.findViewById(R.id.imag);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (holder)convertView.getTag();
        }

        viewHolder.name.setText(data.getName());
        viewHolder.price.setText(data.getPrice());
        viewHolder.imag.setImageBitmap(convertToBitmap(data.getImage()));
        return convertView;
    }
    private Bitmap convertToBitmap(byte[] b){

        return BitmapFactory.decodeByteArray(b,0,b.length);
    }
}
