package com.group1.servicesapp.logic.Exceptions;

public class
ServiceNotFound extends RuntimeException
{
    public ServiceNotFound(String errorMessage)
    {
        super("Service Not Found: " + errorMessage);
    }
}
