package com.example.shopapk.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.shopapk.Classes.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class UserInfoDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "infoManager";
    private static final String TABLE_PRODUCTS = "userInfo";
    private static final String KEY_ID = "id";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_PH = "phone_nr";

    public UserInfoDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + " ("
                + KEY_ID + " INTEGER, "
                + KEY_LOGIN + " TEXT, "
                + KEY_NAME + " TEXT, "
                + KEY_SURNAME + " TEXT, "
                + KEY_PH + " TEXT " + " ) ";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);

        onCreate(db);
    }

    public void addUser(UserInfo user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, user.getId());
        values.put(KEY_LOGIN, user.getUsername());
        values.put(KEY_NAME, user.getName());
        values.put(KEY_SURNAME, user.getSurname());
        values.put(KEY_PH, user.getPhone_number());

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public UserInfo getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS, new String[] { KEY_ID,
                        KEY_LOGIN, KEY_NAME, KEY_SURNAME, KEY_PH }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        UserInfo user = new UserInfo(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return user;
    }

    public List<UserInfo> getAllUsers() {
        List<UserInfo> userList = new ArrayList<UserInfo>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                UserInfo user = new UserInfo();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUsername(cursor.getString(1));
                user.setName(cursor.getString(2));
                user.setSurname(cursor.getString(3));
                user.setPhone_number(cursor.getString(3));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        return userList;
    }

    public void updateName(UserInfo user, String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String updateQuery = "UPDATE " + TABLE_PRODUCTS + " SET " + KEY_NAME + " = \' " + name + " \' WHERE ID = " + user.getId() +" ;";
        db.execSQL(updateQuery);
    }

    public void updateSurame(UserInfo user, String surname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String updateQuery = "UPDATE " + TABLE_PRODUCTS + " SET " + KEY_SURNAME + " = \' " + surname + " \' WHERE ID = " + user.getId() +" ;";
        db.execSQL(updateQuery);
    }

    public void updatePhoneNumber(UserInfo user, String phone_number)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String updateQuery = "UPDATE " + TABLE_PRODUCTS + " SET " + KEY_PH + " = \' " + phone_number + " \' WHERE ID = " + user.getId() +" ;";
        db.execSQL(updateQuery);
    }

    public int updateUser(UserInfo user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN, user.getUsername());
        values.put(KEY_NAME, user.getName());
        values.put(KEY_SURNAME, user.getSurname());
        values.put(KEY_PH, user.getPhone_number());

        return db.update(TABLE_PRODUCTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }

    public void deleteUser(UserInfo user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
        db.close();
    }

    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
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
