package lhsystems.com.coffeereporter.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import lhsystems.com.coffeereporter.db.entity.User;


public class UserDao extends SQLiteOpenHelper implements Dao<User> {

    public UserDao(Context context) {
        super(context, "COFFEE_REP", null, 1);
        //context, DATABASE_NAME, null, DATABASE_VERSION)
    }

    @Override
    public User findById() {
        return null;
    }

    @Override
    public List<User> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USERS", null);
        List<User> users = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {
                users.add(cursorToObj(cursor));
            }  while(cursor.moveToNext());
        }
        return users;
    }

    @Override
    public User create(User entity) {
        SQLiteDatabase db = this.getWritableDatabase();
        /*
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(User.TABLE_NAME);
        sb.append("(");
        sb.append(User.COLUMNS[1]);
        sb.append(",");
        sb.append(User.COLUMNS[2]);
        sb.append(",");
        sb.append(User.COLUMNS[3]);
        sb.append(") Values (?,?,?)");

        db.rawQuery(sb.toString(),new String[]{entity.getFirstName(),entity.getLastName(),entity.getEmail()});

        */
        ContentValues values = new ContentValues();
        values.put(User.COLUMNS[1],entity.getFirstName());
        values.put(User.COLUMNS[2],entity.getLastName());
        values.put(User.COLUMNS[3],entity.getEmail());
        db.insert(User.TABLE_NAME,null,values);
        return  entity;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void delete(User entity) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(User.TABLE_NAME, User.COLUMNS[0]+ " = ?",
                new String[] { String.valueOf(entity.getId()) });
        db.close();
    }

    @Override
    public User cursorToObj(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getInt(0));
        user.setFirstName(cursor.getString(1));
        user.setLastName(cursor.getString(2));
        user.setEmail(cursor.getString(3));
        return user;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + User.TABLE_NAME + "("
                + User.COLUMNS[0] + " INTEGER PRIMARY KEY AUTOINCREMENT," + User.COLUMNS[1] + " TEXT,"
                +  User.COLUMNS[2] + " TEXT," +  User.COLUMNS[3] + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
