package com.example.advanced_programming_2_android.viewModels;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.advanced_programming_2_android.R;

import com.example.advanced_programming_2_android.database.AppDB;
import com.example.advanced_programming_2_android.database.Settings;
import com.example.advanced_programming_2_android.database.SettingsDao;
import com.example.advanced_programming_2_android.settings.ConfigParser;

import java.util.Map;


public class PreferencesViewModel extends ViewModel {
    private MutableLiveData<String> usernameLiveData;
    private MutableLiveData<String> tokenLiveData;
    private MutableLiveData<String> passwordLiveData;
    private MutableLiveData<Integer> themeLiveData;
    private MutableLiveData<String> serverAddressLiveData;
    private static SharedPreferences sharedPreferences;

    private static PreferencesViewModel preferencesViewModel = null;

    private static Context context;
    private SettingsDao settingsDao;

    private PreferencesViewModel(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        usernameLiveData = new MutableLiveData<>();
        tokenLiveData = new MutableLiveData<>();
        passwordLiveData = new MutableLiveData<>();
        themeLiveData = new MutableLiveData<>();
        serverAddressLiveData = new MutableLiveData<>();

        settingsDao = AppDB.getInstance(context).getSettingsDao();
        Settings settings = settingsDao.getSettings();
        if (settings == null) {
            // Settings not initialized, create and insert into the database
            settings = new Settings();
            settingsDao.insert(settings);
            preferencesViewModel.setDefault();
        }
        else{
            setTheme(settingsDao.getTheme());
            setServerAddress(settingsDao.getServerAddress());
        }
    }

    public static PreferencesViewModel createPreferencesViewModel(Context context) {
        if (preferencesViewModel == null) {
            preferencesViewModel = new PreferencesViewModel(context);
            preferencesViewModel.setDefault();
        }
        return preferencesViewModel;
    }

    private void setDefault() {
        Map<String, String> configMap = ConfigParser.parseConfig(context, R.xml.config);
        String theme = configMap.get("theme");
        String port = configMap.get("server_address");
        setServerAddress(port);
        assert theme != null;
        setTheme(parseInt(theme));
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
        settingsDao.updateTheme(theme);
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
        settingsDao.updateServerAddress(serverAddress);
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
