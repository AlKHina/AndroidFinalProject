package com.example.test;

public class ChatClass {
    private String message;
    private String key;
    private long time;

    public ChatClass(String message, String key, long time) {
        this.message = message;
        this.key = key;
        this.time = time;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
