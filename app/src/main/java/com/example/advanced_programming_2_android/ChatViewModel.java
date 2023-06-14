package com.example.advanced_programming_2_android;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.advanced_programming_2_android.database.AppDB;
import com.example.advanced_programming_2_android.database.Chat;
import com.example.advanced_programming_2_android.database.Message;
import com.example.advanced_programming_2_android.database.User;
import com.example.advanced_programming_2_android.database.UserDao;
import com.example.advanced_programming_2_android.database.MessageDao;
import com.example.advanced_programming_2_android.database.ChatDao;
import com.example.advanced_programming_2_android.classes.MessageU;
import com.example.advanced_programming_2_android.classes.ChatU;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {

    private ChatDao chatDao;
    private MessageDao messageDao;
    private UserDao userDao;
    private MutableLiveData<List<ChatU>> chatLiveData;

    public ChatViewModel(Context context) {
        AppDB db = AppDB.getInstance(context);
        chatDao = db.getChatDao();
        messageDao = db.getMessageDao();
        userDao = db.getUserDao();
    }

    public MutableLiveData<List<ChatU>> getChatLiveData() {
        if (chatLiveData == null) {
            chatLiveData = new MutableLiveData<>();
            loadChats(); // Load chats when the LiveData is first accessed
        }
        return chatLiveData;
    }

    private void loadChats() {
        List<Chat> chats = chatDao.getAllChats();
        List<ChatU> chatsU = new ArrayList<>();
        for (Chat chat : chats) {
            // Convert each Chat to ChatU
            ChatU chatU = ChatU.convertToChatU(chat, userDao,messageDao);
            chatsU.add(chatU);
        }
        // Set the chatLiveData to chatsU
        chatLiveData.setValue(chatsU);
    }

    public void setChat(ChatU chat) {
        List<ChatU> chats = chatLiveData.getValue();
        if (chats != null) {
            chats.add(chat);
            chatLiveData.setValue(chats);
        }
    }
}

// How to use the ChatViewModel:
// 1. Create an instance of the ChatViewModel by passing the context.
//    ChatViewModel chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

// 2. Observe the chatLiveData for changes in your activity or fragment:
//    chatViewModel.getChatLiveData().observe(this, chats -> {
//        // Update the UI with the new chats list
//        // For example, you can update a RecyclerView adapter with the new data
//        adapter.setChats(chats);
//    });

// 3. To add a new chat and trigger the observer above, call the setChat() method:
//    ChatU chat = new ChatU(chatId, users, lastMessage);
//    chatViewModel.setChat(chat);
//    // The observer will be notified and the UI will be updated with the new chat list
