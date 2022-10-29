package com.group1.servicesapp.logic.Exceptions;

public class DuplicateAccountException extends RuntimeException
{
    public DuplicateAccountException(String errorMessage)
    {
        super("Duplicate Account: " + errorMessage);
    }
}
