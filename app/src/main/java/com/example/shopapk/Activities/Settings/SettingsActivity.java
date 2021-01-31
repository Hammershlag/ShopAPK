package com.example.shopapk.Activities.Settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.example.shopapk.Activities.MainActivity;
import com.example.shopapk.R;

import static com.example.shopapk.Data.Data.darkModeCheck;
import static com.example.shopapk.Data.Data.is_Dark_Mode_On;

public class SettingsActivity extends AppCompatActivity {

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        final Switch btnToggleDark = findViewById(R.id.dark_mode_settings);


        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

        if (isDarkModeOn) { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            btnToggleDark.setText("Disable Dark Mode");
            btnToggleDark.setChecked(true);
            is_Dark_Mode_On = true;
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            btnToggleDark.setText("Enable Dark Mode");
            btnToggleDark.setChecked(false);
            is_Dark_Mode_On = false;
        }

        if (!darkModeCheck)
        {
            darkModeCheck = true;
            Intent intent = new Intent(context, LoadingScreenActivity.class);
            startActivity(intent);

        }
        btnToggleDark.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view)
                    {
                        if (isDarkModeOn) {

                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                            editor.putBoolean("isDarkModeOn", false);
                            editor.apply();

                            btnToggleDark.setText("Enable Dark Mode");
                        }
                        else {

                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                            editor.putBoolean("isDarkModeOn", true);
                            editor.apply();

                            btnToggleDark.setText("Disable Dark Mode");
                        }
                    }
                });

        findViewById(R.id.changepasswordbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.userinfobutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserInfoActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.change_user_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserInfoSettingsActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.back_button_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
