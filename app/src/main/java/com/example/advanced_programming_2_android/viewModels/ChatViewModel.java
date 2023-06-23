package com.example.advanced_programming_2_android.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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

    /*
    public LiveData<List<Chat>> getChat() {
        if (chats == null) { // Check if chatLiveData is null
            chats = new MutableLiveData<>(); // Create a new instance of MutableLiveData
            loadChats(); // Load chats when the LiveData is first accessed
        }
        return chats; // Return the MutableLiveData object
    }
    */
    public LiveData<Boolean> getIsAddChatSucceeded() {
        return isAddChatSucceeded;
    }

    public void getChatsApi(){
        chatRepository.getChatsApi();
    }

    public void createChatApi(String chatWithUsername){
        chatRepository.createChatApi(chatWithUsername);
    }

    /*
    private void loadChats() {
        List<Chat> chats = chatDao.getAllChats(); // Retrieve all chats from the ChatDao instance
        chatLiveData.setValue(chats); // Set the chatLiveData to the retrieved chats
    }
    */

    /*
    public void setChat(Chat chat) {
        List<Chat> chats = chatLiveData.getValue(); // Retrieve the current value of the chatLiveData
        if (chats != null) { // Check if the chats list is not null
            chats.add(chat); // Add the new chat to the chats list
            chatLiveData.setValue(chats); // Update the chatLiveData with the modified chats list
        }
    }
    */
}
