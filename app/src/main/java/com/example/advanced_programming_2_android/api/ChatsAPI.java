package com.example.advanced_programming_2_android.api;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.advanced_programming_2_android.MyApplication;
import com.example.advanced_programming_2_android.R;
import com.example.advanced_programming_2_android.database.Chat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatsAPI {

    private MutableLiveData<List<Chat>> chats;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;

    public ChatsAPI(MutableLiveData<List<Chat>> chatsListData) {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        this.chats = chatsListData;
    }

    public void getChats(String authorization) {
        Call<List<Chat>> call = webServiceAPI.getChats(authorization);
        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                if (response.isSuccessful()) {
                    List<Chat> chatList = response.body();
                    chats.postValue(chatList);
                } else {
                    //TODO: Handle the error
                }
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {
                Toast.makeText(MyApplication.context, "Failed to connect to the server", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void createChat(String username, String authorization) {
        Call<Void> call = webServiceAPI.createChat(username, authorization);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Chat created successfully
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MyApplication.context, "Failed to connect to the server", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getChatById(int id, String authorization) {
        Call<Chat> call = webServiceAPI.getChatById(id, authorization);
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful()) {
                    Chat chat = response.body();
                    // Process the chat data
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                Toast.makeText(MyApplication.context, "Failed to connect to the server", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deleteChat(int id, String authorization) {
        Call<Void> call = webServiceAPI.deleteChat(id, authorization);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Chat deleted successfully
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MyApplication.context, "Failed to connect to the server", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void createMessage(String message, int id, String authorization) {
        Call<Void> call = webServiceAPI.createMessage(message, id, authorization);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Message created successfully
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MyApplication.context, "Failed to connect to the server", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getMessagesByChatId(int id, String authorization) {
        Call<Chat> call = webServiceAPI.getMessagesByChatId(id, authorization);
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful()) {
                    Chat chat = response.body();
                    // Process the chat data with messages
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                Toast.makeText(MyApplication.context, "Failed to connect to the server", Toast.LENGTH_LONG).show();
            }
        });
    }
}
