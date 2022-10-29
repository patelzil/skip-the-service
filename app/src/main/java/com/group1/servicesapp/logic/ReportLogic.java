package com.group1.servicesapp.logic;

public class ReportLogic implements IReportLogic{

    //Not much to it. Just make sure they wrote anything
    public boolean reportPass(String message) {
        //if the string is null, return false
        if(message == null) {
            return false;
        }
        //check each character to make sure they aren't all blank spaces
        else {
            for (int i = 0; i < message.length(); i++) {
                char character = message.charAt(i);
                if (character != ' ') {
                    return true;
                }
            }
            return false;
        }
    }
}//end ReportLogic
