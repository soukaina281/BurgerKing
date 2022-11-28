package com.example.burgerking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBname = "Login.db";
    public DBHelper(Context context) {
        super(context,"Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String queryString= "create Table user(id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,email TEXT,phone INTEGER,password TEXT)";
        DB.execSQL(queryString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists user");

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
}
