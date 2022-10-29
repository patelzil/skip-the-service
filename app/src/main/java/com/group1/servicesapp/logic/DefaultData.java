package com.group1.servicesapp.logic;

import android.database.SQLException;

import com.group1.servicesapp.data.IAccountDatabase;
import com.group1.servicesapp.data.IReviewDatabase;
import com.group1.servicesapp.data.IServiceDatabase;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.objects.Account;

import org.sqlite.database.sqlite.SQLiteConstraintException;

public class DefaultData implements IDefaultData {
    IServiceDatabase sDb;
    IAccountDatabase aDb;
    IReviewDatabase rdb;

    public DefaultData(RealDatabase db) {
        this.sDb = db;
        this.aDb = db;
        this.rdb = db;
    }


    public void addAccountData() {
        Account defaultUserAccount = new Account("user","test1@gmail.com","123 Test Ave.", "123",false);
        Account defaultBusinessAccount = new Account("BusinessUser","test2@gmail.com","1234 Test Ave.", "123",true);
        Account defaultBusinessAccount2 = new Account("BusinessUser2","test3@gmail.com","12345 Test Ave.", "123",true);
        try {
            aDb.addAccount(defaultUserAccount);
            aDb.addAccount(defaultBusinessAccount);
            aDb.addAccount(defaultBusinessAccount2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addServiceData() {
        try {
            sDb.addService("Snow Man1", "BusinessUser", "Snow Removal", 100.00, "Test service1", "Winnipeg", "myImg");
            sDb.addService("Snow Man2", "BusinessUser", "Snow Removal", 10.00, "Test service2", "Winnipeg", "myImg");
            sDb.addService("Snow Man3", "BusinessUser", "Snow Removal", 101.00, "Test service3", "Winnipeg", "myImg");
            sDb.addService("Snow Man4", "BusinessUser", "Snow Removal", 42.00, "Test service4", "Winnipeg", "myImg");
            sDb.addService("Snow Man5", "BusinessUser", "Snow Removal", 123.00, "Test service5", "Winnipeg", "myImg");
            sDb.addService("Snow Man6", "BusinessUser2", "Snow Removal", 100.00, "Test service6", "Winnipeg", "myImg");
            sDb.addService("Snow Man7", "BusinessUser2", "Snow Removal", 20.00, "Test service7", "Winnipeg", "myImg");
            sDb.addService("Snow Man8", "BusinessUser2", "Snow Removal", 40.00, "Test service8", "Winnipeg", "myImg");
            sDb.addService("Luigi's Plumbing", "BusinessUser2", "Plumbing", 400.00, "Test service9", "Winnipeg", "myImg");
            sDb.addService("Mario's Plumbing", "BusinessUser2", "Plumbing", 300.00, "Test service10", "Winnipeg", "myImg");
            sDb.addService("My Lawn Care Ltd", "BusinessUser2", "Lawn Care", 10.00, "Test service11", "Winnipeg", "myImg");
        } catch (SQLiteConstraintException e) {

        }
    }

    public void addReviewData() {
        try{
            rdb.addReview("josh","Plumbing mart","4.5", "good");
            rdb.addReview("zil","Service Depot","3.5", "good");
            rdb.addReview("wes","Drillway","5", "awesome");
            rdb.addReview("yue","Angle Master","4", "good");
            rdb.addReview("zelin","Eagle Services","1", "bad");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
