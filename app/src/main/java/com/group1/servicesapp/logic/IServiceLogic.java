package com.group1.servicesapp.logic;

import com.group1.servicesapp.logic.Exceptions.InvalidServiceException;
import com.group1.servicesapp.objects.Service;

public interface IServiceLogic {
    Service addService(String serviceName, String userName, String serviceCategory, String serviceLoc,
                       String serviceDesc, Double servicePrice, String serviceImg);

    void editService(String targetName, String targetUser, String newName, String newCategory,
                     Double newPrice, String newDesc, String newLocation) throws InvalidServiceException;

    void deleteService(String userName, String serviceName);
}
