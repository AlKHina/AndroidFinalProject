package com.example.test;

import android.graphics.drawable.Drawable;

public class OpisanieClass {
    private Drawable image;
    private String name;
    private String opisanie;
    private String price;
    private String number;
    private String mini_opisanie;

    public OpisanieClass(Drawable image, String name, String opisanie, String price, String number, String mini_opisanie) {
        this.image = image;
        this.name = name;
        this.opisanie = opisanie;
        this.price = price;
        this.number = number;
        this.mini_opisanie = mini_opisanie;
    }

    public Drawable getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpisanie() {
        return opisanie;
    }

    public void setOpisanie(String opisanie) {
        this.opisanie = opisanie;
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

    public String getMini_opisanie() {
        return mini_opisanie;
    }

    public void setMini_opisanie(String mini_opisanie) {
        this.mini_opisanie = mini_opisanie;
    }
}

