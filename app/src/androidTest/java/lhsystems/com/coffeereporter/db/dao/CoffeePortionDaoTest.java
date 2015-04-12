package lhsystems.com.coffeereporter.db.dao;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import org.joda.time.DateTime;

import java.util.List;

import lhsystems.com.coffeereporter.db.entity.CoffeePortion;

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
}
