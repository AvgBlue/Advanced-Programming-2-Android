package com.example.advanced_programming_2_android.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MessageDao {
    @Insert
    void insert(Message message);

    @Query("SELECT * FROM messages")
    List<Message> getAllMessages();

    // get all messages sent by a specific user
    @Query("SELECT * FROM messages WHERE senderUsername = :username")
    List<Message> getAllMessagesByUser(String username);
    // get a massage by id
    @Query("SELECT * FROM messages WHERE id = :id")
    Message getMessageById(int id);
}
