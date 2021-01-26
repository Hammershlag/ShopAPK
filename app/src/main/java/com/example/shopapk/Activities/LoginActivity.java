package com.example.shopapk.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapk.Classes.User;
import com.example.shopapk.Database.UserDatabaseHandler;
import com.example.shopapk.R;

import java.util.List;

import static com.example.shopapk.Data.Data.*;

public class LoginActivity extends AppCompatActivity {

    UserDatabaseHandler db = new UserDatabaseHandler(this);
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
                EditText text = findViewById(R.id.emailFieldLog);
                String value = text.getText().toString();
                EditText pass1 = findViewById(R.id.passwordFieldLog);
                if((isValidEmail(value) || value.equals("admin")) && findViewById(R.id.passwordFieldLog) != null) {

                    Log.d("Reading: ", "Reading all products..");
                    List<User> user = db.getAllUsers();
                    for (User us : user)
                    {
                        if (value.equals(us.getUsername()) && TextUtils.equals(pass1.getText(), us.getPassword()))
                        {
                            check_log = true;
                            count++;

                            current_user = us;
                            current_user_number = us.getId();
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                            break;
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
