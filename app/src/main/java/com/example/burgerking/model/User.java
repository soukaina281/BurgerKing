package com.example.burgerking.model;

public class User {
    private int id;
    private String mName;
    private String mEmail;
    private String mPassword;

    public User(){}

    public User(int id, String mName, String mEmail, String mPassword) {
        this.id = id;
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
