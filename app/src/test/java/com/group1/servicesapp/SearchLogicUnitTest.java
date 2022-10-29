package com.group1.servicesapp;

import com.group1.servicesapp.data.IServiceDatabase;
import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.logic.ISearchLogic;
import com.group1.servicesapp.logic.SearchLogic;
import com.group1.servicesapp.objects.Service;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchLogicUnitTest {

    private IServiceDatabase db;
    private MockDatabase mDb;
    private ISearchLogic sLogic;

    @Before
    public void setup()
    {
        mDb = new MockDatabase();
        sLogic = new SearchLogic(mDb);
        db = mDb;
        //Add services for testing if they aren't in already
        if ( !db.checkService("test") ) {
            db.addService("test","Josh", "Snow Removal", 10.00, "test1 service", "wpg", "myImg");
        }
        if ( !db.checkService("test2") ) {
            db.addService("test2", "Josh", "Snow Removal", 10.00, "test2 service", "edm", "myImg");
        }
        if ( !db.checkService("test3") ) {
            db.addService("test3", "William", "Gardening", 10.00, "test3 service", "tor", "myImg");
        }

    }

    @Test
    public void testSearch() {
        // First search for a single service that exists
        assert(sLogic.search("test2") !=  null);
        // Next search for a category that exists
        assert (sLogic.search("Snow Removal") != null);

        ArrayList<Service> data = sLogic.search("Snow Removal");
        // col 0 is name data, 1 is img, 2 is loc, 3 is cat
        for ( int i=0; i < data.size(); i++ ) { // go through the list of results and make sure they all have same cat
            assert( data.get(i).getCategory().equals("Snow Removal") );
        }
    }

    @Test
    public void searchUser() {
        ArrayList<Service> list = sLogic.getServicesFromUser("Josh");
        for ( int i = 0; i < list.size(); i++ ) {
            assert(list.get(i).getUser().equals("Josh"));
        }
    }




}
