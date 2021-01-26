package com.example.shopapk.Activities.Admin;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapk.Classes.Product;
import com.example.shopapk.Database.ProductsDatabaseHandler;
import com.example.shopapk.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    ListView list, list_head;
    ArrayList<HashMap<String, String>> mylist, mylist_title;
    ListAdapter adapter_title, adapter;
    HashMap<String, String> map1, map2;
    String[] pr_name = new String[100];
    String[] pr_des = new String[100];
    String[] pr_id = new String[100];
    final ProductsDatabaseHandler db = new ProductsDatabaseHandler(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_list_activity);

        int i  = 100, nr = 0;
        List<Product> products = db.getAllProducts();

        i = products.size();
        pr_name = new String[i];
        pr_des = new String[i];
        for (Product pr : products)
        {
            pr_name[nr] = pr.getName();
            pr_des[nr] = pr.getDescription();
            pr_id[nr] = String.valueOf(pr.getId());
            nr++;
        }
        list = findViewById(R.id.listView2);
        list_head = findViewById(R.id.listView1);

        showActivity();
    }
    public void showActivity() {

        mylist = new ArrayList<HashMap<String, String>>();
        mylist_title = new ArrayList<HashMap<String, String>>();

        map1 = new HashMap<String, String>();

        map1.put("slno", "DB ID");
        map1.put("one", " Name");
        map1.put("two", " Description");
        mylist_title.add(map1);



        try {
            adapter_title = new SimpleAdapter(this, mylist_title, R.layout.row,
                    new String[] { "slno", "one", "two" }, new int[] {
                    R.id.Slno, R.id.one, R.id.two });
            list_head.setAdapter(adapter_title);
        } catch (Exception e) {

        }

        for (int i = 0; i < pr_name.length; i++) {
            map2 = new HashMap<String, String>();

            map2.put("slno", pr_id[i]);
            map2.put("one", pr_name[i]);
            map2.put("two", pr_des[i]);
            mylist.add(map2);
        }


        try {
            adapter = new SimpleAdapter(this, mylist, R.layout.row,
                    new String[] { "slno", "one", "two" }, new int[] {
                    R.id.Slno, R.id.one, R.id.two });
            list.setAdapter(adapter);
        } catch (Exception e) {

        }
    }
}
