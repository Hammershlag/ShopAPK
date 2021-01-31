package com.example.shopapk.Data;

import com.example.shopapk.Classes.User;
import com.here.android.mpa.common.GeoCoordinate;

public class Data{
    public static double lat = 0.0;
    public static double lon = 0.0;
    public static GeoCoordinate currentLocation;
    public static int count = 0;
    public static User current_user;
    public static int current_user_number;
    public static String adres;
    public static int ctr = 0;
    public static boolean check_log = false;
    public static boolean darkModeCheck = false;
    public static boolean is_Dark_Mode_On = false;


    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

}
