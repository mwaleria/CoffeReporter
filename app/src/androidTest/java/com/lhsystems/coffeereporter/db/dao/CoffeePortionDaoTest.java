package com.lhsystems.coffeereporter.db.dao;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import org.joda.time.DateTime;

import java.util.List;

import com.lhsystems.coffeereporter.db.entity.CoffeePortion;

/**
 * Created by waler on 12/04/2015.
 */
public class CoffeePortionDaoTest extends AndroidTestCase {

    private CoffeePortionDao coffeePortionDao;

    @Override
    protected void setUp() throws Exception {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        coffeePortionDao = new CoffeePortionDao(context);
    }

    public void testInsert() {
        List<CoffeePortion> coffeePortions = coffeePortionDao.findAll();
        assertEquals(0,coffeePortions.size());
        CoffeePortion  coffeePortion = new CoffeePortion();
        coffeePortion.setCoffeeId(1l);
        coffeePortion.setUserId(1l);
        DateTime dt = new DateTime(2015,4,12,15,21,0);
        coffeePortion = coffeePortionDao.create(coffeePortion , dt.getMillis());
        coffeePortions = coffeePortionDao.findAll();
        assertEquals(1,coffeePortions.size());
        CoffeePortion coffeePortionFromDb = coffeePortions.get(0);
        assertEquals(1l,coffeePortionFromDb.getCoffeeId());
        assertEquals(1l,coffeePortionFromDb.getUserId());
        assertEquals(dt.getMillis(),coffeePortionFromDb.getTimestamp());
    }

    public void testGetCoffeePortionsForUserInTime() {
        CoffeePortion  coffeePortion = new CoffeePortion();
        coffeePortion.setCoffeeId(1l);
        coffeePortion.setUserId(1l);
        DateTime dt = new DateTime(2015,4,12,15,21,0);
        coffeePortionDao.create(coffeePortion , dt.plusDays(1).getMillis());
        coffeePortionDao.create(coffeePortion , dt.getMillis());
        long count = coffeePortionDao.getCoffeePortionsForUserInTime(1l,dt.minusSeconds(1),dt.plusDays(2));
        assertEquals(2,count);
        count = coffeePortionDao.getCoffeePortionsForUserInTime(1l,dt.minusSeconds(1),dt.plusHours(2));
        assertEquals(1,count);
        count = coffeePortionDao.getCoffeePortionsForUserInTime(1l,dt.plusHours(4),dt.plusDays(2));
        assertEquals(1,count);
        count = coffeePortionDao.getCoffeePortionsForUserInTime(1l,dt.plusDays(2),dt.plusDays(4));
        assertEquals(0,count);
        count = coffeePortionDao.getCoffeePortionsForUserInTime(2l,dt.minusYears(10),dt.plusYears(10));
        assertEquals(0,count);
    }
}
