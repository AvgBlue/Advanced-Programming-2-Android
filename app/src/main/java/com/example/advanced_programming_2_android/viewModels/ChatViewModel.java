package com.example.advanced_programming_2_android.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.advanced_programming_2_android.database.Chat;
import com.example.advanced_programming_2_android.repositories.ChatRepository;

import java.util.List;

public class ChatViewModel extends ViewModel {
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

    public void createChatRoom(String myUsername, String chatWithUsername){
        chatRepository.createChatRoom(myUsername, chatWithUsername);
    }
}
