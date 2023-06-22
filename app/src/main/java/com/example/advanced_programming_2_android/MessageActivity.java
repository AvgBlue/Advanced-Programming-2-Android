package com.example.advanced_programming_2_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.advanced_programming_2_android.classes.MessageU;
import com.example.advanced_programming_2_android.database.Conversation;
import com.example.advanced_programming_2_android.database.Message;
import com.example.advanced_programming_2_android.database.User;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModel;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModelFactory;
import com.example.advanced_programming_2_android.viewModels.ConversationViewModel;
import com.example.advanced_programming_2_android.viewModels.ConversationViewModelFactory;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class MessageActivity extends AppCompatActivity {
    private ImageView settings;
    private PreferencesViewModel preferencesViewModel;
    private ConversationViewModel conversationViewModel;

    private ImageView settingsButton;
    private ImageButton sendButton;

    private EditText inputMessage;
    private MessageAdapter messageAdapter;

    private RecyclerView messagesRecycleView;

    private ImageView logout;

    private int chatId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        PreferencesViewModelFactory factory = new PreferencesViewModelFactory(getApplicationContext());
        preferencesViewModel = new ViewModelProvider(this, factory).get(PreferencesViewModel.class);

        String token = preferencesViewModel.getTokenLiveData().getValue();
        // Retrieve the profile picture URI and display name from the intent
        Uri profilePic = Uri.parse(getIntent().getStringExtra("profilePic"));
        String displayName = getIntent().getStringExtra("displayName");
        chatId = getIntent().getIntExtra("chatId", 0);

        ConversationViewModelFactory factoryConversation = new ConversationViewModelFactory(chatId, token);
        conversationViewModel = new ViewModelProvider(this, factoryConversation).get(ConversationViewModel.class);

        RoundedImageView rivProfilePic = findViewById(R.id.profilePic);
        TextView tvDisplayName = findViewById(R.id.displayName);
        settingsButton = findViewById(R.id.settings_action_bar);
        sendButton = findViewById(R.id.sentBtn);
        inputMessage = findViewById(R.id.inputMessage);
        messagesRecycleView = findViewById(R.id.chatRecycleView);
        logout = findViewById(R.id.logout_action_bar);

        String username = preferencesViewModel.getUsernameLiveData().getValue();

        // Load the profile picture into the RoundedImageView using Glide library
        Glide.with(this)
                .load(profilePic)
                .into(rivProfilePic);

        // Set the display name in the TextView
        tvDisplayName.setText(displayName);
// אנחנו אצרנו פה יש בעיה עם  יש בעיה שזה ריק
        conversationViewModel.getChatByIdApi();
        conversationViewModel.getConversation().observe(this, thisConversation -> {
            if (thisConversation != null) {
                // TODO - change
                String toastMessage = "live data changed";
                // inputMessage.setText(toastMessage.toString());
                Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                messageAdapter.setMessages(thisConversation.getMessages());
                messageAdapter.notifyDataSetChanged();
            }
        });

        Conversation conversation = conversationViewModel.getConversation().getValue();
        if(conversation == null){
            conversationViewModel.getChatByIdApi();
            conversation = conversationViewModel.getConversation().getValue();
        }
        List<Message> messeges = conversation.getMessages();
        messageAdapter = new MessageAdapter(messeges, username);
        messagesRecycleView.setAdapter(messageAdapter);
        messagesRecycleView.setLayoutManager(new LinearLayoutManager(this));

        settingsButton.setOnClickListener(view -> {
            // Open the SettingsActivity when the settings ImageView is clicked
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });

        sendButton.setOnClickListener(view -> {
            sendMessage();
            inputMessage.setText("");
        });

        logout.setOnClickListener(view -> {
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
        });
    }

    private void sendMessage() {
        String content = trimString(inputMessage.getText().toString());
        if (content.equals("")) {
            Toast.makeText(getApplicationContext(), "empty message", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO - to change
        String toastMessage = "You wanted to send: '" + content + "'.";
        Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();

        conversationViewModel.sendMessageApi(content, chatId);
    }

    public static String trimString(String input) {
        if (input == null) {
            return "";
        }

        String trimmedString = input.trim();
        trimmedString = trimmedString.replaceAll("(^\\n+)|(\\n+$)", "");

        return trimmedString;


    }

}