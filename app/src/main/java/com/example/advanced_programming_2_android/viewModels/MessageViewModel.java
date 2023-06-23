package com.example.advanced_programming_2_android.viewModels;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



import com.example.advanced_programming_2_android.classes.MessageU;

import java.util.ArrayList;
import java.util.List;

public class MessageViewModel extends ViewModel {
    private MutableLiveData<List<MessageU>> messagesLiveData;

    public MessageViewModel() {
        messagesLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<MessageU>> getMessagesLiveData() {
        if (messagesLiveData == null) {
            messagesLiveData = new MutableLiveData<>();;
        }
        return messagesLiveData;
    }

    public void addMessage(MessageU message) {
        List<MessageU> messagesList = messagesLiveData.getValue();
        if (messagesList == null) {
            messagesList = new ArrayList<MessageU>();
        }
        messagesList.add(message);
        messagesLiveData.setValue(messagesList);
    }

}
