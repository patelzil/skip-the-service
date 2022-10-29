package com.group1.servicesapp;

import com.group1.servicesapp.data.IAccountDatabase;
import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.logic.ILoginLogic;
import com.group1.servicesapp.logic.LoginLogic;
import com.group1.servicesapp.objects.Account;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoginLogicTest {

    private MockDatabase x;
    private ILoginLogic testLog;

    @Before
    public void setup(){
        x = new MockDatabase();
        testLog = new LoginLogic(x);
    }

    //Test if our username and password match logic works
    @Test
    public void testUserMatch(){
        boolean output;

        output = testLog.userMatch("wes", "123");//right
            assertTrue(output);

        output =  testLog.userMatch("qwfsde", "sdha");//wrong
            Assert.assertFalse(output);

        output = testLog.userMatch("josh", "abc");//right
            assertTrue(output);

        output =  testLog.userMatch("wes","234");//right user, wrong pass
            Assert.assertFalse(output);

        output = testLog.userMatch("dasd","123");//wrong user, right pass
            Assert.assertFalse(output);

        x.deleteAccount("josh"); //is in the db
        x.deleteAccount("wes"); //Is in the db

        output = testLog.userMatch("wes","123");//used to be in database
            Assert.assertFalse(output);

        output =  testLog.userMatch("josh", "234");//used to be in database
            Assert.assertFalse(output);

        output = testLog.userMatch("eweq","213");//was never is database
            Assert.assertFalse(output);
    }

}