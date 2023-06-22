package com.example.advanced_programming_2_android.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.advanced_programming_2_android.database.Conversation;
import com.example.advanced_programming_2_android.repositories.ConversationRepository;

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
}
