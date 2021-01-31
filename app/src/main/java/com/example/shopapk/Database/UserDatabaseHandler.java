package com.example.shopapk.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.shopapk.Classes.User;

import java.util.ArrayList;
import java.util.List;

// remote
// to merge

public class UserDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userManager";
    private static final String TABLE_PRODUCTS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_PASS = "password";

    public UserDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LOGIN + " TEXT,"
                + KEY_PASS + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);

        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN, user.getUsername());
        values.put(KEY_PASS, user.getPassword());

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS, new String[] { KEY_ID,
                        KEY_LOGIN, KEY_PASS }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        return userList;
    }
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public boolean checkIfUserExists(User user)
    {
        boolean empty = true;

        String selectQeury = "SELECT " + KEY_LOGIN + " FROM " + TABLE_PRODUCTS + " WHERE " + KEY_LOGIN + " = \'" + user.getUsername() + " /'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery(selectQeury, null);

        if (cur != null && cur.moveToFirst()) {
            empty = (cur.getInt (0) == 0);
        }
        cur.close();

        return empty;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN, user.getUsername());
        values.put(KEY_PASS, user.getPassword());

        return db.update(TABLE_PRODUCTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
        db.close();
    }


    public boolean isEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean empty = true;
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_PRODUCTS, null);
        if (cur != null && cur.moveToFirst()) {
            empty = (cur.getInt (0) == 0);
        }
        cur.close();

        return empty;
    }

}
