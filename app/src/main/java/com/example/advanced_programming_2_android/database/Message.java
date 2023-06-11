package com.example.advanced_programming_2_android.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "messages",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "username",
                childColumns = "senderUsername",
                onDelete = ForeignKey.CASCADE))
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date created;
    private String senderUsername;
    private String contact;

    public Message(Date created, String senderUsername, String contact) {
        this.created = created;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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


