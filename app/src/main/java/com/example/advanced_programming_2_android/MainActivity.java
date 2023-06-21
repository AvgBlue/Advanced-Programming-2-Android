package com.example.advanced_programming_2_android;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.advanced_programming_2_android.api.UserAPI;
import com.example.advanced_programming_2_android.database.AppDB;

public class MainActivity extends AppCompatActivity {
    private Button btnSignIn;
    private Button btnRegister;
    private ImageView settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDB appdb=AppDB.getInstance(this);

        btnSignIn = findViewById(R.id.sign_in_btn);
        btnRegister = findViewById(R.id.register_btn);
        settings = findViewById(R.id.settings_action_bar);

        btnSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
        });

        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        settings.setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}







