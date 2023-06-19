package com.example.advanced_programming_2_android.api;

import androidx.lifecycle.MutableLiveData;

import com.example.advanced_programming_2_android.MyApplication;
import com.example.advanced_programming_2_android.R;
import com.example.advanced_programming_2_android.database.User;
import com.example.advanced_programming_2_android.database.UserDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {
    private MutableLiveData<List<User>> userListData;
    private UserDao dao;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public UserAPI(MutableLiveData<List<User>> userListData, UserDao dao) {

        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void getUserByUsername(String username, String authorization) {
        Call<User> call = webServiceAPI.getUserByUsername(username, authorization);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    // Process the user data
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Handle the failure
            }
        });
    }

    public void createUser(User user) {
        Call<User> call = webServiceAPI.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User createdUser = response.body();
                    // Process the created user data
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Handle the failure
            }
        });
    }
}
