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
    private LiveData<Boolean> isAddChatSucceeded;

    public ChatViewModel(String token) {
        chatRepository = new ChatRepository(token);
        chats = chatRepository.getAllChats();
        isAddChatSucceeded = chatRepository.getIsAddChatSucceeded();

    }

    public LiveData<List<Chat>> getChat() {
        return chats;
    }
    public LiveData<Boolean> getIsAddChatSucceeded() {
        return isAddChatSucceeded;
    }

    public void getChatsApi(){
        chatRepository.getChatsApi();
    }

    public void createChatApi(String chatWithUsername){
        chatRepository.createChatApi(chatWithUsername);
    }

}