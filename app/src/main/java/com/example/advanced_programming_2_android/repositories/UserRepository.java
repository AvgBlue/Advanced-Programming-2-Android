package com.example.advanced_programming_2_android.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.advanced_programming_2_android.api.UserAPI;
import com.example.advanced_programming_2_android.database.AppDB;
import com.example.advanced_programming_2_android.database.Chat;
import com.example.advanced_programming_2_android.database.User;
import com.example.advanced_programming_2_android.database.UserDao;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;

public class UserRepository {
    private UserDao userDao;
    private UsersListData usersListData;
    private UserAPI userAPI;

    public UserRepository() {
        AppDB db = AppDB.getInstance();
        userDao = db.getUserDao();
        usersListData = new UsersListData();
        userAPI = new UserAPI();
    }

    class UsersListData extends MutableLiveData<List<User>> {
        public UsersListData() {
            super();
            setValue(new LinkedList<>());
        }

        @Override
        protected void onActive() {
            super.onActive();

            new Thread(() -> {
                usersListData.postValue(userDao.getAllUsers());
            }).start();
        }
    }

    public LiveData<List<User>> getAllUsers() {
        return usersListData;
    }

    public UserAPI getUserAPI() {
        return userAPI;
    }

    public void createUserRoom(User user) {
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.insert(user);
        });
    }

    //TODO to add the api call
}
