package com.example.shopapk.Activities.Admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapk.Classes.Product;
import com.example.shopapk.Database.ProductsDatabaseHandler;
import com.example.shopapk.R;

import java.util.List;

public class AdminActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Context context = this;
        final ProductsDatabaseHandler db = new ProductsDatabaseHandler(this);

            setContentView(R.layout.admin_buying_activity);
            final TextView test = findViewById(R.id.testTextView);
            final TextView test2 = findViewById(R.id.testView2);

            test.setText(String.valueOf("asjlnasl"));
            findViewById(R.id.addproductbuttonnd).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText name = findViewById(R.id.createProductEdit);
                    EditText description = findViewById(R.id.productdescriptionedit);
                    EditText price = findViewById(R.id.pr_price_edit);
                    boolean check = true;

                    Log.d("Reading: ", "Reading all products..");
                    List<Product> products = db.getAllProducts();
                    for (Product pr: products)
                    {
                        if (pr.getName().equals(name.getText().toString()) && pr.getDescription().equals(description.getText().toString())) {
                            check = false;
                            Toast toast = Toast.makeText(AdminActivity.this, "Item already exists", 10);
                            toast.show();
                            break;
                        }
                    }
                    if (check) {
                        db.addProduct(new Product(name.getText().toString(), description.getText().toString(), Float.parseFloat(price.getText().toString())));
                        test.setText(name.getText());
                        test2.setText(description.getText());
                    }
                    Log.d("Reading: ", "Reading all products..");
                    products = db.getAllProducts();
                    for (Product pr : products) {
                        String log = "Id: " + pr.getId() + " ,Name: " + pr.getName() + " ,Description: " +
                                pr.getDescription();
                        Log.d("Name: ", log);
                    }
                }
            });
            findViewById(R.id.remove_product).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText name = findViewById(R.id.createProductEdit);
                    EditText description = findViewById(R.id.productdescriptionedit);
                    boolean check_deleted = true;

                    Log.d("Reading: ", "Reading all products..");
                    List<Product> products = db.getAllProducts();
                    for (Product pr: products)
                    {
                        if (pr.getName().equals(name.getText().toString()) && pr.getDescription().equals(description.getText().toString()))
                        {
                            db.deleteProduct(pr);
                            Toast toast = Toast.makeText(AdminActivity.this, "Item deleted", 10);
                            toast.show();
                            check_deleted = false;
                            break;
                        }
                    }
                    if (check_deleted)
                    {
                        Toast toast = Toast.makeText(AdminActivity.this, "Item doesn't exist", 10);
                        toast.show();
                    }
                    Log.d("Reading: ", "Reading all products..");
                    products = db.getAllProducts();
                    for (Product pr : products) {
                        String log = "Id: " + pr.getId() + " ,Name: " + pr.getName() + " ,Description: " +
                                pr.getDescription() + ", Price: " + pr.getPrice();
                        Log.d("Name: ", log);
                    }
                }
            });
            findViewById(R.id.pr_list).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductListActivity.class);
                    startActivity(intent);
                }
            });
            findViewById(R.id.remove_all).setOnClickListener(new View.OnClickListener() {
                List<Product> products = db.getAllProducts();
                @Override
                public void onClick(View v) {
                    for (Product pr : products)
                    {
                        db.deleteProduct(pr);
                    }
                }
            });
        }
    }
