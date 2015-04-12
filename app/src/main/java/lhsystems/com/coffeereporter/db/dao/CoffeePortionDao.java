package lhsystems.com.coffeereporter.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

import org.joda.time.DateTime;

import lhsystems.com.coffeereporter.db.entity.Coffee;
import lhsystems.com.coffeereporter.db.entity.CoffeePortion;

/**
 * Created by waler on 12/04/2015.
 */
public class CoffeePortionDao extends AbstractDao<CoffeePortion> {
    public CoffeePortionDao(Context context) {
        super(context, CoffeePortion.class);
    }

    @Override
    public ContentValues entityToContentValues(CoffeePortion entity) {
        ContentValues cv = new ContentValues();
        cv.put(CoffeePortion.COLUMNS[1],entity.getUserId());
        cv.put(CoffeePortion.COLUMNS[2],entity.getCoffeeId());
        cv.put(CoffeePortion.COLUMNS[3],entity.getTimestamp());
        return cv;
    }

    @Override
    public CoffeePortion cursorToObj(Cursor cursor) {
        CoffeePortion coffeePortion = new CoffeePortion();
        coffeePortion.setId(cursor.getLong(0));
        coffeePortion.setUserId(cursor.getLong(1));
        coffeePortion.setCoffeeId(cursor.getLong(2));
        coffeePortion.setTimestamp(cursor.getLong(3));
        return coffeePortion;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COFFEE_PORTIONS_TABLE = "CREATE TABLE " + CoffeePortion.TABLE_NAME + "("
                + CoffeePortion.COLUMNS[0] + " INTEGER PRIMARY KEY AUTOINCREMENT," + CoffeePortion.COLUMNS[1] + " INTEGER," + CoffeePortion.COLUMNS[2] + " INTEGER,"
                +  CoffeePortion.COLUMNS[3] + " INTEGER)";
        db.execSQL(CREATE_COFFEE_PORTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //do nothing
    }

    public CoffeePortion create(final CoffeePortion entity,final long timestamp) {
        entity.setTimestamp(timestamp);
        return super.create(entity);
    }

    @Override
    public CoffeePortion create(CoffeePortion entity) {
        DateTime time = new DateTime();
        long timestamp = time.getMillis();
        return this.create(entity,timestamp);
    }

    public long getCoffeePortionsForUserInTime(final long userId,final DateTime dateFrom,final DateTime dateTo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT count(id) from "+ CoffeePortion.TABLE_NAME + " WHERE userId = ? and timestamp > ? and timestamp <  ?";
        String[] params = new String[3];
        params[0]  = String.valueOf(userId);
        params[1] = String.valueOf(dateFrom.getMillis());
        params[2] = String.valueOf(dateTo.getMillis());
        Cursor cursor = db.rawQuery(query,params);
        cursor.moveToFirst();
        return cursor.getLong(0);
    }

}
