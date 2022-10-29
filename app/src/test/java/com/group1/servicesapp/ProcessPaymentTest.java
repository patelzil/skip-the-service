package com.group1.servicesapp;

import com.group1.servicesapp.logic.Exceptions.InvalidCredentialException;
import com.group1.servicesapp.logic.IPaymentLogic;
import com.group1.servicesapp.logic.PaymentLogic;
import com.group1.servicesapp.logic.ReportLogic;
import com.group1.servicesapp.objects.Payment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProcessPaymentTest {

    private IPaymentLogic processPayment;

    @Before
    public void setUp(){
        processPayment = new PaymentLogic();
    }

    @Test
    public void testProcess(){
        Payment p1 = new Payment("1234567890123456", "123", "1010", "Mike");
        Payment p2 = new Payment("0000000000000000", "000", "0000", "Jack");

      boolean result;
        try{
            processPayment.cardNumberCheck(p1.getCardNum());
            result = false;
        }catch(InvalidCredentialException e){
            result = true;
        }
        assertEquals(false, result);

        try{
            processPayment.cvcNumberCheck(p2.getCvcNum());
            result = false;
        }catch(InvalidCredentialException e){
            result = true;
        }
        assertEquals(false, result);

        try{
            processPayment.validDateCheck(p2.getValidDate());
            result = false;
        }catch(InvalidCredentialException e){
            result = true;
        }
        assertEquals(false, result);

    }
}
