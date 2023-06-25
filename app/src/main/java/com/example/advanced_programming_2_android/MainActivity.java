package com.example.advanced_programming_2_android;

import static com.example.advanced_programming_2_android.settings.ThemeManager.applyTheme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.advanced_programming_2_android.api.UserAPI;
import com.example.advanced_programming_2_android.database.AppDB;
import com.example.advanced_programming_2_android.database.Settings;
import com.example.advanced_programming_2_android.database.SettingsDao;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModel;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModelFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private Button btnSignIn;
    private Button btnRegister;
    private ImageView settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDB appdb=AppDB.getInstance(this);

        retrieveFCMToken();

        btnSignIn = findViewById(R.id.sign_in_btn);
        btnRegister = findViewById(R.id.register_btn);
        settingsButton = findViewById(R.id.settings_action_bar);

        PreferencesViewModelFactory factory = new PreferencesViewModelFactory(getApplicationContext());
        PreferencesViewModel preferencesViewModel = new ViewModelProvider(this, factory).get(PreferencesViewModel.class);
        applyTheme(preferencesViewModel.getThemeLiveData().getValue());


        btnSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
        });

        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        settingsButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    private void retrieveFCMToken() {
        FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();
        Task<String> getTokenTask = firebaseMessaging.getToken();

        getTokenTask.addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    //TODO: clear the log before submission
                    Log.e("FCM Token", "Fetching FCM registration token failed", task.getException());
                    return;
                }

                // Get the FCM registration token
                String token = task.getResult();

                // Log the token
                //TODO: clear the log before submission


                // You can now use the token to send push notifications to this device
            }
        });
    }

}







