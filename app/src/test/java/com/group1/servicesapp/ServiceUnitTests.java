package com.group1.servicesapp;


import com.group1.servicesapp.data.IServiceDatabase;
import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.objects.Service;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ServiceUnitTests {

    private MockDatabase mDb;
    private IServiceDatabase db;

    @Before
    public void setup()
    {
        mDb = new MockDatabase();
        db = mDb;
        //Add services for testing if they aren't in already
        if ( !db.checkService("test") ) {
            db.addService("test", "myMail", "Snow Removal", 10.00, "test1 service", "wpg", "myImg");
        }
        if ( !db.checkService("test2") ) {
            db.addService("test2", "myMail", "Snow Removal", 10.00, "test2 service", "edm", "myImg");
        }
        if ( !db.checkService("test3") ) {
            db.addService("test3", "myMail", "Gardening", 10.00, "test3 service", "tor", "myImg");
        }
    }


    @Test
    public void testServiceAddRemove() {
        long startSize = db.getServiceSize();
        db.addService("test4", "myMail", "Snow Removal", 10.00, "test4 service", "wpg","myImg");
        assert(db.getServiceSize() == (startSize + 1) );
        db.deleteService("test4","myMail");
        assert(db.getServiceSize() == startSize);
    }
    @Test
    public void testSearch() {
        ArrayList<Service> services = db.getServiceFromServiceName("test");
        Service s = services.get(0);
        assert ( s != null );
        assert ( s.getDesc().equals("test1 service"));
    }

    @Test
    public void testUpdate() {
            db.updateService("test","myMail","this is a new test", "new cat",
                    0.01, "new desc", "new location", "new img");

            assert(db.getServiceFromServiceName("this is a new test").get(0).getDesc().equals("new desc"));

            assert(db.getServiceFromServiceName("this is a new test").get(0).getCategory().equals("new cat"));

            assert(db.getServiceFromServiceName("this is a new test").get(0).getLoc().equals("new location"));

            assert( (Double.compare(db.getServiceFromServiceName("this is a new test").get(0).getPrice(), 0.01)) == 0);

            db.updateService("this is a new test","myMail","test", "new cat",
                    0.01, "new desc", "new location", "new img");

    }

}