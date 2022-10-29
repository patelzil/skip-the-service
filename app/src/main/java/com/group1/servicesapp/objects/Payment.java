package com.group1.servicesapp.objects;

public class Payment {

    private String cardNum;
    private String cvcNum;
    private String validDate;
    private String name;

    public Payment(String cardN, String cvcN, String vD, String n) {
        cardNum = cardN;
        cvcNum = cvcN;
        validDate = vD;
        name = n;
    }

    public String getCardNum() {

        return cardNum;
    }

    public String getCvcNum() {

        return cvcNum;
    }

    public String getValidDate() {

        return validDate;
    }

    public String getName() {

        return name;
    }
}
