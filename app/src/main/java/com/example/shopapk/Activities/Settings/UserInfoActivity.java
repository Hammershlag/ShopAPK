package com.example.shopapk.Activities.Settings;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapk.Classes.UserInfo;
import com.example.shopapk.Database.UserDatabaseHandler;
import com.example.shopapk.Database.UserInfoDatabaseHandler;
import com.example.shopapk.R;

import static com.example.shopapk.Data.Data.*;

public class UserInfoActivity extends AppCompatActivity {

    UserInfoDatabaseHandler udb = new UserInfoDatabaseHandler(this);
    UserDatabaseHandler db = new UserDatabaseHandler(this);
    TextView username, password, name, surname, ph_number;
    CheckBox show_pass;
    String character = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_activity);

        show_pass = findViewById(R.id.view_password);

        username = findViewById(R.id.userinfoemail);
        username.setText(current_user.getUsername());

        for (int i = 0; i < current_user.getPassword().length(); i++)
        {
            character += "*";
        }
        password = findViewById(R.id.userinfopassword);
        show_pass.setOnClickListener(getOnClickDoSomething(show_pass));
        if (show_pass.isChecked()) {
            password.setText(current_user.getPassword());
        }
        else
        {
            password.setText(character);
        }

        name = findViewById(R.id.NameInfo);
        surname = findViewById(R.id.SurnameInfo);
        ph_number = findViewById(R.id.PH_Info);

        UserInfo rem_user = udb.getUser(current_user_number);
        name.setText(rem_user.getName());
        surname.setText(rem_user.getSurname());
        ph_number.setText(rem_user.getPhone_number());

    }
    View.OnClickListener getOnClickDoSomething(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                if (show_pass.isChecked()) {
                    password.setText(current_user.getPassword());
                }
                else
                {
                    password.setText(character);
                }
            }
        };
    }

}
