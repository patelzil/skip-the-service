package com.group1.servicesapp.data;


import com.group1.servicesapp.logic.Exceptions.InvalidCategoryException;
import com.group1.servicesapp.objects.Service;

import java.util.ArrayList;

public interface IServiceDatabase {
    ArrayList<Service> getServiceFromServiceName(String name);
    ArrayList<Service> getServiceFromName(String username);
    ArrayList<Service> getCategory(String category) throws InvalidCategoryException;

    long getServiceSize();

    boolean checkService(String name);

    long addService(String name, String user, String cat, Double price, String desc, String loc, String img);

    int deleteService(String name,String user);

    int updateService(String targetName,String targetUser, String newName, String newCat, Double newPrice, String newDesc, String newLoc, String newImg);

    String getEmail(String serviceName);
}