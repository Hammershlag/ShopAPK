package com.example.shopapk.Activities.Settings;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapk.Database.UserDatabaseHandler;
import com.example.shopapk.R;

import static com.example.shopapk.Data.Data.*;

public class UserInfoActivity extends AppCompatActivity {

    UserDatabaseHandler db = new UserDatabaseHandler(this);
    TextView username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_activity);

        username = findViewById(R.id.userinfoemail);
        username.setText(current_user.getUsername());

        password = findViewById(R.id.userinfopassword);
        password.setText(current_user.getPassword());

    }
}
