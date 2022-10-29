package com.group1.servicesapp.logic;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.group1.servicesapp.data.IAccountDatabase;
import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.data.RealDatabase;

public class DeleteAccount implements IDeleteAccount {


    private IAccountDatabase db;

    //Constructor for the real database
    public DeleteAccount(Context context) {
        this.db = RealDatabase.getInstance(context);
    }

    //constructor for the mock database
    public DeleteAccount(MockDatabase db){
        this.db = db;
    }

    //Delete an account from the table based off the key: Username
    public void deleteFromTable(String username){
        try {
            //Call the method that deletes the account
            this.db.deleteAccount(username);
        }catch(SQLiteException e){
            //If the username doesn't exist, do nothing
            Log.e("SQL Exception", "Username doesn't exist in DB");
        }
    }
}
