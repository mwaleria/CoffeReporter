package com.lhsystems.coffeereporter.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import com.lhsystems.coffeereporter.db.entity.Entity;

public interface Dao<T extends Entity> {

    public T findById(int id);
    public List<T> findAll();
    public T create(T entity);
    public T update(T entity);
    public void delete(T entity);
    public ContentValues entityToContentValues(T entity);

    public T cursorToObj(Cursor cursor);

}
