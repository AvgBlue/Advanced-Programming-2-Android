package com.example.advanced_programming_2_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddChatActivity extends AppCompatActivity {
    private EditText etUsername;
    private Button btnAddChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat);

        etUsername = findViewById(R.id.edAddChatUsername);
        btnAddChat = findViewById(R.id.btnAddChat);

        btnAddChat.setOnClickListener(view -> {
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        });
    }
}