package com.example.advanced_programming_2_android.database;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private ChatDao chatDao;
    private MessageDao messageDao;
    private ConversationDao conversationDao;

    private UserDao userDao;

    private AppDB db;

    private static Storage storage = null;

    private Storage(Context context){
        db = AppDB.getInstance(context);
        chatDao = db.getChatDao();
        messageDao = db.getMessageDao();
        conversationDao = db.getConversationDao();
        userDao = db.getUserDao();
    }

    public static Storage getStorage(Context context){
        if(storage == null){
            storage = new Storage(context);
        }
        return storage;
    }

    public void clearStorage(){
        deleteAllChats();
    }

    public List<Chat> getAllChats(){
        List<Chat> chats = chatDao.getAllChats();
        if(chats == null){
            return new ArrayList<>();
        }
        return chats;
    }

    public void insertNewChat(Chat chat) {
        // Get all the chats from the database
        List<Chat> chats = chatDao.getAllChats();

        // Add the new chat to the list
        boolean isNew = true;
        for (Chat c : chats) {
            if (c.getId() == chat.getId()) {
                isNew = false;
                break;
            }
        }
        if(isNew){
            chats.add(chat);

            // Convert the list back to an array of Chat objects
            Chat[] chatArray = chats.toArray(new Chat[0]);

            // Insert the updated array of chats into the database
            chatDao.insert(chatArray);
        }
    }

    public void deleteAllChats() {
        // Get all the chats from the database
        List<Chat> chats = chatDao.getAllChats();

        // Convert the list to an array of Chat objects
        Chat[] chatArray = chats.toArray(new Chat[0]);

        // Delete all chats from the database
        chatDao.delete(chatArray);
    }

    public void updateChats(List<Chat> chats){
        List<Chat> dbChats = chatDao.getAllChats();
        HashMap<Integer, Chat> newChatsMap = new HashMap<>();

        Log.d("MY_ACTIVITY", "9) dbChats: ");
        printAllChats(dbChats);

        for(Chat chat : chats){
            boolean wasEdited = false;
            for(Chat dbChat : dbChats ){
                if(dbChat.getId() == chat.getId()){
                    dbChat.setLastMessage(chat.getLastMessage());
                    wasEdited = true;
                    break;
                }
            }
            // check if a the chat was not edited, so its new
            if (!wasEdited) {
                newChatsMap.put(chat.getId(), chat);
            }
        }

        // Convert the list to an array of Chat objects
        Chat[] chatArray = dbChats.toArray(new Chat[0]);

        // update all chats from the database
        chatDao.update(chatArray);

        // Enter all the new chats
        Chat[] newChatArray = new ArrayList<>(newChatsMap.values()).toArray(new Chat[0]);

        Log.d("MY_ACTIVITY", "9) newChatArray: ");
        printAllChats(newChatArray);

        chatDao.insert(newChatArray);
    }

    public void printAllChats(List<Chat> chats){
        for(Chat c: chats){
            Log.d("MY_ACTIVITY", "9)"+ c.toString());
        }
    }

    public void printAllChats(Chat[] chats){
        for(Chat c: chats){
            Log.d("MY_ACTIVITY", "9)"+ c.toString());
        }
    }
}