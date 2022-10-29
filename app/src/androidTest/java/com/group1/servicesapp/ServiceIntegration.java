package com.group1.servicesapp;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.group1.servicesapp.data.IServiceDatabase;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.logic.Exceptions.ServiceNotFound;
import com.group1.servicesapp.logic.ISearchLogic;
import com.group1.servicesapp.logic.SearchLogic;
import com.group1.servicesapp.objects.Service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class ServiceIntegration {

    private Context context;
    private IServiceDatabase db;
    private ISearchLogic sLogic;

    @Before
    public void setup()
    {
        System.loadLibrary("sqliteX");
        context = ApplicationProvider.getApplicationContext();
        db = RealDatabase.getInstance(context);
        sLogic = new SearchLogic(context);

        //Add services for testing if they aren't in already
        if (!db.checkService("test2") ) {
            db.addService("test", "myMail", "Snow Removal", 10.00, "test1 service", "wpg", "myImg");
        }

        if ( !db.checkService("test2") ) {
            db.addService("test2", "myMail", "Snow Removal", 10.00, "test2 service", "edm", "myImg");
        }
        if ( !db.checkService("test3") ) {
            db.addService("test3", "myMail", "Gardening", 10.00, "test3 service", "tor", "myImg");
        }
    }

    /* SearchLogic -> Database */
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
    /* SearchLogic -> Database */
    @Test (expected = ServiceNotFound.class )
    public void testExceptionSearch() {
        // Search invalid service
        sLogic.search("not a real service");
    }

}
