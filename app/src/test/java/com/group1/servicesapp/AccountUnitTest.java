package com.group1.servicesapp;

import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.logic.AccountLogic;
import com.group1.servicesapp.logic.Exceptions.InvalidCredentialException;
import com.group1.servicesapp.logic.IAccountLogic;
import com.group1.servicesapp.objects.Account;

import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.*;

public class AccountUnitTest
{
    private MockDatabase db;
    private IAccountLogic accountLogic;

    @Before
    public void setUp()
    {
        db = new MockDatabase();
        accountLogic = new AccountLogic(db);
    }

    @Test
    public void testCheckPassword()
    {
        System.out.println("\nTest Check Password Initializing");

        assertTrue("Password should be equal", accountLogic.checkPassword("!wiremart", "!wiremart"));
        assertFalse("Password should not be equal", accountLogic.checkPassword("!wiremart", "wiremart"));

        System.out.println("Test Check Password Completed");
    }

    @Test
    public void testAddAccount()
    {
        System.out.println("\nTest AddAccount Initializing");

        Account acc = new Account("Wire mart", "wiremart@info.com",  "Adam street, R1N 8V4","!wiremart", true);
        Account acc2 = new Account("plumbing mart",  "plumbingmart@info.com", "Adam street, R1N 8V4","plumart90", true);

        accountLogic.addAccount("Wire mart", "wiremart@info.com",  "Adam street, R1N 8V4","!wiremart", true);
        assertSame("Account should be in the database", accountLogic.getAccount("Wire mart").getUsername(), acc.getUsername());
        assertNull("Account should not be in the database", accountLogic.getAccount("plumbing mart"));

        accountLogic.addAccount("plumbing mart",  "plumbingmart@info.com", "Adam street, R1N 8V4","plumart90", true);
        assertSame("Account should be in the database", accountLogic.getAccount("Wire mart").getUsername(), acc.getUsername());
        assertSame("Account should be in the database", accountLogic.getAccount("plumbing mart").getUsername(), acc2.getUsername());
        System.out.println("Test AddAccount Completed");
    }

    @Test
    public void testGetAccount()
    {
        System.out.println("\nTest GetAccount Initializing");

        Account acc = new Account("Wire mart", "wiremart@info.com",  "Adam street, R1N 8V4","!wiremart", true);
        Account acc2 = new Account("plumbing mart",  "plumbingmart@info.com", "Adam street, R1N 8V4","plumart90", true);

        assertNull("Account should be null", accountLogic.getAccount("Wire mart"));
        assertNull("Account should be null", accountLogic.getAccount("plumbing mart"));

        db.addAccount(acc);
        assertNotNull("Account should not be null.", accountLogic.getAccount("Wire mart"));
        assertSame("Should return the right account.", db.getAccount("Wire mart").getUsername(), acc.getUsername());
        assertNull("Account should be null.", db.getAccount("plumbing mart"));

        db.addAccount(acc2);
        assertSame("Should return the right account.", db.getAccount("Wire mart"), acc);
        assertSame("Should return the right account.", db.getAccount("plumbing mart"), acc2);

        System.out.println("Test GetAccount Completed");
    }

    @Test
    public void testUpdateAccount(){
        System.out.println("\nTest Update Account Initializing");

        Account acc = new Account("Wire mart", "wiremart@info.com",  "Adam street, R1N 8V4","!wiremart", true);

        db.addAccount(acc);
        assertNotNull("Account should not be null.", accountLogic.getAccount("Wire mart"));
        assertSame("Should return the right username.", db.getAccount("Wire mart").getUsername(), "Wire mart");
        accountLogic.updateAccount("Wire mart", "plumbingmart@info.com",  "Adam street, R1N 8V4","!wiremart", true);
        assertSame("Should return the updated email.", accountLogic.getAccount("Wire mart").getEmail(), "plumbingmart@info.com");

        boolean result = accountLogic.updateAccount("plumbing mart",  "plumbingmart@info.com", "Adam street, R1N 8V4","plumart90", true);
        assertNull("Account should be null", accountLogic.getAccount("plumbing mart"));
        assertFalse("Update should fail.",result);
    }

    @Test
    //Test the validateCredentials method in AccountLogic
    public void testValidateCredentials(){
        boolean thrown;
        //no credentials for business account
        Account emptyBusinessAccount = new Account("", "",
                "", "", true);
        //no credentials for user account
        Account emptyUserAccount = new Account("", "",
                "", "", false);

        //missing username (both)
        Account emptyUsernameBusinessAccount = new Account("", "test",
                "test", "test", true);
        Account emptyUsernameUserAccount = new Account("", "test",
                "test", "test", false);

        //missing email (both)
        Account emptyEmailBusinessAccount = new Account("test", "",
                "test", "test", true);
        Account emptyEmailUserAccount = new Account("test", "",
                "test", "test", false);

        //missing address (both)
        Account emptyAddressBusinessAccount = new Account("test", "test",
                "", "test", true);
        Account emptyAddressUserAccount = new Account("test", "test",
                "", "test", false);

        //missing password (both)
        Account emptyPasswordBusinessAccount = new Account("test", "test",
                "test", "", true);
        Account emptyPasswordUserAccount = new Account("test", "test",
                "test", "", false);

        //correct credentials (both)
        Account correctBusinessAccount = new Account("test", "test",
                "test", "test", true);
        Account correctUserAccount = new Account("test", "test",
                "test", "test", false);

        //test empty business account
        try{
            accountLogic.validateCredentials(emptyBusinessAccount);
            thrown = false;
        }catch(InvalidCredentialException e){
            thrown = true;
        }
        assertTrue(thrown);
        //test empty user account
        try{
            accountLogic.validateCredentials(emptyUserAccount);
            thrown = false;
        }catch(InvalidCredentialException e){
            thrown = true;
        }
        assertTrue(thrown);
        //test no username business
        try{
            accountLogic.validateCredentials(emptyUsernameBusinessAccount);
            thrown = false;
        }catch(InvalidCredentialException e){
            thrown = true;
        }
        assertTrue(thrown);
        //test no username user
        try{
            accountLogic.validateCredentials(emptyUsernameUserAccount);
            thrown = false;
        }catch(InvalidCredentialException e){
            thrown = true;
        }
        assertTrue(thrown);
        //test no address business
        try{
            accountLogic.validateCredentials(emptyAddressBusinessAccount);
            thrown = false;
        }catch(InvalidCredentialException e){
            thrown = true;
        }
        assertTrue(thrown);
        //test no address user
        try{
            accountLogic.validateCredentials(emptyAddressUserAccount);
            thrown = false;
        }catch(InvalidCredentialException e){
            thrown = true;
        }
        assertTrue(thrown);
        //test no password business
        try{
            accountLogic.validateCredentials(emptyPasswordBusinessAccount);
            thrown = false;
        }catch(InvalidCredentialException e){
            thrown = true;
        }
        assertTrue(thrown);
        //test no password user
        try{
            accountLogic.validateCredentials(emptyPasswordUserAccount);
            thrown = false;
        }catch(InvalidCredentialException e){
            thrown = true;
        }
        assertTrue(thrown);
        //test correct credentials business
        try{
            accountLogic.validateCredentials(correctBusinessAccount);
            thrown = false;
        }catch(InvalidCredentialException e){
            thrown = true;
        }
        assertFalse(thrown);
        //test correct credentials user
        try{
            accountLogic.validateCredentials(correctUserAccount);
            thrown = false;
        }catch(InvalidCredentialException e){
            thrown = true;
        }
        assertFalse(thrown);
    }
}
