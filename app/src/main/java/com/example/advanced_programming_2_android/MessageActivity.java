package com.example.advanced_programming_2_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.advanced_programming_2_android.viewModels.ChatViewModel;
import com.example.advanced_programming_2_android.viewModels.ChatViewModelFactory;
import com.example.advanced_programming_2_android.viewModels.ConversationViewModel;
import com.example.advanced_programming_2_android.viewModels.ConversationViewModelFactory;
import com.example.advanced_programming_2_android.classes.MessageU;
import com.example.advanced_programming_2_android.database.User;
import com.example.advanced_programming_2_android.viewModels.MessageViewModel;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModel;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModelFactory;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;


public class MessageActivity extends AppCompatActivity {
    private ImageView logout;
    private PreferencesViewModel preferencesViewModel;
    private ConversationViewModel conversationViewModel;
    private ImageView settingsButton; // Declaring a private ImageView variable called settings

    private ImageButton sendButton;

    private EditText inputMessage;

    private RecyclerView messagesRecycleView;

    private MessageViewModel messageViewModel;

    private  PreferencesViewModel preferencesViewModel;

    private MessageAdapter messageAdapter;

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
        settingsButton = findViewById(R.id.settings_action_bar);
        logout = findViewById(R.id.logout_action_bar);
        sendButton = findViewById(R.id.sentBtn);
        inputMessage = findViewById(R.id.inputMessage);
        messagesRecycleView =  findViewById(R.id.chatRecycleView);        

        messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);

        String username = preferencesViewModel.getUsernameLiveData().getValue();

        List<MessageU> messeges = messageViewModel.getMessagesLiveData().getValue();
        messageAdapter = new MessageAdapter( messeges, username);
        messagesRecycleView.setAdapter(messageAdapter);
        messagesRecycleView.setLayoutManager(new LinearLayoutManager(this));

        messageViewModel.getMessagesLiveData().observe(this, new Observer<List<MessageU>>() {
            @Override
            public void onChanged(List<MessageU> messages) {

                // TODO - change
                String toastMessage ="live data changed";
                //inputMessage.setText(toastMessage.toString());
                Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                messageAdapter.setMessages(messages);
                messageAdapter.notifyDataSetChanged();
            }
        });

        // Load the profile picture into the RoundedImageView using Glide library
        Glide.with(this)
                .load(profilePic)
                .into(rivProfilePic);

        // Set the display name in the TextView
        tvDisplayName.setText(displayName);

        conversationViewModel.getChatByIdApi();
        conversationViewModel.getConversation().observe(this, conversation -> {
            if (conversation != null) {

            }
        });

        sendButton.setOnClickListener(view -> {
            String messageToSend = etInput.getText().toString();
            conversationViewModel.sendMessageApi(messageToSend, chatId);
        });

        settingsButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });

         logout.setOnClickListener(view -> {
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
        });
        
        sendButton.setOnClickListener(view -> {
            sendMessage();
            inputMessage.setText("");
        });
    }

    private void sendMessage() {
        String content = trimString(inputMessage.getText().toString());
        if(content.equals("")){
            Toast.makeText(getApplicationContext(), "empty message", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO - to change
        String toastMessage = "You wanted to send: '" + content + "'.";
        Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
        // TODO - to change the username, displayname, profilePic
        User user = new User("username", "displayname", "pic");
        // TODO - to change id, createdData
        messageViewModel.addMessage(new MessageU(1, "createdData", user, content));
    }

    public static String trimString(String input) {
        if (input == null) {
            return null;
        }

        String trimmedString = input.trim();
        trimmedString = trimmedString.replaceAll("(^\\n+)|(\\n+$)", "");

        return trimmedString;
    }
}