package com.example.advanced_programming_2_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.advanced_programming_2_android.api.TokenAPI;
import com.example.advanced_programming_2_android.api.UserAPI;
import com.example.advanced_programming_2_android.classes.LoginRequest;

public class LogInActivity extends AppCompatActivity {
    private EditText tvUsername;
    private EditText tvPassword;
    private Button btnLogin;
    private ImageView settings;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        tvUsername = findViewById(R.id.usernameLogin);
        tvPassword = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.login_btn);
        settings = findViewById(R.id.settings_action_bar);

        TokenAPI tokenAPI = new TokenAPI();

        btnLogin.setOnClickListener(view -> {
            String username = tvUsername.getText().toString();
            String password = tvPassword.getText().toString();
            LoginRequest loginRequest = new LoginRequest(username, password);
            tokenAPI.login(loginRequest);
            MutableLiveData<String> tokenLiveData = tokenAPI.getTokenLiveData();
            tokenLiveData.observe(this, token -> {
                if (token != null) {
                    Intent intent = new Intent(this, ChatActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            });
        });

        settings.setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}