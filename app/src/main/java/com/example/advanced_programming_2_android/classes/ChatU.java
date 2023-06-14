package com.example.advanced_programming_2_android.classes;

import com.example.advanced_programming_2_android.database.User;

import java.util.List;

public class ChatU {

    private int id;
    private List<User> users;
    private MessageU lastMessage;

    public ChatU(int id, List<User> users, MessageU lastMessage) {
        this.id = id;
        this.users = users;
        this.lastMessage = lastMessage;
    }

    public int getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public MessageU getLastMessage() {
        return lastMessage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setLastMessage(MessageU lastMessage) {
        this.lastMessage = lastMessage;
    }


}
