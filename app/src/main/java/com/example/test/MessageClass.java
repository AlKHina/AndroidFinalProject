package com.example.test;

public class MessageClass {
    private String key;
    private String message;
    private long time;
    private String senderName;

    public MessageClass(String key, String message, long time, String senderName) {
        this.key = key;
        this.message = message;
        this.time = time;
        this.senderName = senderName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}