package com.example.advanced_programming_2_android.api;

import com.example.advanced_programming_2_android.classes.LoginRequest;
import com.example.advanced_programming_2_android.database.Chat;
import com.example.advanced_programming_2_android.database.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface WebServiceAPI {
    @GET("api/Chats")
    Call<List<Chat>> getChats(@Header("authorization") String authorization);

    @POST("api/Chats")
    Call<Void> createChat(@Body String username, @Header("authorization") String authorization);

    @GET("api/Chats/{id}")
    Call<Chat> getChatById(@Path("id") int id, @Header("authorization") String authorization);

    @DELETE("api/Chats/{id}")
    Call<Void> deleteChat(@Path("id") int id, @Header("authorization") String authorization);

    @POST("api/Chats/{id}/messages")
    Call<Void> createMessage(@Body String message, @Path("id") int id, @Header("authorization") String authorization);

    @GET("api/Chats/{id}/messages")
    Call<Chat> getMessagesByChatId(@Path("id") int id, @Header("authorization") String authorization);

    @POST("api/Tokens")
    Call<String> login(@Body LoginRequest loginRequest);

    @GET("api/Users/{username}")
    Call<User> getUserByUsername(@Path("username") String username, @Header("authorization") String authorization);

    @POST("api/Users")
    Call<User> createUser(@Body User user);
}
