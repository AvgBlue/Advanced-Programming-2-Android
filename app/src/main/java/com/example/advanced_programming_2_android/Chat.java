package com.example.advanced_programming_2_android;

import java.util.List;

public class Chat {
    private int id;
    private List<User> users;
    private Message lastMessage;

    public Chat(int id, List<User> users, Message lastMessage) {
        this.id = id;
        this.users = users;
        this.lastMessage = lastMessage;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }
}
