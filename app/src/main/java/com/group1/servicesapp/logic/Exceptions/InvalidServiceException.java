package com.group1.servicesapp.logic.Exceptions;

public class InvalidServiceException extends RuntimeException
{
    public InvalidServiceException(String errorMessage)
    {
        super("Service Not Found: " + errorMessage);
    }
}