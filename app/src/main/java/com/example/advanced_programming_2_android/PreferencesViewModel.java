package com.example.advanced_programming_2_android;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PreferencesViewModel extends ViewModel {
    private MutableLiveData<String> usernameLiveData;
    private MutableLiveData<String> tokenLiveData;
    private MutableLiveData<String> passwordLiveData;
    private MutableLiveData<Integer> themeLiveData;
    private SharedPreferences sharedPreferences;

    public PreferencesViewModel(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    public MutableLiveData<String> getUsernameLiveData() {
        if (usernameLiveData == null) {
            usernameLiveData = new MutableLiveData<>();
            loadUsernameFromSharedPreferences();
        }
        return usernameLiveData;
    }

    public MutableLiveData<String> getTokenLiveData() {
        if (tokenLiveData == null) {
            tokenLiveData = new MutableLiveData<>();
            loadTokenFromSharedPreferences();
        }
        return tokenLiveData;
    }

    public MutableLiveData<String> getPasswordLiveData() {
        if (passwordLiveData == null) {
            passwordLiveData = new MutableLiveData<>();
            loadPasswordFromSharedPreferences();
        }
        return passwordLiveData;
    }

    public MutableLiveData<Integer> getThemeLiveData() {
        if (themeLiveData == null) {
            themeLiveData = new MutableLiveData<>();
            loadThemeFromSharedPreferences();
        }
        return themeLiveData;
    }

    public void setUsername(String username) {
        usernameLiveData.setValue(username);
        saveUsernameToSharedPreferences();
    }

    public void setToken(String token) {
        tokenLiveData.setValue(token);
        saveTokenToSharedPreferences();
    }

    public void setPassword(String password) {
        passwordLiveData.setValue(password);
        savePasswordToSharedPreferences();
    }

    public void setTheme(int theme) {
        themeLiveData.setValue(theme);
        saveThemeToSharedPreferences();
    }

    private void loadUsernameFromSharedPreferences() {
        String username = sharedPreferences.getString("username", "");
        usernameLiveData.setValue(username);
    }

    private void loadTokenFromSharedPreferences() {
        String token = sharedPreferences.getString("token", "");
        tokenLiveData.setValue(token);
    }

    private void loadPasswordFromSharedPreferences() {
        String password = sharedPreferences.getString("password", "");
        passwordLiveData.setValue(password);
    }

    private void loadThemeFromSharedPreferences() {
        int theme = sharedPreferences.getInt("theme", 0);
        themeLiveData.setValue(theme);
    }

    private void saveUsernameToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", usernameLiveData.getValue());
        editor.apply();
    }

    private void saveTokenToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", tokenLiveData.getValue());
        editor.apply();
    }

    private void savePasswordToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password", passwordLiveData.getValue());
        editor.apply();
    }

    private void saveThemeToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("theme", themeLiveData.getValue());
        editor.apply();
    }
}

//how to use:
// PreferencesViewModel preferencesViewModel = new ViewModelProvider(this).get(PreferencesViewModel.class);
// preferencesViewModel.getUsernameLiveData().observe(this, username -> {
//    //do something with username
// });
// preferencesViewModel.getTokenLiveData().observe(this, token -> {
//    //do something with token
// });
// preferencesViewModel.getPasswordLiveData().observe(this, password -> {
//    //do something with password
// });
// preferencesViewModel.getThemeLiveData().observe(this, theme -> {
//    //do something with theme
// });
// preferencesViewModel.setUsername(username); //this will trigger the observer above
// preferencesViewModel.setToken(token); //this will trigger the observer above
// preferencesViewModel.setPassword(password); //this will trigger the observer above
// preferencesViewModel.setTheme(theme); //this will trigger the observer above



