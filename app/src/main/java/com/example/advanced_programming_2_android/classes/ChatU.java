package com.example.advanced_programming_2_android.classes;

import com.example.advanced_programming_2_android.database.Chat;
import com.example.advanced_programming_2_android.database.MessageDao;
import com.example.advanced_programming_2_android.database.User;
import com.example.advanced_programming_2_android.database.UserDao;

import java.util.ArrayList;
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

    public static ChatU convertToChatU(Chat chat, UserDao userDao, MessageDao messageDao){
        List<User> users = new ArrayList<>();
        List<String> usernames = chat.getUsernames();
        for (String username : usernames) {
            users.add(userDao.getUserByUsername(username));
        }
        MessageU lastMessage = MessageU.convertToMessageU(chat.getLastMessageId(),userDao,messageDao);
        ChatU chatU = new ChatU(chat.getId(),users,lastMessage);
        return chatU;
    }


}
