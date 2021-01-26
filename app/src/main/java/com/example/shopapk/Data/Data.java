package com.example.shopapk.Data;

import com.example.shopapk.Classes.Product;
import com.example.shopapk.Classes.User;
import com.here.android.mpa.common.GeoCoordinate;

import java.util.Arrays;


public class Data{
    public static double lat = 0.0;
    public static double lon = 0.0;
    public static GeoCoordinate currentLocation;
    public static int count = 0;
    public static Product pr = new Product(921931, ".$.$.", "...");
    public static Product products[] = {};
    public static User users[] = {new User("admin", "admin"), new User("tomekzbroszczyk@gmail.com", "asdfasdf")};
    public static User current_user;
    public static int current_user_number;
    public static String adres;
    public static int ctr = 0;

    int gitTest = 0;

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static User[] addElementToUsersArray(User elementToAdd) {
        User[] destArray = Arrays.copyOf(users, users.length + 1);
        destArray[destArray.length - 1] = elementToAdd;
        users = destArray;
        return users;
    }

    public static Product[] addElementToProductsArray(Product elementToAdd) {
        Product[] destArray = Arrays.copyOf(products, users.length + 1);
        destArray[destArray.length - 1] = elementToAdd;
        products = destArray;
        return products;
    }
}
