package com.example.test;

import android.graphics.drawable.Drawable;

public class DescriptionClass {

    private Drawable image;
    private String name;
    private String description;
    private String price;
    private String number;
    private String miniDescription;

    public DescriptionClass(Drawable image, String name, String description, String price, String number, String miniDescription) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.number = number;
        this.miniDescription = miniDescription;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMiniDescription() {
        return miniDescription;
    }

    public void setMiniDescription(String miniDescription) {
        this.miniDescription = miniDescription;
    }
}

