package com.example.test;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class AuthClass {
    private Context context;
    private static String username = null;
    private static String ava;
    private static UserClass user = null;
    private static String key = null;
    public AuthClass (Context context){this.context = context;

    }

    public  String getUsername() {
        if (username == null){
            SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            username = sp.getString("username", null);
        }
        return username;
    }

    public  void setUsername(String username) {
        AuthClass.username = username;
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", username);
        editor.apply();
    }

    public  String getAva() {
        if (ava == null){
            SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            ava = sp.getString("ava", null);
        }
        return ava;
    }

    public  void setAva(String ava) {
        AuthClass.ava = ava;
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ava", ava);
        editor.apply();
    }

    public  String getKey() {
        if (key == null){
            SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            key = sp.getString("key",null);
        }
        return key;

    }

    public  void setKey(String key) {
        AuthClass.key = key;
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("key", key);
        editor.apply();
    }

    public  UserClass getUser() {
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        Gson gson = new  Gson();
        String json = sp.getString("user","");
        return gson.fromJson(json, UserClass.class);
    }

    public  void setUser(UserClass user) {
        AuthClass.user = user;
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        edit.putString("user", json);
        edit.apply();
    }
}
