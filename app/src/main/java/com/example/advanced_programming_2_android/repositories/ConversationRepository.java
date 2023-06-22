package com.example.advanced_programming_2_android.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.advanced_programming_2_android.api.ChatsAPI;
import com.example.advanced_programming_2_android.database.AppDB;
import com.example.advanced_programming_2_android.database.Conversation;
import com.example.advanced_programming_2_android.database.ConversationDao;

public class ConversationRepository {
    private ConversationDao conversationDao;
    private ConversationData conversationData;
    private ChatsAPI chatsAPI;
    private String token;
    private int conversationId;

    public ConversationRepository(int conversationId, String token) {
        AppDB db = AppDB.getInstance();
        conversationDao = db.getConversationDao();
        conversationData = new ConversationData();
        chatsAPI = new ChatsAPI();
        this.token = token;
        this.conversationId = conversationId;
    }

    class ConversationData extends MutableLiveData<Conversation> {
        public ConversationData() {
            super();
            Conversation conversation = conversationDao.getConversationById(conversationId);
            setValue(conversation);
        }

        @Override
        protected void onActive() {
            super.onActive();

            new Thread(() -> {
                conversationData.postValue(conversationDao.getConversationById(conversationId));
            }).start();
            chatsAPI.getChatById(conversationData, conversationId, token);
        }
    }

    public LiveData<Conversation> getConversation() {
        return conversationData;
    }

    public void getConversationById(){
        chatsAPI.getChatById(conversationData, conversationId, token);
    }

}
