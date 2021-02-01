package com.example.shopapk.Activities.Settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapk.Database.UserInfoDatabaseHandler;
import com.example.shopapk.R;

import static com.example.shopapk.Data.Data.current_user_number;

public class UserInfoSettingsActivity extends AppCompatActivity {

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_settings_activity);

        final UserInfoDatabaseHandler udb = new UserInfoDatabaseHandler(this);
        final EditText name = findViewById(R.id.nameEditText);
        final EditText surname = findViewById(R.id.surnameTextEdit);
        final EditText phone_number = findViewById(R.id.editTextPhone);
        Button confirmation = findViewById(R.id.confirmationButton);

        name.setText(udb.getUser(current_user_number).getName().trim());
        surname.setText(udb.getUser(current_user_number).getSurname().trim());
        phone_number.setText(udb.getUser(current_user_number).getPhone_number().trim());

        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    udb.updateName(udb.getUser(current_user_number), name.getText().toString().trim());
                    udb.updateSurname(udb.getUser(current_user_number), surname.getText().toString().trim());
                    udb.updatePhoneNumber(udb.getUser(current_user_number), phone_number.getText().toString().trim());

                Intent intent = new Intent(context, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }
}
