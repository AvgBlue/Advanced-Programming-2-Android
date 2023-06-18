package com.example.advanced_programming_2_android;

import android.util.Log;//TO DELETE LATHER
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistrationViewModel extends ViewModel {
    private MutableLiveData<Boolean> registrationSuccessLiveData = new MutableLiveData<>();
    private static final String TAG = "RegistrationViewModel";

    public void register(String username, String password, String displayName, String profilePic) {
        // Perform registration logic
        if (isValidRegistration(username, password, displayName, profilePic)) {
            // Registration successful
            // You can perform further actions here, such as saving user data or notifying the user

            // Example: Print registration details
            Log.d(TAG, "Registration successful:");
            Log.d(TAG, "Username: " + username);
            Log.d(TAG, "Password: " + password);
            Log.d(TAG, "Display Name: " + displayName);
            Log.d(TAG, "Profile Picture: " + profilePic);

            // Notify observers of successful registration
            registrationSuccessLiveData.setValue(true);
        } else {
            // Registration failed
            // You can handle the failure scenario here, such as displaying an error message
            Log.d(TAG, "Registration failed: Invalid input");

            // Notify observers of failed registration
            registrationSuccessLiveData.setValue(false);
        }
    }

    private boolean isValidRegistration(String username, String password, String displayName, String profilePic) {
        // Validate the registration inputs
        // Add your own logic here, such as checking for required fields or password complexity

        // For example, let's assume the registration is valid if all fields are non-empty
        return !username.isEmpty() && !password.isEmpty() && !displayName.isEmpty() && !profilePic.isEmpty();
    }

    public MutableLiveData<Boolean> getRegistrationSuccessLiveData() {
        return registrationSuccessLiveData;
    }
}
