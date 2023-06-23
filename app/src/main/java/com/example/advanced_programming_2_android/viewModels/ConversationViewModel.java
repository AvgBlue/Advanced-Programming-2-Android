package com.example.advanced_programming_2_android.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.advanced_programming_2_android.database.Conversation;
import com.example.advanced_programming_2_android.database.Message;
import com.example.advanced_programming_2_android.repositories.ConversationRepository;

import java.util.ArrayList;
import java.util.List;

public class ConversationViewModel extends ViewModel {
    private ConversationRepository conversationRepository;
    private LiveData<Conversation> conversation;

    public ConversationViewModel(int conversationId, String token) {
        conversationRepository = new ConversationRepository(conversationId, token);
        conversation = conversationRepository.getConversation();
    }

    public LiveData<Conversation> getConversation() {
        return conversation;
    }

    public void getChatByIdApi(){
        conversationRepository.getConversationById();
    }

    public void sendMessageApi(String message, int chatId){
        conversationRepository.sendMessageApi(message, chatId);
    }

    /*
    public void addMessage(Message message) {
        Conversation conversation = conversation.getValue();
        if (conversation != null) {
            List<Message> messagesList = conversation.getMessages();
            if (messagesList == null) {
                messagesList = new ArrayList<>();
            }
            messagesList.add(message);
            conversation.setMessages(messagesList);
            conversation.setValue(conversation);
        }
    }

     */
}
