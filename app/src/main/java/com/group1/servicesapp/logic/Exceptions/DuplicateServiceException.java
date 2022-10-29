package com.group1.servicesapp.logic.Exceptions;

public class DuplicateServiceException extends RuntimeException
{
    public DuplicateServiceException(String errorMessage)
    {
        super("Error: Duplicate Service " + errorMessage);
    }
}