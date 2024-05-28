package com.example.asi_mobile.model;


import com.google.firebase.Timestamp;

public class UserModel {
    private String username;
    private String email;
    private Timestamp createdTimestamp;

    public UserModel() {
    }

    public UserModel(String username, String email, Timestamp createdTimestamp) {
        this.username = username;
        this.email = email;
        this.createdTimestamp = createdTimestamp;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
}
