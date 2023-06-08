package com.example.advanced_programming_2_android;

import java.util.Date;

public class Message {
    private int id;
    private Date created;
    private User sender;
    private String contact;

    public Message(int id, Date created, User sender, String contact) {
        this.id = id;
        this.created = created;
        this.sender = sender;
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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
