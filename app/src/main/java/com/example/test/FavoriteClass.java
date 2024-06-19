package com.example.test;

public class FavoriteClass {

    private String key;
    private String carKey;

    public FavoriteClass() {
    }

    public FavoriteClass(String carKey, String key) {
        this.carKey = carKey;
        this.key = key;
    }

    public String getCarKey() {
        return carKey;
    }

    public void setCarKey(String carKey) {
        this.carKey = carKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
