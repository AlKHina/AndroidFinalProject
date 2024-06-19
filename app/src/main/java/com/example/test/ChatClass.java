package com.example.test;

public class ChatClass {

    private String key;
    private String ownerKey;
    private String customerKey;
    private String chatKey;
    private String carName;
    private String ava;
    private String nick;
    private String message;

    public ChatClass(String key, String ownerKey, String customerKey, String chatKey, String carName, String ava, String nick, String message) {
        this.key = key;
        this.ownerKey = ownerKey;
        this.customerKey = customerKey;
        this.chatKey = chatKey;
        this.carName = carName;
        this.ava = ava;
        this.nick = nick;
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOwnerKey() {
        return ownerKey;
    }

    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    public String getChatKey() {
        return chatKey;
    }

    public void setChatKey(String chatKey) {
        this.chatKey = chatKey;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}