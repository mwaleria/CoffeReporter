package com.lhsystems.coffeereporter.db.dao;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import java.util.List;

import com.lhsystems.coffeereporter.db.entity.Coffee;

/**
 * Created by waler on 12/04/2015.
 */
public class CoffeeDaoTest extends AndroidTestCase {

    private CoffeeDao coffeeDao;

    @Override
    protected void setUp() throws Exception {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        coffeeDao = new CoffeeDao(context);
    }

    public void testInsertEntity() {
        List<Coffee> coffees  = coffeeDao.findAll();
        assertEquals(0,coffees.size());
        Coffee coffee = new Coffee();
        coffee.setName("Coffee 1");
        coffee.setPrice(1f);
        coffeeDao.create(coffee);
        coffees  = coffeeDao.findAll();
        assertEquals(1,coffees.size());
        Coffee coffeeFromDb = coffees.get(0);
        assertEquals("Coffee 1",coffeeFromDb.getName());
        assertEquals(1f,coffeeFromDb.getPrice(),0.001f);
    }

    public void testDelete() {
        List<Coffee> coffees  = coffeeDao.findAll();
        Coffee coffee = new Coffee();
        coffee.setName("Coffee 1");
        coffee.setPrice(1f);
        coffeeDao.create(coffee);
        coffees  = coffeeDao.findAll();
        assertEquals(1,coffees.size());
        Coffee coffeeFromDb = coffees.get(0);
        coffeeDao.delete(coffeeFromDb);
        coffees  = coffeeDao.findAll();
        assertEquals(0,coffees.size());
    }

    public void testFindById() {
        Coffee c1 = new Coffee("coffee1",1f);
        Coffee c2 = new Coffee("coffee2",2f);
        coffeeDao.create(c1);
        coffeeDao.create(c2);
        Coffee coffee  = coffeeDao.findById(1);
        assertEquals("coffee1", coffee.getName());
        assertEquals(1f,coffee.getPrice(),0.001f);
    }
}
