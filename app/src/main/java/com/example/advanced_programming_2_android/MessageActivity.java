package com.example.advanced_programming_2_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.advanced_programming_2_android.viewModels.ChatViewModel;
import com.example.advanced_programming_2_android.viewModels.ChatViewModelFactory;
import com.example.advanced_programming_2_android.viewModels.ConversationViewModel;
import com.example.advanced_programming_2_android.viewModels.ConversationViewModelFactory;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModel;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModelFactory;
import com.makeramen.roundedimageview.RoundedImageView;

public class MessageActivity extends AppCompatActivity {
    private ImageView settings;
    private ImageView logout;
    private PreferencesViewModel preferencesViewModel;
    private ConversationViewModel conversationViewModel;
    private EditText etInput;
    private AppCompatImageView sendMessageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        PreferencesViewModelFactory factory = new PreferencesViewModelFactory(getApplicationContext());
        preferencesViewModel = new ViewModelProvider(this, factory).get(PreferencesViewModel.class);

        String token = preferencesViewModel.getTokenLiveData().getValue();
        Uri profilePic = Uri.parse(getIntent().getStringExtra("profilePic"));
        String displayName = getIntent().getStringExtra("displayName");
        int chatId = getIntent().getIntExtra("chatId", 0);

        ConversationViewModelFactory factoryConversation = new ConversationViewModelFactory(chatId, token);
        conversationViewModel = new ViewModelProvider(this, factoryConversation).get(ConversationViewModel.class);

        RoundedImageView rivProfilePic = findViewById(R.id.profilePic);
        TextView tvDisplayName = findViewById(R.id.displayName);
        settings = findViewById(R.id.settings_action_bar);
        logout = findViewById(R.id.logout_action_bar);
        etInput = findViewById(R.id.inputMessage);
        sendMessageButton = findViewById(R.id.sendMessageButton);

        Glide.with(this)
                .load(profilePic)
                .into(rivProfilePic);
        tvDisplayName.setText(displayName);

        conversationViewModel.getChatByIdApi();
        conversationViewModel.getConversation().observe(this, conversation -> {
            if (conversation != null) {

            }
        });

        sendMessageButton.setOnClickListener(view -> {
            String messageToSend = etInput.getText().toString();
            conversationViewModel.sendMessageApi(messageToSend, chatId);
        });

        settings.setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });

        logout.setOnClickListener(view -> {
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
        });
    }
}