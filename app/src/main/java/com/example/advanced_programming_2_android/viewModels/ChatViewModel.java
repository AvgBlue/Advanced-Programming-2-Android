package com.example.advanced_programming_2_android.viewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.advanced_programming_2_android.database.AppDB;
import com.example.advanced_programming_2_android.database.Chat;
import com.example.advanced_programming_2_android.database.UserDao;
import com.example.advanced_programming_2_android.database.MessageDao;
import com.example.advanced_programming_2_android.database.ChatDao;
import com.example.advanced_programming_2_android.repositories.ChatRepository;
import com.example.advanced_programming_2_android.repositories.UserRepository;


import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {


    private ChatDao chatDao;
    private MessageDao messageDao;
    private UserDao userDao;
    private MutableLiveData<List<Chat>> chatLiveData;
    private UserRepository userRepository;
    private ChatRepository chatRepository;
    private LiveData<List<Chat>> chats;
    //hiiii

    public ChatViewModel(String token) {
        chatRepository = new ChatRepository(token);
        chats = chatRepository.getAllChats();

    }

    public LiveData<List<Chat>> getChat() {
        return chats;
    }

    public void getChatsApi(){
        chatRepository.getChatsApi();
    }

}

    /*
    public MutableLiveData<List<Chat>> getChatLiveData() {
        if (chatLiveData == null) {
            chatLiveData = new MutableLiveData<>();
            loadChats(); // Load chats when the LiveData is first accessed
        }
        return chatLiveData;
    }

    private void loadChats() {
        List<Chat> chats = chatDao.getAllChats();
        // Set the chatLiveData to chats
        chatLiveData.setValue(chats);
    }

    public void setChat(Chat chat) {
        List<Chat> chats = chatLiveData.getValue();
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
*/