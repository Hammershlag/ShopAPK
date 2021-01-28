package com.example.shopapk.Activities;

import android.content.Context;
import android.content.Intent;
import android.location.*;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapk.Activities.Admin.AdminActivity;
import com.example.shopapk.Activities.Settings.SettingsActivity;
import com.example.shopapk.Classes.Product;
import com.example.shopapk.Classes.User;
import com.example.shopapk.Data.Data;
import com.example.shopapk.Database.CurrentUserDatabaseHandler;
import com.example.shopapk.R;
import com.here.android.mpa.common.*;
import com.here.android.mpa.mapping.*;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.location.LocationManager.NETWORK_PROVIDER;
import static com.example.shopapk.Data.Data.*;


public class MainActivity extends AppCompatActivity implements LocationListener {
    protected LocationManager locationManager;
    TextView txtLat;
    Map map;
    MapMarker defaultMarker = null;
    Location location = null;
    boolean aBoolean = true;
    final Context context = this;
    GeoCoordinate geoCoordinate;
    CurrentUserDatabaseHandler cdb = new CurrentUserDatabaseHandler(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!cdb.isEmpty())
        {
            check_log = true;
            count++;
            List<User> rem_user = cdb.getAllUsers();
            cdb.getAllUsers();
            User us = rem_user.get(0);
            current_user = us;
            current_user_number = us.getId();

        }

        if (count == 0) {
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        }

        txtLat = findViewById(R.id.textview1);

        if (lat != 0.0 && lon != 0.0)
        {
            txtLat.setText(adres);
        }

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestSingleUpdate(NETWORK_PROVIDER, this, Looper.myLooper());

        final AndroidXMapFragment mapFragment = (AndroidXMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapfragment);

        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(
                    OnEngineInitListener.Error error) {
                if (error == OnEngineInitListener.Error.NONE) {
                    map = mapFragment.getMap();


                    getAddress(context, lat, lon, txtLat);

                    defaultMarker = new MapMarker();
                    map.addMapObject(defaultMarker);

                    if (lat != 0.0)
                    {
                        geoCoordinate = new GeoCoordinate(lat, lon, 0.0);
                        map.setCenter(geoCoordinate, Map.Animation.NONE);
                        txtLat.setText(adres);
                        defaultMarker.setCoordinate(geoCoordinate);
                    }
                    else {
                        map.setCenter(new GeoCoordinate(51.76, 19.45, 0.0), Map.Animation.NONE);
                    }

                } else {
                    System.out.println("ERROR: Cannot initialize AndroidXMapFragment");
                    System.out.println(error);
                }
            }

        });

        findViewById(R.id.reqlocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctr = 1;
                map.removeAllMapObjects();
                recreate();
            }
        });

        findViewById(R.id.logoutbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> rem_users = cdb.getAllUsers();
                for (User rem_us : rem_users)
                {
                    cdb.deleteUser(rem_us);
                }
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.settings_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SettingsActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.passdatabutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if (current_user.getUsername().toLowerCase().equals("admin"))
                {
                    intent = new Intent(context, AdminActivity.class);
                }
                else
                {
                    intent = new Intent(context, BuyingActivity.class);
                }
                startActivity(intent);
            }
        });
    }



    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        txtLat = findViewById(R.id.textview1);

        findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
        if (aBoolean) {
            locateMe();
            aBoolean = false;
        }

        lat = location.getLatitude();
        lon = location.getLongitude();
        geoCoordinate = new GeoCoordinate(lat, lon, 0.0);
        getAddress(context, lat, lon, txtLat);
        txtLat.setText(adres);


    }

    public static void getAddress(Context context, double LATITUDE, double LONGITUDE, TextView txt){
        //Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);

            if (addresses != null && addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                adres = address;
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                txt.setText(adres);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    public void locateMe()
    {
        locationManager.requestSingleUpdate(NETWORK_PROVIDER, this, Looper.myLooper());
    }
}