package com.example.advanced_programming_2_android.classes;

import com.example.advanced_programming_2_android.database.User;

import com.example.advanced_programming_2_android.database.Message;
import com.example.advanced_programming_2_android.database.UserDao;
import com.example.advanced_programming_2_android.database.MessageDao;


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


    public static MessageU convertToMessageU(int id, UserDao userDao ,MessageDao messageDao){
        Message message = messageDao.getMessageById(id);
        User senderUser = userDao.getUserByUsername(message.getSenderUsername());
        MessageU messageU = new MessageU(message.getId(),message.getCreatedDate(),senderUser,message.getContact());
        return messageU;
    }
}
