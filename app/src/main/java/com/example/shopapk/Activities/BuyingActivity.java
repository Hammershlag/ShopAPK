package com.example.shopapk.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapk.Database.DatabaseHandler;
import com.example.shopapk.Classes.Product;
import com.example.shopapk.R;

import java.util.List;

import static com.example.shopapk.Data.Data.*;

public class BuyingActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final DatabaseHandler db = new DatabaseHandler(this);

        setContentView(R.layout.buying_activity);
        }
    }
