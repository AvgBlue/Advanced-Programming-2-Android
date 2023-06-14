package com.example.advanced_programming_2_android;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.advanced_programming_2_android.classes.Chat;

public class ChatViewModel extends ViewModel {
    private MutableLiveData<Chat> chatLiveData;

    public MutableLiveData<Chat> getChatLiveData() {
        if (chatLiveData == null) {
            chatLiveData = new MutableLiveData<>();
        }
        return chatLiveData;
    }

    public void setChat(Chat chat) {
        getChatLiveData().setValue(chat);
    }
}


//how to use:
// ChatViewModel chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
// chatViewModel.getChatLiveData().observe(this, chat -> {
//    //do something with chat
// });
// chatViewModel.setChat(chat); //this will trigger the observer above