package com.example.burgerking.model;

public class produit {
    String  Name,Image,Price;

    public produit(String name,String image,String price) {
        Name = name;
        Image = image;
        Price = price;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public String getPrice() {
        return Price;
    }

    public String getName() {
        return Name;
    }
}
