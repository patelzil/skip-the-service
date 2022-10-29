package com.group1.servicesapp.logic;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import com.group1.servicesapp.data.IAccountDatabase;
import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.logic.Exceptions.AccountNotFound;
import com.group1.servicesapp.logic.Exceptions.DuplicateAccountException;
import com.group1.servicesapp.logic.Exceptions.InvalidCredentialException;
import com.group1.servicesapp.objects.Account;

import java.util.ArrayList;

public class AccountLogic implements IAccountLogic
{
    private IAccountDatabase db;

    // null constructor
    public AccountLogic(){
        db = null;
    }

    // Constructor for the RealDatabase
    public AccountLogic(Context context) { this.db = RealDatabase.getInstance(context); }

    // Constructor for the MockDatabase
    public AccountLogic(MockDatabase mock) { this.db = mock; }

    public boolean addAccount(String userName, String email, String address, String password, boolean isBusiness) throws DuplicateAccountException
    {
        Account newAccount = new Account(userName, email, address, password, isBusiness);
        boolean result= false;

        try
        {
            // if valid then add the account to the database
            if (validateAccount(newAccount)) {
                db.addAccount(newAccount);
                result = true;
            }
        }
        catch (DuplicateAccountException e)
        {
            Log.e("Duplicate Account: ", e.getMessage());
        }

        return result;
    }


    private boolean validateAccount(Account newAccount) throws InvalidCredentialException
    {
        // if the account already exists then throw a duplicate account exception
        boolean found = true;
        boolean result = false;
        Account myAccount = null;
        myAccount = db.getAccount(newAccount.getUsername());

        if ( myAccount == null ) {
            found = false;
        }
        if ( !found ) {
            try {
                validateCredentials(newAccount);
                result = true;
            } catch (InvalidCredentialException e) {
                Log.e("Invalid Credential: ", e.getMessage());
            }
        }

        return result;
    }

    public void validateCredentials(Account newAccount)
    {
        ArrayList<String> list = new ArrayList<>();

        list.add(newAccount.getUsername());
        list.add(newAccount.getEmail());
        list.add(newAccount.getAddress());
        list.add(newAccount.getPassword());

        for(String value: list)
        {
            if(value.isEmpty())
            {
                throw new InvalidCredentialException("Empty input");
            }
        }
    }

    public boolean checkPassword(String password, String confirmPass)
    {
        return password.equals(confirmPass);
    }


    public Account getAccount(String userName) throws AccountNotFound
    {
        Account result = null;

        try {
            result = db.getAccount(userName);
        } catch (SQLException e) {
            throw new AccountNotFound("Account doesn't exist");
        }

        return result;
    }

    public boolean updateAccount(String targetUser, String newMail, String newAdd, String newPass, boolean busBool)
    {
        boolean result = true;

        if( getAccount(targetUser) == null )
            result = false;
        else
            db.updateAccount(targetUser, newMail, newAdd, newPass, busBool);

        return result;
    }
}
