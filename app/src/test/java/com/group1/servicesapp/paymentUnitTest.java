package com.group1.servicesapp;

import com.group1.servicesapp.objects.Payment;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class paymentUnitTest {
    Payment p1 = new Payment("1234567890123456", "123", "1010", "Rob");
    Payment p2 = new Payment("2234567890123456", "333", "2010", "Adam");

    @Test
    public void paymentInfoTest(){
        int digitsCardNum = p1.getCardNum().length();
        assertEquals("Wrong card number digits", 16, digitsCardNum);

        int digitsCardNum2 = p2.getCardNum().length();
        assertEquals("Wrong card number digits", 16, digitsCardNum2);

        int digitsCvcNum = p1.getCvcNum().length();
        assertEquals("Wrong cvc digits", 3, digitsCvcNum);

        int digitsCvcNum2 = p2.getCvcNum().length();
        assertEquals("Wrong cvc digits", 3, digitsCvcNum);


        int digitsDateNum = p1.getValidDate().length();
        assertEquals("Wrong date", 4, digitsDateNum);

        int digitsDateNum2 = p2.getValidDate().length();
        assertEquals("Wrong date", 4, digitsDateNum2);
    }


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}