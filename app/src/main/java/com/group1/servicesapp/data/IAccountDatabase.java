package com.group1.servicesapp.data;

import com.group1.servicesapp.objects.Account;

public interface IAccountDatabase {
     void addAccount(Account newAccount);
     Account getAccount(String username);
     void updateAccount(String targetUser, String newMail, String newAdd, String newPass, boolean busBool);
     void deleteAccount(String deletedAccountUsername);
}
