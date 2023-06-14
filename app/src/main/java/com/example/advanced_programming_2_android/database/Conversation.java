package com.example.advanced_programming_2_android.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "conversations")
public class Conversation {
    @PrimaryKey
    private int id;
    private List<Integer> messageIds;

    public Conversation(int id, List<Integer> messageIds) {
        this.id = id;
        this.messageIds = messageIds;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getMessageIds() {
        return messageIds;
    }

    public void setMessageIds(List<Integer> messageIds) {
        this.messageIds = messageIds;
    }
}
