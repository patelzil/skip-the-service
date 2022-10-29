package com.group1.servicesapp.logic.Exceptions;

public class InvalidCategoryException extends RuntimeException
{
    public InvalidCategoryException(String errorMessage)
    {
        super("Category Invalid: " + errorMessage);
    }
}