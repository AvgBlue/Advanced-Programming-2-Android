/*
package com.example.advanced_programming_2_android.viewModels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.advanced_programming_2_android.database.AppDB;
import com.example.advanced_programming_2_android.database.Chat;
import com.example.advanced_programming_2_android.database.UserDao;
import com.example.advanced_programming_2_android.database.MessageDao;
import com.example.advanced_programming_2_android.database.ChatDao;


import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {

    private ChatDao chatDao; // Declaration of ChatDao instance
    private MessageDao messageDao; // Declaration of MessageDao instance
    private UserDao userDao; // Declaration of UserDao instance
    private MutableLiveData<List<Chat>> chatLiveData; // Declaration of MutableLiveData that holds a list of Chat objects

    public ChatViewModel(Context context) {
        AppDB db = AppDB.getInstance(context); // Create an instance of the AppDB by accessing the singleton getInstance() method
        chatDao = db.getChatDao(); // Assign the ChatDao instance by calling the getChatDao() method from the AppDB instance
        messageDao = db.getMessageDao(); // Assign the MessageDao instance by calling the getMessageDao() method from the AppDB instance
        userDao = db.getUserDao(); // Assign the UserDao instance by calling the getUserDao() method from the AppDB instance
    }

    public MutableLiveData<List<Chat>> getChatLiveData() {
        if (chatLiveData == null) { // Check if chatLiveData is null
            chatLiveData = new MutableLiveData<>(); // Create a new instance of MutableLiveData
            loadChats(); // Load chats when the LiveData is first accessed
        }
        return chatLiveData; // Return the MutableLiveData object
    }

    private void loadChats() {
        List<Chat> chats = chatDao.getAllChats(); // Retrieve all chats from the ChatDao instance
        chatLiveData.setValue(chats); // Set the chatLiveData to the retrieved chats
    }

    public void setChat(Chat chat) {
        List<Chat> chats = chatLiveData.getValue(); // Retrieve the current value of the chatLiveData
        if (chats != null) { // Check if the chats list is not null
            chats.add(chat); // Add the new chat to the chats list
            chatLiveData.setValue(chats); // Update the chatLiveData with the modified chats list
        }
    }
}
*/