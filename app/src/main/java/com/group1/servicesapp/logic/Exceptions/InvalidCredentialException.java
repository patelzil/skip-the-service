package com.group1.servicesapp.logic.Exceptions;

public class InvalidCredentialException extends RuntimeException
{
    public InvalidCredentialException(String errorMessage)
    {
        super("Invalid Credential: " + errorMessage);
    }
}
