package lhsystems.com.coffeereporter.db.dao;

import android.database.Cursor;

import java.util.List;

public interface Dao<T> {

    public T findById();
    public List<T> findAll();
    public T create(T entity);
    public T update(T entity);
    public void delete(T entity);

    public T cursorToObj(Cursor cursor);

}
