package com.example.shopapk.Activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapk.Database.ProductsDatabaseHandler;
import com.example.shopapk.R;

public class BuyingActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ProductsDatabaseHandler db = new ProductsDatabaseHandler(this);

        setContentView(R.layout.buying_activity);
        }
    }
