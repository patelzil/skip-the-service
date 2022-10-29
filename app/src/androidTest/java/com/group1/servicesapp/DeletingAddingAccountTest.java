package com.group1.servicesapp;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.group1.servicesapp.data.IAccountDatabase;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.logic.AccountLogic;
import com.group1.servicesapp.logic.DeleteAccount;
import com.group1.servicesapp.logic.IAccountLogic;
import com.group1.servicesapp.logic.IDeleteAccount;
import com.group1.servicesapp.logic.ILoginLogic;
import com.group1.servicesapp.logic.LoginLogic;
import com.group1.servicesapp.objects.Account;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DeletingAddingAccountTest {
    //For this test we are going to be registering an account
    //Then we will log in with that account
    //Then we will delete that account
    //Then we will attempt to log in again

    private RealDatabase db;
    private IDeleteAccount delete;
    private IAccountLogic account;
    private ILoginLogic login;
    private boolean result;
    private Context c;

    @Before
    public void setup(){
        System.loadLibrary("sqliteX");
        c = ApplicationProvider.getApplicationContext();
        db = RealDatabase.getInstance(c);
        delete = new DeleteAccount(c);
        account = new AccountLogic(c);
        login = new LoginLogic(c);
    }

    @Test
    public void registerAccountTest(){
        //add a new account
        result = account.addAccount("mom","123@abc.ca","mom street",
                "123",false);
        Assert.assertTrue(result);

        //Try to add a duplicate account
        result = account.addAccount("mom","123@abc.ca","mom street",
                "123",false);
        Assert.assertFalse(result);
    }

    @Test
    public void loginTestWithEntry(){
        //Try to log in with a real account
        result = login.userMatch("mom","123");
        Assert.assertTrue(result);

        //Try to log in with a fake account
        result = login.userMatch("dad", "abc");
        Assert.assertFalse(result);
    }

    @Test
    public void deleteAccountTest(){
        //Delete a real account
        delete.deleteFromTable("mom");

        //Make sure the account is gone
        result = db.getAccount("mom") == null;
        Assert.assertTrue(result);
    }

    @Test
    public void loginTestWithoutEntry(){
        //Try to log in with a real account that was deleted
        result = login.userMatch("mom","123");
        Assert.assertFalse(result);
    }

    @Test
    public void resetData(){
        db.resetTable();
    }
}
