package com.group1.servicesapp.logic.Exceptions;

public class OrderNotFound extends RuntimeException {
    public OrderNotFound(String errorMessage)
    {
        super("Order Not Found: " + errorMessage);
    }

}
