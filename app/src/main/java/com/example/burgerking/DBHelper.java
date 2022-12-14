package com.example.burgerking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.burgerking.model.User;

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
    public User getUser(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ?", new String[]{email});
        if(cursor != null){
            cursor.moveToFirst();
        }
        return new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(4));
    }
    public void changeUserName(int id, String newName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", newName);
        db.update("user", values, "id = ?", new String[]{String.valueOf(id)});
    }
    public void changeUserEmail(int id, String newEmail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", newEmail);
        db.update("user", values, "id = ?", new String[]{String.valueOf(id)});
    }
    public Boolean changeUserPassword(int id, String oldPassword, String newPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", newPassword);
        long update = db.update("user", values, "id = ? AND password = ?", new String[]{String.valueOf(id), oldPassword});
        if (update == -1) {
            return false;
        }
        else {
            return true;
        }
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


}
