package com.example.advanced_programming_2_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.advanced_programming_2_android.database.Message;
import com.example.advanced_programming_2_android.database.User;
import com.example.advanced_programming_2_android.viewModels.ConversationViewModel;
import com.example.advanced_programming_2_android.viewModels.ConversationViewModelFactory;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModel;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModelFactory;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MessageActivity extends AppCompatActivity {
    private ImageView logout;
    private PreferencesViewModel preferencesViewModel;
    private ConversationViewModel conversationViewModel;
    private ImageView settingsButton; // Declaring a private ImageView variable called settings

    private ImageButton sendButton;

    private EditText inputMessage;

    private RecyclerView messagesRecycleView;

    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        MyApplication myApp = (MyApplication) getApplication();
        PreferencesViewModelFactory factory = new PreferencesViewModelFactory(getApplicationContext());
        preferencesViewModel = new ViewModelProvider(myApp, factory).get(PreferencesViewModel.class);

        String token = preferencesViewModel.getTokenLiveData(this).getValue();
        Uri profilePic = Uri.parse(getIntent().getStringExtra("profilePic"));
        String displayName = getIntent().getStringExtra("displayName");
        int chatId = getIntent().getIntExtra("chatId", 0);

        ConversationViewModelFactory factoryConversation = new ConversationViewModelFactory(chatId, token);
        conversationViewModel = new ViewModelProvider(this, factoryConversation).get(ConversationViewModel.class);

        RoundedImageView rivProfilePic = findViewById(R.id.profilePic);
        TextView tvDisplayName = findViewById(R.id.displayName);
        settingsButton = findViewById(R.id.settings_action_bar);
        logout = findViewById(R.id.logout_action_bar);
        sendButton = findViewById(R.id.sentBtn);
        inputMessage = findViewById(R.id.inputMessage);
        messagesRecycleView =  findViewById(R.id.chatRecycleView);

        String username = preferencesViewModel.getUsernameLiveData(this).getValue();
        messageAdapter = new MessageAdapter(new ArrayList<>(), username);
        messagesRecycleView.setAdapter(messageAdapter);
        messagesRecycleView.setLayoutManager(new LinearLayoutManager(this));

        // Load the profile picture into the RoundedImageView using Glide library
        Glide.with(this)
                .load(profilePic)
                .into(rivProfilePic);

        // Set the display name in the TextView
        tvDisplayName.setText(displayName);

        conversationViewModel.getChatByIdApi();
        conversationViewModel.getConversation().observe(this, conversation -> {
            if(conversation != null){
                messageAdapter.setMessages(conversation.getMessages());
                messageAdapter.notifyDataSetChanged();
                if (messageAdapter.getItemCount() > 0) {
                    messagesRecycleView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                }
            }
        });

        sendButton.setOnClickListener(view -> {
            String messageToSend = inputMessage.getText().toString();
            if (!messageToSend.equals("")) {
                conversationViewModel.sendMessageApi(messageToSend, chatId);
                inputMessage.setText("");
            }
            // scroll to the bottom of the list
            if (messageAdapter.getItemCount() > 0) {
                messagesRecycleView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
            }
            messageAdapter.notifyDataSetChanged();
        });


        settingsButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });

        logout.setOnClickListener(view -> {
            preferencesViewModel.setToken(this, "");
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

}