package com.example.shopapk.Activities.Settings;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapk.R;

import static com.example.shopapk.Data.Data.current_user_number;
import static com.example.shopapk.Data.Data.users;

public class UserInfoActivity extends AppCompatActivity {

    TextView username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_activity);

        username = findViewById(R.id.userinfoemail);
        username.setText(users[current_user_number].getUsername());

        password = findViewById(R.id.userinfopassword);
        password.setText(users[current_user_number].getPassword());

    }
}
