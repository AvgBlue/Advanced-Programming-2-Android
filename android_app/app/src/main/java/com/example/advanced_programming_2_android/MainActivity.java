package com.example.advanced_programming_2_android;

import static com.example.advanced_programming_2_android.settings.ConfigParser.getDefaultServerAddress;
import static com.example.advanced_programming_2_android.settings.ConfigParser.getDefaultTheme;
import static com.example.advanced_programming_2_android.settings.ThemeManager.applyTheme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.advanced_programming_2_android.api.UserAPI;
import com.example.advanced_programming_2_android.database.AppDB;
import com.example.advanced_programming_2_android.database.Settings;
import com.example.advanced_programming_2_android.database.SettingsDao;
import com.example.advanced_programming_2_android.database.User;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModel;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModelFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private Button btnSignIn;
    private Button btnRegister;
    private ImageView settingsButton;

    private PreferencesViewModel preferencesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDB appdb=AppDB.getInstance(this);

        retrieveFCMToken();

        btnSignIn = findViewById(R.id.sign_in_btn);
        btnRegister = findViewById(R.id.register_btn);
        settingsButton = findViewById(R.id.settings_action_bar);

        //PreferencesViewModelFactory factory = new PreferencesViewModelFactory(getApplicationContext());
        MyApplication myApp = (MyApplication) getApplication();
        preferencesViewModel = new ViewModelProvider(myApp).get(PreferencesViewModel.class);
        if(preferencesViewModel.getThemeLiveData(this).getValue() == null){
            Log.d("MY_ACTIVITY", "1) IN setTheme");
            preferencesViewModel.setTheme(this, getDefaultTheme(this));
        }
        if(preferencesViewModel.getServerAddressLiveData(this).getValue() == null){
            Log.d("MY_ACTIVITY", "1) IN setServerAddress");
            preferencesViewModel.setServerAddress(this, getDefaultServerAddress(this));
        }
        applyTheme(preferencesViewModel.getThemeLiveData(this).getValue());

        Log.d("MY_ACTIVITY", "1) PREFERENCES: "+ preferencesViewModel.toString());
        Log.d("MY_ACTIVITY", "1) THEME: "+preferencesViewModel.getThemeLiveData(this).getValue());
        Log.d("MY_ACTIVITY", "1) ADDRESS: "+preferencesViewModel.getServerAddressLiveData(this).getValue());
        Log.d("MY_ACTIVITY", "1) TOKEN: "+preferencesViewModel.getTokenLiveData(this).getValue());
        Log.d("MY_ACTIVITY", "1) USERNAME: "+preferencesViewModel.getUsernameLiveData(this).getValue());

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

        // if the token doesn't equal null is will try to connect the user and move to the chat screen
        String token =preferencesViewModel.getTokenLiveData(this).getValue();
        String username = preferencesViewModel.getUsernameLiveData(this).getValue();
        Log.d("MY_ACTIVITY", "1) TOKEN AGAIN: "+token);
        Log.d("MY_ACTIVITY", "1) USERNAME AGAIN: "+username);
        Log.d("MY_ACTIVITY","1) token != '' && username != '': "+((token != "" && username != "")?"ture":"false"));
        if (token != "" && username != "") {
            boolean tokenIsValid = false;
            UserAPI userAPI = new UserAPI();
            userAPI.getUserByUsername(username, token);
            userAPI.getUserMutableLiveData().observe(this, user->{
                Log.d("MY_ACTIVITY","1) myUser.getValue: "+ user);
                if(user != null){
                    Intent intent = new Intent(this, ChatActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    preferencesViewModel.setToken(this, null);
                    preferencesViewModel.setUsername(this, null);
                }
            });
        }
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







