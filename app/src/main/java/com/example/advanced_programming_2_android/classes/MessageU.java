package com.example.advanced_programming_2_android.classes;

import com.example.advanced_programming_2_android.database.User;
public class MessageU {

    private int id;
    private String createdDate;
    private User senderUser;
    private String contact;

    public MessageU(int id, String createdDate, User senderUser, String contact) {
        this.id = id;
        this.createdDate = createdDate;
        this.senderUser = senderUser;
        this.contact = contact;
    }

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

    public User getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
