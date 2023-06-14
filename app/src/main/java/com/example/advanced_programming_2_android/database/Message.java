package com.example.advanced_programming_2_android.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "messages",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "username",
                childColumns = "senderUsername",
                onDelete = ForeignKey.CASCADE))
public class Message {
    @PrimaryKey
    @NonNull
    private int id;
    private String createdDate;
    private String senderUsername;
    private String contact;

    public Message(@NonNull int id, String createdDate, String senderUsername, String contact) {
        this.id = id;
        this.createdDate = createdDate;
        this.senderUsername = senderUsername;
        this.contact = contact;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}

