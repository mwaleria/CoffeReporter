package com.lhsystems.coffeereporter.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.lhsystems.coffeereporter.db.entity.Coffee;
import com.lhsystems.coffeereporter.db.entity.User;

/**
 * Created by Marcin on 2015-03-29.
 */
public class CoffeeDao  extends AbstractDao<Coffee> {

    public CoffeeDao(Context context) {
        super(context, Coffee.class);
        //context, DATABASE_NAME, null, DATABASE_VERSION)
    }

    @Override
    public ContentValues entityToContentValues(Coffee entity) {
        ContentValues cv = new ContentValues();
        cv.put(Coffee.COLUMNS[1],entity.getName());
        cv.put(Coffee.COLUMNS[2],entity.getPrice());
        return cv;
    }

    @Override
    public Coffee cursorToObj(Cursor cursor) {
        Coffee coffee = new Coffee();
        coffee.setId(cursor.getInt(0));
        coffee.setName(cursor.getString(1));
        coffee.setPrice(cursor.getFloat(2));
        return coffee;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COFFEES_TABLE = "CREATE TABLE " + Coffee.TABLE_NAME + "("
                + Coffee.COLUMNS[0] + " INTEGER PRIMARY KEY AUTOINCREMENT," + Coffee.COLUMNS[1] + " TEXT,"
                +  Coffee.COLUMNS[2] + " REAL)";
        db.execSQL(CREATE_COFFEES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
