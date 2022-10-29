package com.group1.servicesapp;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.data.RealDatabase;


import org.junit.Before;
import org.junit.Test;


public class ReviewAndFavoriteTest {

    private RealDatabase db;
    private MockDatabase mDb;


    @Before
    public void setup()
    {
        System.loadLibrary("sqliteX");
        mDb = new MockDatabase();
        Context context = ApplicationProvider.getApplicationContext();
        db = RealDatabase.getInstance(context);

        db.addReview("testUser1", "testService1","5.0","testReview1");
        db.addReview("testUser2", "testService2","4.0","testReview2");
        db.addReview("testUser3", "testService3","3.0","testReview3");
        db.addReview("testUser4", "testService4","2.0","testReview4");

        db.addFavorite("testUser1", "testService1");
        db.addFavorite("testUser2", "testService2");
        db.addFavorite("testUser3", "testService3");
        db.addFavorite("testUser4", "testService4");

    }


    @Test
    public void testReviewAdd() {
        long reviewListSize = db.getReviewSize();
        db.addReview("testUser5", "testService5","1.0","testReview5");
        assert(db.getReviewSize() == (reviewListSize + 1) );
    }




}
