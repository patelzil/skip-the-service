package com.group1.servicesapp;

import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.logic.DeleteAccount;
import com.group1.servicesapp.logic.IDeleteAccount;
import com.group1.servicesapp.objects.Account;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeleteAccountTest {

    private MockDatabase db;
    private IDeleteAccount da;

    @Before
    public void setup(){
        db = new MockDatabase();
        da = new DeleteAccount(db);
    }

    @Test
    public void testDeleteAccount(){
        boolean output;
        output = hasAccount("wes"); //is in the mock db
        Assert.assertTrue(output);

        da.deleteFromTable("wes"); //deletes wes from table
        output = hasAccount("wes");//is not in the mock db
        Assert.assertFalse(output);

        output = hasAccount("josh");//make sure it didn't delete other stuff
        Assert.assertTrue(output);

        da.deleteFromTable("dsadas");//Delete something that is not there
        output = hasAccount("dsadas");//is not in the mock db
        Assert.assertFalse(output);
    }

    private boolean hasAccount(String checking){
        return db.getAccount(checking) != null;
    }
}
