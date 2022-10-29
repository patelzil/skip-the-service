package com.group1.servicesapp.logic;

import com.group1.servicesapp.objects.Payment;

public interface IPaymentLogic {
    void formatCheck(Payment p);
    void cardNumberCheck(String c);
    void cvcNumberCheck(String c);
    void validDateCheck(String v);

}
