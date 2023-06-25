package com.example.advanced_programming_2_android.repositories;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.advanced_programming_2_android.api.ChatsAPI;
import com.example.advanced_programming_2_android.database.AppDB;
import com.example.advanced_programming_2_android.database.Chat;
import com.example.advanced_programming_2_android.database.ChatDao;
import com.example.advanced_programming_2_android.database.UserDao;

import java.util.List;
import java.util.concurrent.Executors;

public class ChatRepository {
    private ChatDao chatDao;
    private UserDao userDao;
    private ChatListData chatListData;
    private ChatsAPI chatsAPI;
    private String token;

    public ChatRepository(String token) {
        AppDB db = AppDB.getInstance();
        chatDao = db.getChatDao();
        userDao = db.getUserDao();
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

    public void createChatRoom(String myUsername, String chatWithUsername) {
        Executors.newSingleThreadExecutor().execute(() -> {
            int newChatId = chatDao.getLastChatId();
            Chat newChat1 = new Chat(newChatId, userDao.getUserByUsername(myUsername), null);
            Chat newChat2 = new Chat(newChatId, userDao.getUserByUsername(chatWithUsername), null);
            chatDao.insert(newChat1);
            chatDao.insert(newChat2);
        });
    }
}
