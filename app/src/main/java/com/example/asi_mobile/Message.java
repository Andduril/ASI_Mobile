package com.example.asi_mobile;

import java.util.Date;

public class Message {
    private String content;
    private long timestamp;

    // Firebase empty constructor
    public Message() {

    }

    public Message(String content) {
        this.content = content;
        this.timestamp = new Date().getTime();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
