package com.example.advanced_programming_2_android.api;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.advanced_programming_2_android.MyApplication;
import com.example.advanced_programming_2_android.R;
import com.example.advanced_programming_2_android.classes.FullUser;
import com.example.advanced_programming_2_android.database.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {
    private MutableLiveData<Boolean> isUsernameExist;

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public UserAPI() {

        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        isUsernameExist = new MutableLiveData<>();
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

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MyApplication.context, "Failed to connect to the server", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void createUser(FullUser user) {
        Call<FullUser> call = webServiceAPI.createUser(user);
        call.enqueue(new Callback<FullUser>() {
            @Override
            public void onResponse(Call<FullUser> call, Response<FullUser> response) {
                if (response.isSuccessful()) {
                    isUsernameExist.setValue(false);
                } else {
                    isUsernameExist.setValue(true);
                    Toast.makeText(MyApplication.context, "username already exist", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<FullUser> call, Throwable t) {
                Toast.makeText(MyApplication.context, "Failed to connect to the server", Toast.LENGTH_LONG).show();
            }
        });
    }

    public MutableLiveData<Boolean> getIsUsernameExist() {
        return isUsernameExist;
    }
}
