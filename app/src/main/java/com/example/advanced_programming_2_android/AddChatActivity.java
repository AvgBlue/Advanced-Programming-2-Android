package com.example.advanced_programming_2_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddChatActivity extends AppCompatActivity {
    private EditText etUsername;
    private Button btnAddChat;
    private ImageView settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat);

        etUsername = findViewById(R.id.edAddChatUsername);
        btnAddChat = findViewById(R.id.btnAddChat);
        settings = findViewById(R.id.settings_action_bar);

        btnAddChat.setOnClickListener(view -> {
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        });

        settings.setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}