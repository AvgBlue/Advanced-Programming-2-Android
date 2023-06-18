package com.example.advanced_programming_2_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

        btnLogin.setOnClickListener(view -> {
            // Todo: check if the user exist in with the api
            if (true) {
                Intent intent = new Intent(this, ChatActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "username or password is incorrect", Toast.LENGTH_LONG).show();
            }
        });

        settings.setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}