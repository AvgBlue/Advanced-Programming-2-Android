package com.example.advanced_programming_2_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.advanced_programming_2_android.api.TokenAPI;
import com.example.advanced_programming_2_android.api.UserAPI;
import com.example.advanced_programming_2_android.classes.LoginRequest;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModel;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModelFactory;

public class LogInActivity extends AppCompatActivity {
    private EditText tvUsername;
    private EditText tvPassword;
    private Button btnLogin;
    private ImageView settings;
    private PreferencesViewModel preferencesViewModel;

    private static final String TAG = "LogInActivity";

    public static final int PERMISSION_REQUEST_CODE = 1;
    public static final String POST_NOTIFICATION_PERMISSION = "android.permission.POST_NOTIFICATIONS";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        PreferencesViewModelFactory factory = new PreferencesViewModelFactory(getApplicationContext());
        preferencesViewModel = new ViewModelProvider(this, factory).get(PreferencesViewModel.class);

        tvUsername = findViewById(R.id.usernameLogin);
        tvPassword = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.login_btn);
        settings = findViewById(R.id.settings_action_bar);

        TokenAPI tokenAPI = new TokenAPI();

        btnLogin.setOnClickListener(view -> {
            String username = tvUsername.getText().toString();
            String password = tvPassword.getText().toString();

            LoginRequest loginRequest = new LoginRequest(username, password);
            tokenAPI.login(loginRequest);

            MutableLiveData<String> tokenLiveData = tokenAPI.getTokenLiveData();
            tokenLiveData.observe(this, token -> {

                if (token != null) {
                    if(true){
                        preferencesViewModel.setToken(token);
                        preferencesViewModel.setUsername(username);
                        Intent intent = new Intent(this, ChatActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else{
                        String message = "We're sorry, but we cannot continue without the necessary permissions. Please grant the required permissions in the device settings to proceed.";

                        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            });
        });


        settings.setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    /*
    public boolean requestNotificationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{POST_NOTIFICATION_PERMISSION},
                PERMISSION_REQUEST_CODE);
        return (ContextCompat.checkSelfPermission(this, POST_NOTIFICATION_PERMISSION) == PackageManager.PERMISSION_GRANTED);
    }*/
}
