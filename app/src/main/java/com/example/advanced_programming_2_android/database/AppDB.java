package com.example.advanced_programming_2_android.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class, Message.class, Chat.class}, version = 8)
@TypeConverters({Converters.class})
public abstract class AppDB extends RoomDatabase {

    private static AppDB instance; // Add the instance variable
    public static synchronized AppDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDB.class, "database-name")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract UserDao getUserDao();
    public abstract MessageDao getMessageDao();
    public abstract ChatDao getChatDao();
}
