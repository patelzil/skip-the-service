package com.group1.servicesapp.objects;

public class Account
{
    private String username;
    private String email;
    private String address;
    private String password;
    private boolean isBusinessAccount;

    // null constructor
    public Account()
    {
        this.username = null;
        this.email = null;
        this.address = "";
        this.password="12345"; //default password
    }

    // constructor
    public Account(String username, String email, String address, String password, boolean isBusiness)
    {
        this.username = username;
        this.email = email;
        this.address = address;
        this.password = password;
        this.isBusinessAccount = isBusiness;
    }

    //------------------------------------------------------
    // GETTERS.
    //
    // PURPOSE: Get business information
    //-----------------------------------------------------
    public String getUsername() {
        return username;
    }

    public String getEmail()
    {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public boolean isBusiness() {
        return isBusinessAccount;
    }

    public String getPassword()
    {
        return password;
    }
}
