package com.example.asi_mobile.Models;

import java.util.Date;

public class Message {
    String content;
    Long timestamp;
    String userId;

    // constructeur vide pour firebase
    public Message() {}

    public Message(String content, String userId) {
        this.userId = userId;
        this.content = content;
        this.timestamp = new Date().getTime();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
