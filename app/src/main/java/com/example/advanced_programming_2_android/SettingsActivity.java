package com.example.advanced_programming_2_android;

import static com.example.advanced_programming_2_android.settings.ThemeManager.applyTheme;
import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.advanced_programming_2_android.viewModels.PreferencesViewModel;


public class SettingsActivity extends AppCompatActivity {

    private PreferencesViewModel preferencesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferencesViewModel = new ViewModelProvider(this).get(PreferencesViewModel.class);
        setServerAddress();
        setTheme();

        Button applyButton = findViewById(R.id.btnApply);
        applyButton.setOnClickListener(view ->{
            // Get the selected radio button's ID
            RadioGroup radioGroup = findViewById(R.id.rgTheme);
            int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

            // Find the selected radio button by ID
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            int radioButtonTag = parseInt(selectedRadioButton.getTag().toString());

            // Get the text from the EditText
            EditText editText = findViewById(R.id.edHttp);
            String editTextValue = editText.getText().toString();

            // Display a Toast with the selected radio button and EditText values
            String toastMessage = "Applied";
            Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();

            preferencesViewModel.setServerAddress(this, editTextValue);
            preferencesViewModel.setTheme(this, radioButtonTag);

            setServerAddress();
            setTheme();
        });
    }

    private void setServerAddress() {
        EditText addressEditText = findViewById(R.id.edHttp);
        addressEditText.setText(preferencesViewModel.getServerAddressLiveData(this).getValue());
    }

    private void setTheme() {
        RadioButton button = findViewById(R.id.rbLight);
        MutableLiveData<Integer> theme = preferencesViewModel.getThemeLiveData(this);
        Integer themeValue = theme.getValue();  // Store the value in a separate variable

        if (themeValue != null) {  // Add a null check here
            if (themeValue == 2) {
                button = findViewById(R.id.rbDark);
            }
            button.setChecked(true);
            applyTheme(themeValue);  // Invoke applyTheme() only if themeValue is not null
        }
    }

}