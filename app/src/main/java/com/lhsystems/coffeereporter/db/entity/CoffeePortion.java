package com.lhsystems.coffeereporter.db.entity;

/**
 * Created by waler on 12/04/2015.
 */
public class CoffeePortion implements Entity {

    public static final String[] COLUMNS=  {"id","userId","coffeeId","timestamp"};
    public static final String TABLE_NAME = "COFFEE_PORTIONS";

    private long id;
    private long userId;
    private long coffeeId;
    private long timestamp;

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getColumns() {
        return COLUMNS;
    }
}
