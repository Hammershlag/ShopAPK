package com.example.shopapk.Activities.Settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapk.Activities.LoginActivity;
import com.example.shopapk.R;

import static com.example.shopapk.Data.Data.*;

public class ChangePasswordActivity extends AppCompatActivity {

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_activity);


        findViewById(R.id.newpasswordconfirmation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText old_pass = findViewById(R.id.oldpassword);
                EditText new_pass = findViewById(R.id.newpassword);
                EditText new_pass_confirmation = findViewById(R.id.newpasswordconfirmation);


                if (TextUtils.equals(old_pass.getText(), current_user.getPassword()))
                {
                    if (new_pass.length() >= 8)
                    {
                        if (TextUtils.equals(new_pass.getText(), new_pass_confirmation.getText()))
                        {
                            current_user.setPassword(new_pass.getText().toString());
                            Intent intent = new Intent(context, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        Toast toast = Toast.makeText(ChangePasswordActivity.this, "New password must be at least 8 characters long", 10);
                        toast.show();
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(ChangePasswordActivity.this, "It's not your old password", 10);
                    toast.show();
                }
            }
        });
    }
}
