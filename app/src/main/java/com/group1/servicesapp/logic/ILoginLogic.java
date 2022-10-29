package com.group1.servicesapp.logic;

import com.group1.servicesapp.data.RealDatabase;

public interface ILoginLogic {
    public boolean userMatch(String username, String password);
    public boolean accountBusiness();
}
