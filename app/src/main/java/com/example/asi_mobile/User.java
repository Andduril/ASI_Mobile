package com.example.asi_mobile;

public class User {
    private String name;
    private String email;

    public User() {
        // Constructeur vide requis pour Firebase
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

