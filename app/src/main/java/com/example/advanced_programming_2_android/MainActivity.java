package com.example.advanced_programming_2_android;

import static com.example.advanced_programming_2_android.settings.ThemeManager.applyTheme;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.advanced_programming_2_android.api.UserAPI;
import com.example.advanced_programming_2_android.database.AppDB;
import com.example.advanced_programming_2_android.database.Settings;
import com.example.advanced_programming_2_android.database.SettingsDao;
import com.example.advanced_programming_2_android.viewModels.PreferencesViewModel;

public class MainActivity extends AppCompatActivity {
    private Button btnSignIn;
    private Button btnRegister;
    private ImageView settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = findViewById(R.id.sign_in_btn);
        btnRegister = findViewById(R.id.register_btn);
        settingsButton = findViewById(R.id.settings_action_bar);

        // set up the PreferencesViewModel
        PreferencesViewModel preferencesViewModel = new ViewModelProvider(this).get(PreferencesViewModel.class);
        preferencesViewModel.setDefault(this);
        MutableLiveData<Integer> theme = preferencesViewModel.getThemeLiveData(this);
        if(theme.getValue() != null){
            applyTheme(theme.getValue());
        }


        // read the information from the database
        SettingsDao settingsDao = AppDB.getInstance(this).getSettingsDao();
        Settings settings = settingsDao.getSettings();
        if (settings == null) {
            // Settings not initialized, create and insert into the database
            settings = new Settings();
            settingsDao.insert(settings);
        }
        else{
            preferencesViewModel.setServerAddress(this, settingsDao.getServerAddress());
            preferencesViewModel.setTheme(this, settingsDao.getTheme());
        }



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
}







