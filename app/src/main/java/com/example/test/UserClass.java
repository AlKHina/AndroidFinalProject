package com.example.test;

public class UserClass {
    private String name;
    private String login;
    private String pass;
    private String key;

    public UserClass(){

    }
    public UserClass(String name, String login, String pass, String key) {
        this.name = name;
        this.login = login;
        this.pass = pass;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

