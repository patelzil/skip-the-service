package com.group1.servicesapp;

import com.group1.servicesapp.UI.ProcessPayment;
import com.group1.servicesapp.logic.DeleteAccount;
import com.group1.servicesapp.logic.ServiceLogic;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunUnitTests {
    public static void main(String[] args) {
        System.out.println("\nRunning Business Unit Tests...");
        Result result = JUnitCore.runClasses(AccountUnitTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
        /////////////////////////////////////////////
        System.out.println("\nRunning Favorite Unit Tests...");
        result = JUnitCore.runClasses(AddFavoriteTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
        /////////////////////////////////////////////
        System.out.println("\nRunning 1 Unit Tests...");
        result = JUnitCore.runClasses(AddReviewTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
        /////////////////////////////////////////////
        System.out.println("\nRunning Order Unit Tests...");
        result = JUnitCore.runClasses(OrderUnitTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
        /////////////////////////////////////////////
        System.out.println("\nRunning Delete Account Unit Tests...");
        result = JUnitCore.runClasses(DeleteAccountTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
        /////////////////////////////////////////////
        System.out.println("\nRunning Login Logic Unit Tests");
        result = JUnitCore.runClasses(LoginLogicTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
        /////////////////////////////////////////////
        System.out.println("\nRunning Order Logic Unit Tests...");
        result = JUnitCore.runClasses(OrderLogicUnitTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
        /////////////////////////////////////////////
        System.out.println("\nRunning Payment Unit Tests...");
        result = JUnitCore.runClasses(paymentUnitTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
        /////////////////////////////////////////////
        System.out.println("\nRunning ProcessPay  Unit Tests...");
        result = JUnitCore.runClasses(ProcessPaymentTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
        /////////////////////////////////////////////
        System.out.println("\nRunning Report Unit Tests...");
        result = JUnitCore.runClasses(ReportUnitTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
        /////////////////////////////////////////////
        System.out.println("\nRunning Service Logic Unit Tests");
        result = JUnitCore.runClasses(SearchLogicUnitTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
        /////////////////////////////////////////////
        System.out.println("\nRunning Service Com Unit Tests...");
        result = JUnitCore.runClasses(ServiceCommunicationUnitTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
        /////////////////////////////////////////////
        System.out.println("\nRunning Service Logic Unit Tests...");
        result = JUnitCore.runClasses(ServiceLogicUnitTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
        /////////////////////////////////////////////
        System.out.println("\nRunning Report Service Unit Test");
        result = JUnitCore.runClasses(ReportUnitTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
