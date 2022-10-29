package com.group1.servicesapp.logic;


import com.group1.servicesapp.logic.Exceptions.ServiceNotFound;
import com.group1.servicesapp.objects.Service;

import java.util.ArrayList;

public interface ISearchLogic {
    ArrayList<Service> search(String target) throws ServiceNotFound;
    ArrayList<Service> getServicesFromUser(String user);
}
