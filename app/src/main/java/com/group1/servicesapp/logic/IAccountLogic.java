package com.group1.servicesapp.logic;

import com.group1.servicesapp.objects.Account;

public interface IAccountLogic {

     boolean addAccount(String userName, String email, String address,
                              String password, boolean isBusiness);
     void validateCredentials(Account newAccount);
     boolean checkPassword(String password, String confirmPass);
     Account getAccount(String userName);
     boolean updateAccount(String targetUser, String newMail, String newAdd, String newPass, boolean busBool);
}
