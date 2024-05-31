package com.example.asi_mobile.Models;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    String content, userId;
    Long timestamp;
    Boolean isLocation;

    // constructeur vide pour firebase
    public Message() {}

    public Message(String content, String userId, Boolean isLocation) {
        this.userId = userId;
        this.content = content;
        this.timestamp = new Date().getTime();
        this.isLocation = isLocation;
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

    public String getFormatedDate() {
        Date date = new Date(this.timestamp);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    };
    public Boolean getIsLocation() {
        return isLocation;
    }
    public void setIsLocation(Boolean isLocation) {
        this.isLocation = isLocation;
    }

}