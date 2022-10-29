package com.group1.servicesapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;


public class RunIntegrationTests {
    public static void main(String[] args) {
        System.out.println("Running Service Integration Tests...");
        Result result = JUnitCore.runClasses(ServiceIntegration.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
        //////////////////////////////////////////////
        System.out.println("Running Account Integration Tests...");
        result = JUnitCore.runClasses(DeletingAddingAccountTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());
        //////////////////////////////////////////////
        System.out.println("Running Review and Favorite Integration Tests...");
        result = JUnitCore.runClasses(ReviewAndFavoriteTest.class);
        for ( Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(result.wasSuccessful());

    }
}
