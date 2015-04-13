package com.lhsystems.coffeereporter.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.method.DateTimeKeyListener;

import java.util.ArrayList;
import java.util.List;

import com.lhsystems.coffeereporter.db.entity.User;


public class UserDao extends AbstractDao<User> {

    public UserDao(Context context) {
        super(context, User.class);

    }


    public boolean checkIfExist(String code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USERS WHERE "+User.COLUMNS[4] +" =?", new String[]{code});
        return cursor.moveToFirst();
    }


    @Override
    public User cursorToObj(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getInt(0));
        user.setFirstName(cursor.getString(1));
        user.setLastName(cursor.getString(2));
        user.setEmail(cursor.getString(3));
        user.setCode(cursor.getString(4));
        return user;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + User.TABLE_NAME + "("
                + User.COLUMNS[0] + " INTEGER PRIMARY KEY AUTOINCREMENT," + User.COLUMNS[1] + " TEXT,"
                +  User.COLUMNS[2] + " TEXT," +  User.COLUMNS[3] + " TEXT,"+User.COLUMNS[4] +" TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public ContentValues entityToContentValues(User entity) {
        ContentValues cv = new ContentValues();
        // {"id","firstName","lastName","email","code"};
        cv.put(User.COLUMNS[1], entity.getFirstName());
        cv.put(User.COLUMNS[2], entity.getLastName());
        cv.put(User.COLUMNS[3], entity.getEmail());
        cv.put(User.COLUMNS[4], entity.getCode());
        return cv;
    }
}
