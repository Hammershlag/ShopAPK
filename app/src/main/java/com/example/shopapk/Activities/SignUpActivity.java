package com.example.shopapk.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapk.R;
import com.example.shopapk.Classes.User;

import static com.example.shopapk.Data.Data.*;


public class SignUpActivity extends AppCompatActivity {

    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        findViewById(R.id.regButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Login Button clicked");
                EditText text = (EditText)findViewById(R.id.emailtextfield2);
                String value = text.getText().toString();
                CheckBox reg_checkbox = (CheckBox) findViewById(R.id.regulations);
                EditText pass1 = (EditText) findViewById(R.id.passEntry);
                EditText pass2 = (EditText) findViewById(R.id.passConfirmationField);
                if (pass1.length() >= 8)
                {
                    if(isValidEmail(value) && TextUtils.equals(pass1.getText(), pass2.getText()) && reg_checkbox.isChecked())
                    {
                        addElementToUsersArray(new User(value, pass1.getText().toString()));
                        //Data.users[0] = new User(value, pass1.getText().toString());
                        Intent intent = new Intent(context, LoginActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast toast = Toast.makeText(SignUpActivity.this, "Wrong data", 10);
                        toast.show();
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(SignUpActivity.this, "Password must be at least 8 long", 10);
                    toast.show();
                }

            }
        });

    }
}
