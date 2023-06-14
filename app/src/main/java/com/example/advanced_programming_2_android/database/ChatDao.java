package com.example.advanced_programming_2_android.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ChatDao {
    @Insert
    void insert(ChatDatabase... chatDatabase);

    @Query("SELECT * FROM chats")
    List<ChatDatabase> getAllChats();

    //get chat by id
    @Query("SELECT * FROM chats WHERE id = :id")
    ChatDatabase getChat(int id);

    @Update
    void update(ChatDatabase... chatDatabase);

    @Delete
    void delete(ChatDatabase... chatDatabase);


}
