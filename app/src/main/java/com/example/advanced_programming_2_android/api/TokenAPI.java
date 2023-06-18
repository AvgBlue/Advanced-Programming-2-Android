package com.example.advanced_programming_2_android.api;

import com.example.advanced_programming_2_android.MyApplication;
import com.example.advanced_programming_2_android.R;
import com.example.advanced_programming_2_android.classes.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokenAPI {
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;

    public TokenAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void login(LoginRequest loginRequest) {
        Call<String> call = webServiceAPI.login(loginRequest);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String token = response.body();
                    // Process the token data
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Handle the failure
            }
        });
    }
}
