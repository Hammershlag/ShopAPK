package com.example.shopapk.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapk.R;

import static com.example.shopapk.Data.Data.*;

public class LoginActivity extends AppCompatActivity {

    private Context context = this;
    private boolean check_log = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        findViewById(R.id.logbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Login Button clicked");
                EditText text = (EditText)findViewById(R.id.emailFieldLog);
                String value = text.getText().toString();
                EditText pass1 = (EditText) findViewById(R.id.passwordFieldLog);
                if((isValidEmail(value) || value.equals("admin")) && findViewById(R.id.passwordFieldLog) != null) {
                    for (int i = 0; i < users.length; i++) {
                        if (value.equals(users[i].getUsername()) && TextUtils.equals(pass1.getText(), users[i].getPassword())) {
                            count++;
                            check_log = true;
                            current_user = users[i];
                            current_user_number = i;
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                    if (!check_log) {
                        Toast toast = Toast.makeText(LoginActivity.this, "Wrong password or username", 10);
                        toast.show();
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(LoginActivity.this, "Wrong data", 10);
                    toast.show();
                }
            }
        });

        findViewById(R.id.signupbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}
