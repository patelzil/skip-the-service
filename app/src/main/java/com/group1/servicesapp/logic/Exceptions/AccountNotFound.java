package com.group1.servicesapp.logic.Exceptions;

public class AccountNotFound extends RuntimeException {
    public AccountNotFound(String errorMessage)
    {
        super("Account Not Found: " + errorMessage);
    }

}
