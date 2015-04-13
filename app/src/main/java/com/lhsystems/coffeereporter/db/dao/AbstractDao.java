package com.lhsystems.coffeereporter.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.lhsystems.coffeereporter.db.entity.Entity;

/**
 * Created by Marcin on 2015-03-31.
 */
public abstract class AbstractDao<T extends Entity> extends SQLiteOpenHelper implements Dao<T>  {

    private T fakeEntity = null;
    private final Class<T> clazz;

    public AbstractDao(final Context context,Class<T> clazz) {
        super(context, "COFFEE_REP", null, 1);
        this.clazz = clazz;
        try {
            fakeEntity = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T entity) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(fakeEntity.getTableName(), fakeEntity.getColumns()[0]+ " = ?",
                new String[] { String.valueOf(entity.getId()) });
        db.close();
    }

    @Override
    public T update(T entity) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(fakeEntity.getTableName(),this.entityToContentValues(entity), fakeEntity.getColumns()[0]+"=?" , new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public T create(T entity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = this.entityToContentValues(entity);
        long id = db.insert(fakeEntity.getTableName(),null,values);
        entity.setId(id);
        return  entity;
    }

    @Override
    public List<T> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + fakeEntity.getTableName(), null);
        List<T> entities = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {
               entities.add(cursorToObj(cursor));
            }  while(cursor.moveToNext());
        }
        return entities;
    }

    @Override
    public T findById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+fakeEntity.getTableName()+" WHERE "+ fakeEntity.getColumns()[0] +" =?", new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()) {
            return cursorToObj(cursor);
        }
        return null;
    }


}
