package com.group1.servicesapp;

import com.group1.servicesapp.data.IServiceDatabase;
import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.logic.IServiceCommunicationLogic;
import com.group1.servicesapp.logic.ServiceCommunicationLogic;

import org.junit.Before;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
public class ServiceCommunicationUnitTest
{

    private IServiceCommunicationLogic serviceComms;
   @Mock
   private MockDatabase db = new MockDatabase();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        serviceComms = new ServiceCommunicationLogic(db);

        db.addService("dig","steve","digging",5.00,
                "steve's","test","test");
        db.addService("fish","fisher","test",5.00,
                "test","test","test");

        when(db.getEmail(null)).thenReturn("fail");//should pass
        when(db.getEmail("dig")).thenReturn("pass");//should pass
        when(db.getEmail("fish")).thenReturn("pass");//should pass
        when(db.getEmail("fail")).thenReturn("fail");//sending fake service
        when(db.getEmail("")).thenReturn("fail");//Sending empty string
    }

    @Test
    public void getServiceEmail(){
        String result;

        result = serviceComms.contactServiceProvider("fail");
        assert(result.equals("fail"));

        result = serviceComms.contactServiceProvider("dig");
        assert(result.equals("pass"));

        result = serviceComms.contactServiceProvider("fish");
        assert(result.equals("pass"));

        result = serviceComms.contactServiceProvider("");
        assert(result.equals("fail"));

        result = serviceComms.contactServiceProvider(null);
        assert(result.equals("fail"));
    }
}
