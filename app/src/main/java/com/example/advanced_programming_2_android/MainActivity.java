package com.example.advanced_programming_2_android;

import static com.example.advanced_programming_2_android.settings.ConfigParser.getDefaultServerAddress;
import static com.example.advanced_programming_2_android.settings.ConfigParser.getDefaultTheme;
import static com.example.advanced_programming_2_android.settings.ThemeManager.applyTheme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.example.advanced_programming_2_android.api.FirebaseTokenAPI;
import com.example.advanced_programming_2_android.api.UserAPI;
import com.example.advanced_programming_2_android.database.AppDB;
import com.example.advanced_programming_2_android.database.Storage;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModel;

public class MainActivity extends AppCompatActivity {
    private Button btnSignIn;
    private Button btnRegister;
    private ImageView settingsButton;

    private PreferencesViewModel preferencesViewModel;

    private Storage storage;

    public static final int PERMISSION_REQUEST_CODE = 1;

    public static final String POST_NOTIFICATION_PERMISSION = "android.permission.POST_NOTIFICATIONS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestNotificationPermission();

        AppDB appdb=AppDB.getInstance(this);

        btnSignIn = findViewById(R.id.sign_in_btn);
        btnRegister = findViewById(R.id.register_btn);
        settingsButton = findViewById(R.id.settings_action_bar);

        //PreferencesViewModelFactory factory = new PreferencesViewModelFactory(getApplicationContext());
        MyApplication myApp = (MyApplication) getApplication();
        preferencesViewModel = new ViewModelProvider(myApp).get(PreferencesViewModel.class);
        if(preferencesViewModel.getThemeLiveData(this).getValue() == 0){
            Log.d("MY_ACTIVITY", "1) IN setTheme");
            preferencesViewModel.setTheme(this, getDefaultTheme(this));
        }
        if(preferencesViewModel.getServerAddressLiveData(this).getValue() == ""){
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

            String url = preferencesViewModel.getServerAddressLiveData(this).getValue();
            UserAPI userAPI = new UserAPI(url);
            
            userAPI.getUserByUsername(username, token);
            userAPI.getUserMutableLiveData().observe(this, user->{
                Log.d("MY_ACTIVITY","1) myUser.getValue: "+ user);
                if(user != null){
                    retrieveFCMToken(username, url);
                    Intent intent = new Intent(this, ChatActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    preferencesViewModel.setToken(this, null);
                    preferencesViewModel.setUsername(this, null);
                    storage = Storage.getStorage(this);
                    storage.clearStorage();
                }
            });
        }

    }


    private void retrieveFCMToken(String username, String url) {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                return;
            }

            // Get the token
            String token = task.getResult();
            FirebaseTokenAPI firebaseTokenAPI = new FirebaseTokenAPI(url);
            firebaseTokenAPI.postFirebaseToken(token, username);

            // Save the token or send it to your server
        });
}

    public void requestNotificationPermission() {
        ActivityCompat.requestPermissions (this,
                new String[] {POST_NOTIFICATION_PERMISSION},
                PERMISSION_REQUEST_CODE);

    }
}







