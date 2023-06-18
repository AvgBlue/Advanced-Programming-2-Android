package com.example.advanced_programming_2_android;

import android.content.Context;

public class TokenManager {
    private static TokenManager instance;
    private String token;

    private TokenManager() {
        // Private constructor to enforce singleton pattern
    }

    public static synchronized TokenManager getInstance(Context context) {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }

    public void saveToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
