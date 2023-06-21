package com.example.advanced_programming_2_android.viewModels;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.advanced_programming_2_android.R;


public class PreferencesViewModel extends ViewModel {
    private MutableLiveData<String> usernameLiveData;
    private MutableLiveData<String> tokenLiveData;
    private MutableLiveData<Integer> themeLiveData;
    private MutableLiveData<String> serverAddressLiveData;
    private static SharedPreferences sharedPreferences;

    private static PreferencesViewModel preferencesViewModel = null;

    private PreferencesViewModel(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        usernameLiveData = new MutableLiveData<>();
        tokenLiveData = new MutableLiveData<>();
        themeLiveData = new MutableLiveData<>();
        serverAddressLiveData = new MutableLiveData<>();
    }

    public static PreferencesViewModel createPreferencesViewModel(Context context){
        if(preferencesViewModel == null){
            preferencesViewModel = new PreferencesViewModel(context);
            preferencesViewModel.setDefault();
        }
        return preferencesViewModel;
    }

    private void setDefault() {
        //TODO
        setServerAddress("TEST"); // change to actually read from config.xml
        setTheme(1); // change to actually read from config.xml

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

    private void saveThemeToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("theme", themeLiveData.getValue());
        editor.apply();
    }

    public MutableLiveData<String> getServerAddressLiveData() {
        if (serverAddressLiveData == null) {
            serverAddressLiveData = new MutableLiveData<>();
            loadServerAddressFromSharedPreferences();
        }
        return serverAddressLiveData;
    }

    public void setServerAddress(String serverAddress) {
        serverAddressLiveData.setValue(serverAddress);
        saveServerAddressToSharedPreferences();
    }

    private void loadServerAddressFromSharedPreferences() {
        String serverAddress = sharedPreferences.getString("serverAddress", "");
        serverAddressLiveData.setValue(serverAddress);
    }

    private void saveServerAddressToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("serverAddress", serverAddressLiveData.getValue());
        editor.apply();
    }
}
