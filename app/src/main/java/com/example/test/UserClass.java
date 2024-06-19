package com.example.test;

public class UserClass {

    private String key;
    private String nick;
    private String email;
    private String number;
    private String pass;
    private String ava;

    public UserClass() {
    }

    public UserClass(String key, String nick, String email, String number, String pass, String ava) {
        this.key = key;
        this.nick = nick;
        this.email = email;
        this.number = number;
        this.pass = pass;
        this.ava = ava;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }
}