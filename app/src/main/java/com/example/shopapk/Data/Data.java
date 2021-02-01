package com.example.shopapk.Data;

import com.example.shopapk.Classes.User;
import com.here.android.mpa.common.GeoCoordinate;

import java.util.Random;

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
    public static int leftLimit = 48;
    public static int rightLimit = 122;
    public static int targetStringLength = 32;


    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static String randomString() {

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        System.out.println(generatedString);
        return generatedString;
    }

}
