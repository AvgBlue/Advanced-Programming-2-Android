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


