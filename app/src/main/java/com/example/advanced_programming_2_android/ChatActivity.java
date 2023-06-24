package com.example.advanced_programming_2_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log; // Added import statement
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.advanced_programming_2_android.api.UserAPI;
import com.example.advanced_programming_2_android.database.Chat;
import com.example.advanced_programming_2_android.database.User;
import com.example.advanced_programming_2_android.viewModels.ChatViewModel;
import com.example.advanced_programming_2_android.viewModels.ChatViewModelFactory;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModel;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private List<Chat> chats;
    private EditText etSearch;
    private FloatingActionButton addChatBtn;
    private ImageView settings;
    private ImageView logout;
    private ChatAdapter chatAdapter;
    private TextView displayName;
    private RoundedImageView profilePic;
    private Button btnSearch;
    private PreferencesViewModel preferencesViewModel;
    private ChatViewModel chatViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        PreferencesViewModelFactory factory = new PreferencesViewModelFactory(getApplicationContext());
        preferencesViewModel = new ViewModelProvider(this, factory).get(PreferencesViewModel.class);

        String token = preferencesViewModel.getTokenLiveData().getValue();
        String username = preferencesViewModel.getUsernameLiveData().getValue();

        ChatViewModelFactory factoryChat = new ChatViewModelFactory(token);
        chatViewModel = new ViewModelProvider(this, factoryChat).get(ChatViewModel.class);

        UserAPI userAPI = new UserAPI();
        userAPI.getUserByUsername(username, token);
        MutableLiveData<User> myUser = userAPI.getUserMutableLiveData();

        displayName = findViewById(R.id.displayName);
        profilePic = findViewById(R.id.profilePic);
        btnSearch = findViewById(R.id.btnSearch);
        etSearch = findViewById(R.id.etSearch);
        addChatBtn = findViewById(R.id.btnAddChat);
        settings = findViewById(R.id.settings_action_bar);
        logout = findViewById(R.id.logout_action_bar);

        displayName.setText(username);
        myUser.observe(this, user -> {
            Glide.with(this)
                    .load(Uri.parse(user.getProfilePic()))
                    .into(profilePic);
        });

        ListView lvChats =  findViewById(R.id.lvChats);
        chatViewModel.getChatsApi();
        chatViewModel.getChat().observe(this, chatList -> {
            chats = chatList;
            chatAdapter = new ChatAdapter(chats);
            lvChats.setAdapter(chatAdapter);
        });

        Log.d("ChatActivity", "ChatActivity created"); // Debug print

        // Todo: work on the search
        btnSearch.setOnClickListener(view -> {
            String nameToSearch = etSearch.getText().toString();
            if (!nameToSearch.equals("")) {
                List<Chat> filteredChats = new ArrayList<>();
                for (Chat chat : chats) {
                    if (chat.getUser().getDisplayName().toLowerCase().startsWith(nameToSearch.toLowerCase())) {
                        filteredChats.add(chat);
                    }
                }
                chatAdapter.updateChats(filteredChats);
            } else {
                chatAdapter.updateChats(chats);
            }
        });

        lvChats.setOnItemClickListener((parent, view, position, id) -> {
            Chat c = chats.get(position);
            chatAdapter.notifyDataSetChanged();

            // for now not for later
            Intent intent = new Intent(this, MessageActivity.class);
            intent.putExtra("profilePic", c.getUser().getProfilePic());
            intent.putExtra("displayName", c.getUser().getDisplayName());
            intent.putExtra("chatId", c.getId());
            startActivity(intent);
        });

        addChatBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddChatActivity.class);
            intent.putExtra("token", token);
            intent.putExtra("username", username);
            startActivity(intent);
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

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ChatActivity", "ChatActivity resumed"); // Debug print
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ChatActivity", "ChatActivity paused"); // Debug print
    }
}
