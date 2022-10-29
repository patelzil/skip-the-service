package com.group1.servicesapp.logic;

import com.group1.servicesapp.data.IServiceDatabase;
import com.group1.servicesapp.logic.Exceptions.DuplicateServiceException;
import com.group1.servicesapp.logic.Exceptions.InvalidServiceException;
import com.group1.servicesapp.objects.Service;

import org.sqlite.database.sqlite.SQLiteConstraintException;

import java.util.ArrayList;
import java.util.Iterator;

public class ServiceLogic implements IServiceLogic {
    private IServiceDatabase db;

    public ServiceLogic(IServiceDatabase rDb) {
        db = rDb;
    }

    // validation class, creates a service object as long as the required fields are not blank
    // otherwise returns null
    private Service validateService(String serviceName, String userName, String serviceCategory, String serviceLoc, String serviceDesc, Double servicePrice, String serviceImg) {
        Service result = null;
        //Check for required fields
        if (!serviceName.equals("") && !userName.equals("") && !serviceCategory.equals("") && !serviceLoc.equals("") && servicePrice > 0.0) {
            result = new Service(serviceName,userName,serviceCategory,servicePrice,serviceDesc,serviceLoc,serviceImg);
        }
        return result;
    }

    //Given service parameters first validate it then add it to the database
    public Service addService(String serviceName, String userName, String serviceCategory, String serviceLoc,
                           String serviceDesc, Double servicePrice, String serviceImg) throws InvalidServiceException, DuplicateServiceException {

        Service s = validateService(serviceName,userName,serviceCategory,serviceLoc,serviceDesc,servicePrice,serviceImg);

        if ( s == null ) { //blank fields were present
            throw new InvalidServiceException("Tried to create a service with blank parameters");
        } else {
            try {
                db.addService(s.getName(), s.getUser(), s.getCategory(), s.getPrice(), s.getDesc(), s.getLoc(), s.getImg());
            } catch (SQLiteConstraintException e) {
                throw new DuplicateServiceException("Service already exists");
            }
        }
        return s;
    }

    public void editService(String targetName, String targetUser, String newName, String newCategory,
                            Double newPrice, String newDesc, String newLocation) throws InvalidServiceException, DuplicateServiceException {
        // Check to make sure we can change it to what we want to
        int rows = db.updateService(targetName,targetUser,newName,newCategory,newPrice,newDesc,newLocation,"");
        if ( rows <= 0 ) {
            throw new InvalidServiceException("No Existing Service Found");
        }
    }

    public void deleteService(String serviceName, String userName) {
        int rows = db.deleteService(serviceName,userName);
        if ( rows <= 0 ) {
            throw new InvalidServiceException("No Existing Service Found");
        }
    }
}
