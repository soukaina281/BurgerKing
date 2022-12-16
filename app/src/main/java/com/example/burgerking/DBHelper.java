package com.example.burgerking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBname = "Login.db";
    public DBHelper(Context context) {
        super(context,"Login.db", null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String queryString= "create Table user(id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,email TEXT,phone INTEGER,password TEXT)";
        DB.execSQL(queryString);
        DB.execSQL("create table shoopingCart(name TEXT ,price TEXT ,image BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            DB.execSQL("drop table if exists user");
            DB.execSQL("drop table if exists shoopingCart");
            onCreate(DB);
        }




    }
    public Boolean insertData(String username,String email,int phone,String password){
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("username",username);
        cv.put("email",email);
        cv.put("phone",phone);
        cv.put("password",password);

        long result = DB.insert("user",null,cv);

        if (result == -1) return false;
        else return true;
    }
    public Boolean checkusername( String username){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * from user where username= ?",new String[] {username});
        if(cursor.getCount() > 0) return true;
        else return false;
    }
    public Boolean checkusernamepassword(String username,String password){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * from user where username= ? and password=?",new String[] {username,password});
        if(cursor.getCount() > 0) return true;
        else return false;
    }

    public List<cartmodel> GetsoppingCart(Context cn){

        List<cartmodel> data = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cart = db.rawQuery("select * from shoopingCart",null);
        while(cart.moveToNext()){
            cartmodel cr = new cartmodel();
            cr.setName(cart.getString(0));
            cr.setPrice(cart.getString(1));
            cr.setImage(cart.getBlob(2));
            data.add(cr);
        }
        return data;
    }

   public void AddshoopingCart(String name,String price,byte[] image){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data= new ContentValues();
        data.put("name",name);
        data.put("price",price);
        data.put("image",image);
        db.insert("shoopingCart",null,data);
    }
   /* public int countShoopingcart(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cn = db.rawQuery("select * from shoopingCart",null);
        cn.moveToFirst();
        return cn.getCount();
    }*/
}
