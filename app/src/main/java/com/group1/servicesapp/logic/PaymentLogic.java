package com.group1.servicesapp.logic;

import com.group1.servicesapp.logic.Exceptions.InvalidCredentialException;
import com.group1.servicesapp.objects.Payment;

public class PaymentLogic implements IPaymentLogic{
    private final int CARD_NUMBER_DIGITS=16;
    private final int CVC_NUMBER_DIGITS=3;
    private final int VALID_DATE_DIGITS=4;

    public void formatCheck(Payment p) throws InvalidCredentialException {
        cardNumberCheck(p.getCardNum());
        cvcNumberCheck(p.getCvcNum());
        validDateCheck(p.getValidDate());
    }

    public void cardNumberCheck(String c){
        if(c.length()!=CARD_NUMBER_DIGITS)
        {
            throw new InvalidCredentialException("Wrong card number digits input");
        }

    }

    public void cvcNumberCheck(String c){
        if(c.length()!=CVC_NUMBER_DIGITS)
        {
            throw new InvalidCredentialException("Wrong cvc number digits input");
        }

    }

    public void validDateCheck(String v){
        if(v.length()!=VALID_DATE_DIGITS)
        {
            throw new InvalidCredentialException("Wrong valid date number digits input");
        }
    }
}
