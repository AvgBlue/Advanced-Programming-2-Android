package com.example.advanced_programming_2_android.repositories;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.advanced_programming_2_android.api.ChatsAPI;
import com.example.advanced_programming_2_android.database.AppDB;
import com.example.advanced_programming_2_android.database.Chat;
import com.example.advanced_programming_2_android.database.ChatDao;
import com.example.advanced_programming_2_android.database.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChatRepository {
    private ChatDao chatDao;
    private ChatListData chatListData;
    private ChatsAPI chatsAPI;
    private String token;

    public ChatRepository(String token) {
        AppDB db = AppDB.getInstance();
        chatDao = db.getChatDao();
        chatListData = new ChatListData();
        chatsAPI = new ChatsAPI();
        this.token = token;
    }

    class ChatListData extends MutableLiveData<List<Chat>> {
        public ChatListData() {
            super();
            List<Chat> chats = chatDao.getAllChats();
            setValue(chats);

        }

        @Override
        protected void onActive() {
            super.onActive();

            new Thread(() -> {
                chatListData.postValue(chatDao.getAllChats());
            }).start();
            chatsAPI.getChats(chatListData, token);
        }
    }

    public LiveData<List<Chat>> getAllChats() {
        return chatListData;
    }
    public LiveData<Boolean> getIsAddChatSucceeded() {
        return chatsAPI.getIsAddChatSucceeded();
    }

    public void getChatsApi() {
        chatsAPI.getChats(chatListData, token);
    }

    public void createChatApi(String chatWithUsername) {
        chatsAPI.createChat(chatWithUsername, token);
    }
}
