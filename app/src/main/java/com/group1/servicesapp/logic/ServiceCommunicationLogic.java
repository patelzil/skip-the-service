package com.group1.servicesapp.logic;

import android.content.Context;

import com.group1.servicesapp.data.IServiceDatabase;
import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.logic.Exceptions.ServiceNotFound;

public class ServiceCommunicationLogic implements IServiceCommunicationLogic{

    private IServiceDatabase db;

    //real db
    public ServiceCommunicationLogic(Context c){
        this.db = RealDatabase.getInstance(c);
    }

    //mock db
    public ServiceCommunicationLogic(MockDatabase db){
        this.db = db;
    }

    public String contactServiceProvider(String serviceName) {
        //Search the DB for the service name and get the user
        //Then Search the DB for the user and get the email
        try{
            return db.getEmail(serviceName);
        }catch(NullPointerException e){
            return null;
        }catch(ServiceNotFound n){
            return null;
        }
    }
}
