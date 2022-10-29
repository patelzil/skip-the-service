package com.group1.servicesapp.data;

public class
InvalidSearch extends RuntimeException {
    public InvalidSearch(String err) {
        super("Invalid search" + err);
    }
}
