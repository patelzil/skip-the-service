package com.group1.servicesapp.logic;


import android.content.Context;
import android.util.Log;

import android.database.SQLException;

import com.group1.servicesapp.data.IAccountDatabase;
import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.objects.Account;

public class LoginLogic implements ILoginLogic{

    private IAccountDatabase db;
    private Account tableAccount;

    public LoginLogic(Context context){
        db = RealDatabase.getInstance(context);
    }

    public LoginLogic(MockDatabase db){
        this.db = db;
    }

    //Check if the username is in the Database and makes sure the password matches
    public boolean userMatch(String username, String password) {
        try {
            //Try to see if the account exist and return true if the password matches
            tableAccount = db.getAccount(username);
            if( tableAccount != null )
                return (username.equals(tableAccount.getUsername())
                    && password.equals(tableAccount.getPassword()));
            else
                return false;
        }catch(SQLException e){
            Log.e("SQL Exception","Username was not in DB");
            return false;
        }
    }

    public boolean accountBusiness(){
        return tableAccount.isBusiness();
    }

}
