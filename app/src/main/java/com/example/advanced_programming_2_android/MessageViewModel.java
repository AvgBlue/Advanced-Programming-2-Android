package com.example.advanced_programming_2_android;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.advanced_programming_2_android.classes.MessageU;
import com.example.advanced_programming_2_android.database.AppDB;
import com.example.advanced_programming_2_android.database.Conversation;
import com.example.advanced_programming_2_android.database.ConversationDao;
import com.example.advanced_programming_2_android.database.MessageDao;
import com.example.advanced_programming_2_android.database.UserDao;

import java.util.ArrayList;
import java.util.List;

public class MessageViewModel extends ViewModel {

    private MessageDao messageDao;

    private ConversationDao conversationDao;
    private UserDao userDao;

    private int chatId;

    private MutableLiveData<List<MessageU>> MessageLiveData;


    public MessageViewModel(Context context,int chatId) {
        AppDB db = AppDB.getInstance(context);
        messageDao = db.getMessageDao();
        conversationDao = db.getConversationDao();
        userDao = db.getUserDao();
        this.chatId=chatId;
    }

    public MutableLiveData<List<MessageU>> getMessageLiveData() {
        if (MessageLiveData == null) {
            MessageLiveData = new MutableLiveData<>();
            loadMessages(); // Load Messages when the LiveData is first accessed
        }
        return MessageLiveData;
    }

    public void loadMessages() {
        List<MessageU> messages = new ArrayList<>();
        Conversation conversation = conversationDao.getConversationById(chatId);
        for (int messageId : conversation.getMessageIds()) {
            // Convert each Message to MessageU
            MessageU message = MessageU.convertToMessageU(messageId, userDao, messageDao);
            messages.add(message);
        }
        MessageLiveData.setValue(messages);
    }

    public void setMessage(MessageU message) {
        List<MessageU> messages = MessageLiveData.getValue();
        if (messages != null) {
            messages.add(message);
            MessageLiveData.setValue(messages);
        }
    }

}

// How to use the MessageViewModel:
// 1. Create an instance of the MessageViewModel by passing the context.
//    MessageViewModel messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);

// 2. Observe the messageLiveData for changes in your activity or fragment:
//    messageViewModel.getMessageLiveData(conversationId).observe(this, messages -> {
//        // Update the UI with the new list of messages
//        // For example, you can update a RecyclerView adapter with the new data
//        adapter.setMessages(messages);
//    });



