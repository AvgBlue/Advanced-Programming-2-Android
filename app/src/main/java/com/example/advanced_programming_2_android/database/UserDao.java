package com.example.advanced_programming_2_android.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User... user);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("SELECT * FROM users WHERE username = :username")
    User getUserByUsername(String username);

    //get a user by a display name
    @Query("SELECT * FROM users WHERE displayName = :displayName")
    User getUserByDisplayName(String displayName);

    @Update
    void update(User... user);

    @Delete
    void delete(User... user);


}
