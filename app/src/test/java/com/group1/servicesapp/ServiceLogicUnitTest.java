package com.group1.servicesapp;

import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.logic.Exceptions.DuplicateAccountException;
import com.group1.servicesapp.logic.Exceptions.DuplicateServiceException;
import com.group1.servicesapp.logic.Exceptions.InvalidServiceException;
import com.group1.servicesapp.logic.IServiceLogic;
import com.group1.servicesapp.logic.ServiceLogic;
import com.group1.servicesapp.objects.Service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceLogicUnitTest {

    private IServiceLogic sLogic;

    @Mock
    private MockDatabase db = new MockDatabase();

    @Before
    public void setup() {
        sLogic = new ServiceLogic(db);
        db.addService("TestName","user","Snow Removal",10.00,"Hi","WPG","My Image");
        when(db.deleteService("TestName","user")).thenReturn(1);
        when(db.deleteService("N/A","N/A")).thenReturn(0);

    }

    @Test
    public void testRegisterService() {
        assert(db != null);
        Service s;
        // Test a normal insertion
        String testName = "My New Service!";
        String testUser = "User123";
        String testCategory = "Snow Removal";
        String testLocation = "Canada";
        String testDescription = "This is a great business!";
        Double testPrice = 50.00;
        String testImage = "imageString";
        try {  // Test normal insertion
            sLogic.addService(testName, testUser, testCategory, testLocation, testDescription, testPrice, testImage);
        } catch (DuplicateServiceException e) {
            fail("Shouldn't throw exception");
        } catch (InvalidServiceException e) {
            fail("Shouldn't throw exception");
        }
        // Test bad insertion
        InvalidServiceException e1 = assertThrows(
                InvalidServiceException.class, () -> sLogic.addService("","","","","",0.0,"")
        );
        assert(e1.getMessage().contains("Service Not Found"));

        // Test duplicate insertion
        when(db.addService(testName,testUser,testCategory,testPrice,testDescription,testLocation,testImage)).
                thenThrow(new DuplicateServiceException("Duplicate Service"));

        DuplicateServiceException e2 = assertThrows(
                DuplicateServiceException.class, () -> sLogic.addService(testName, testUser, testCategory,
                        testLocation, testDescription, testPrice, testImage)
        );
        assert(e2.getMessage().contains("Duplicate Service"));

    }

    @Test
    public void testUpdateService() {
        when(db.updateService("My New Service!","User123","Less good service",
                "Plumbing",10.00,"Worse Business","America","")).thenReturn(1);
        when(db.updateService("","","","",0.0,"","","")).thenReturn(0);
        try {  // Test updating a real service
            sLogic.editService("My New Service!","User123","Less good service",
                    "Plumbing",10.00,"Worse Business","America");
        } catch (InvalidServiceException e) {
            fail("This should not fail");
        }
        // Then try and update a service that doesn't exist (should fail)
        InvalidServiceException e = assertThrows(
                InvalidServiceException.class, () -> sLogic.editService("","","","",0.0,"","")
        );
        assert(e.getMessage().contains("Service Not Found"));
    }

    @Test
    public void testDeleteService() {
        try {
            sLogic.deleteService("TestName","user");
        } catch (InvalidServiceException e) {
            fail("This should not fail");
        }
        // Try bad delete
        InvalidServiceException e = assertThrows(
                InvalidServiceException.class, () -> sLogic.deleteService("N/A","N/A")
        );
        assert(e.getMessage().contains("Service Not Found"));
    }

}
