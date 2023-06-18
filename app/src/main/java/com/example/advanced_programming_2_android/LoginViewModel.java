package com.example.advanced_programming_2_android;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.Context;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Boolean> loginSuccessLiveData = new MutableLiveData<>();
    private TokenManager tokenManager;

    public LoginViewModel(Context context) {
        tokenManager = TokenManager.getInstance(context);
    }

    public void login(String username, String password) {
        // Perform login validation
        if (isValidCredentials(username, password)) {
            loginSuccessLiveData.setValue(true);

            // Simulate receiving a token from the server
            String token = "your_token_here";
            tokenManager.saveToken(token);
        } else {
            loginSuccessLiveData.setValue(false);
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // Validate the username and password
        // You can add your own logic here (e.g., calling a server API)
        // For now, let's assume the username is "admin" and the password is "password"
        return username.equals("admin") && password.equals("password");
    }

    public MutableLiveData<Boolean> getLoginSuccessLiveData() {
        return loginSuccessLiveData;
    }
}
