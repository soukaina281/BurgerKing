package com.example.burgerking.model;

public class Meal {
    private String mName, mImage, mPrice;

    public Meal(String mName, String mImage, String mPrice) {
        this.mName = mName;
        this.mImage = mImage;
        this.mPrice = mPrice;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }
}
