package com.group1.servicesapp.logic;

import android.content.Context;

import com.group1.servicesapp.data.IServiceDatabase;
import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.logic.Exceptions.ServiceNotFound;
import com.group1.servicesapp.objects.Service;

import java.util.ArrayList;

public class SearchLogic implements ISearchLogic {
    // Category strings
    private String cat1 = "Cleaning";
    private String cat2 = "Snow Removal";
    private String cat3 = "Mechanic";
    private String cat4 = "Health Services";
    private String cat5 = "Pool Maintenance";
    private String cat6 = "Lawn Care";
    private String cat7 = "Gardening";
    private String cat8 = "Plumbing";


    private IServiceDatabase db;

    public SearchLogic(Context c) { this.db = RealDatabase.getInstance(c);}

    public SearchLogic(RealDatabase db){
        this.db = db;
    }

    public SearchLogic(MockDatabase db){
        this.db = db;
    }

    // Search logic methods
    public ArrayList<Service> search(String mySearch) throws ServiceNotFound {
        ArrayList<Service> resultList = new ArrayList<>();
        //Search name null if no result
        if (isCategory(mySearch)) {
            resultList = db.getCategory(mySearch);
        } else {
                ArrayList<Service> searchList = db.getServiceFromServiceName(mySearch);
                if (searchList.size() > 0) { //Search returned some number of results
                    resultList.addAll(searchList);
                } else {
                    throw new ServiceNotFound(mySearch);
                }
        }
        return resultList;
    }

    public ArrayList<Service> getServicesFromUser(String myUser) {
        ArrayList<Service> result = new ArrayList<>();
        ArrayList<Service> resultList = db.getServiceFromName(myUser);
        if ( resultList.size() > 0 ) { //Search returned some number of results
            result.addAll(resultList);
        }
        return result;
    }

    private boolean isCategory(String input) {
        boolean isCat = false;
        if ( input.equalsIgnoreCase(cat1) || input.equalsIgnoreCase(cat2) ||
                input.equalsIgnoreCase(cat3) || input.equalsIgnoreCase(cat4) ||
                input.equalsIgnoreCase(cat5) || input.equalsIgnoreCase(cat6) ||
                input.equalsIgnoreCase(cat7) || input.equalsIgnoreCase(cat8) ) {
            isCat = true;
        }
        return isCat;
    }
}
