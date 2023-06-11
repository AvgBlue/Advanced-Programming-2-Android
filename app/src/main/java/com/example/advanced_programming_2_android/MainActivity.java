package com.example.advanced_programming_2_android;

//import androidx.appcompat.app.AppCompatActivity;
//
//
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.advanced_programming_2_android.database.AppDB;
import com.example.advanced_programming_2_android.database.Chat;
import com.example.advanced_programming_2_android.database.Message;
import com.example.advanced_programming_2_android.database.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get an instance of the database
        AppDB database = AppDB.getInstance(getApplicationContext());

//        // Example usage: Inserting data into the database
//        User user1 = new User("user1", "User 1", "profile_pic_1");
//        User user2 = new User("user2", "User 2", "profile_pic_2");
//        User user3 = new User("user3", "User 3", "profile_pic_3");
//
//        Message message1 = new Message(1, "2023-06-11", "user1", "Hello User 2");
//        Message message2 = new Message(2, "2023-06-11", "user2", "Hi User 1");
//
//        List<String> chatUsernames = new ArrayList<>();
//        chatUsernames.add("user1");
//        chatUsernames.add("user2");
//
//        Chat chat = new Chat(1, chatUsernames, 2);
//
//        database.getUserDao().insert(user1, user2, user3);
//        database.getMessageDao().insert(message1, message2);
//        database.getChatDao().insert(chat);



        // Example usage: Retrieving data from the database
        List<User> users = database.getUserDao().getAllUsers();
        List<Message> messages = database.getMessageDao().getAllMessages();
        List<Chat> chats = database.getChatDao().getAllChats();

        //log
        Log.d(TAG, "Log BOOM");

        // Print the retrieved data
        for (User user : users) {
            Log.d(TAG, "User: " + user.getUsername() + ", Display Name: " + user.getDisplayName());
        }

        for (Message message : messages) {
            Log.d(TAG, "Message ID: " + message.getId() + ", Sender: " + message.getSenderUsername());
        }

        for (Chat c : chats) {
            Log.d(TAG, "Chat ID: " + c.getId() + ", Usernames: " + c.getUsernames());
        }
    }
}





