package com.example.advanced_programming_2_android.database;

import java.util.List;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;



// represent the response of GET /api/Chats
@Entity(tableName = "chats")
public class Chat {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private int id;
    @SerializedName("user")
    private User user;
    @SerializedName("lastMessage.id")
    private int lastMessageId;
    @SerializedName("lastMessage.created")
    private String lastMessageCreated;
    @SerializedName("lastMessage.content")
    private String lastMessageContent;

    public Chat(@NonNull int id, User user, int lastMessageId, String lastMessageCreated, String lastMessageContent) {
        this.id = id;
        this.user = user;
        this.lastMessageId = lastMessageId;
        this.lastMessageCreated = lastMessageCreated;
        this.lastMessageContent = lastMessageContent;
    }

    @Ignore
    public Chat(@NonNull int id, User user) {
        this.id = id;
        this.user = user;
    }

    // Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(int lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public String getLastMessageCreated() {
        return lastMessageCreated;
    }

    public void setLastMessageCreated(String lastMessageCreated) {
        this.lastMessageCreated = lastMessageCreated;
    }

    public String getLastMessageContent() {
        return lastMessageContent;
    }

    public void setLastMessageContent(String lastMessageContent) {
        this.lastMessageContent = lastMessageContent;
    }
}

