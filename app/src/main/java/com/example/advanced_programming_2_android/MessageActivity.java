package com.example.advanced_programming_2_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

public class MessageActivity extends AppCompatActivity {
    private ImageView settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        getIntent().getExtras();
        Uri profilePic = Uri.parse(getIntent().getStringExtra("profilePic"));
        String displayName = getIntent().getStringExtra("displayName");

        RoundedImageView rivProfilePic = findViewById(R.id.profilePic);
        TextView tvDisplayName = findViewById(R.id.displayName);
        settings = findViewById(R.id.settings_action_bar);

        Glide.with(this)
                .load(profilePic)
                .into(rivProfilePic);
        tvDisplayName.setText(displayName);

        settings.setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}