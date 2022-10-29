package com.group1.servicesapp;

import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.logic.FavoriteLogic;
import com.group1.servicesapp.logic.IFavoriteLogic;
import com.group1.servicesapp.objects.Favorite;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddFavoriteTest {

    private MockDatabase db;
    private IFavoriteLogic fLogic;

    @Before
    public void setup(){
        db = new MockDatabase();
        fLogic = new FavoriteLogic(db);
    }

    @Test
    public void testAddFavorite(){

        fLogic.addFavorite("josh", "Plumbing mart");
        fLogic.addFavorite("zil", "Service Depot");
        fLogic.addFavorite("wes", "Drillway");
        fLogic.addFavorite("Yue", "Angle Master");
        fLogic.addFavorite("zelin", "Eagle Services");

        int size= db.getFavoriteListSize();
        assertEquals("add error", 5, size);
    }
}
