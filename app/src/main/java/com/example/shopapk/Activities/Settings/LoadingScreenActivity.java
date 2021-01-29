package com.example.shopapk.Activities.Settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapk.Activities.LoginActivity;
import com.example.shopapk.Activities.MainActivity;
import com.example.shopapk.Classes.User;
import com.example.shopapk.Database.CurrentUserDatabaseHandler;
import com.example.shopapk.R;

import java.util.List;
import java.util.Timer;

import static com.example.shopapk.Data.Data.*;
import static com.example.shopapk.Data.Data.current_user_number;

public class LoadingScreenActivity extends AppCompatActivity {

    private Context context = this;
    CurrentUserDatabaseHandler cdb = new CurrentUserDatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen_activity);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (!cdb.isEmpty())
                {
                    check_log = true;
                    count++;
                    List<User> rem_user = cdb.getAllUsers();
                    cdb.getAllUsers();
                    User us = rem_user.get(0);
                    current_user = us;
                    current_user_number = us.getId();
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                }
            }

        }, 5000L);
    }
}
