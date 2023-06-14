package com.example.advanced_programming_2_android.database;

import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "chats")
public class ChatDatabase {
    @PrimaryKey
    @NonNull
    private int id;
    private List<String> usernames;
    private int lastMessageId;

    public ChatDatabase(@NonNull int id, List<String> usernames, int lastMessageId) {
        this.id = id;
        this.usernames = usernames;
        this.lastMessageId = lastMessageId;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public int getLastMessageId() {
        return lastMessageId;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    public void setLastMessageId(int lastMessageId) {
        this.lastMessageId = lastMessageId;
    }


}

