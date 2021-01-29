package com.example.shopapk.Activities.Settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapk.Activities.MainActivity;
import com.example.shopapk.R;

public class SettingsActivity extends AppCompatActivity {

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        Switch darkModeSwitch = findViewById(R.id.dark_mode_settings);
        Boolean darkModeState = darkModeSwitch.isSelected();

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
