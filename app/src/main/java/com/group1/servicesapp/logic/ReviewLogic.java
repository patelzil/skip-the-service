package com.group1.servicesapp.logic;

import android.content.Context;
import android.database.SQLException;

import com.group1.servicesapp.data.IReviewDatabase;
import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.logic.Exceptions.ServiceNotFound;
import com.group1.servicesapp.objects.Review;

import java.util.ArrayList;

public class ReviewLogic implements IReviewLogic {
    private IReviewDatabase db;

    public ReviewLogic(Context context) {
        db = RealDatabase.getInstance(context);
    }

    public ReviewLogic(MockDatabase ndb) {
        db = ndb;
    }

    public void addReview(String userName, String serviceName, String rating, String message) {
        db.addReview(userName, serviceName, rating, message);
    }

    public ArrayList<Review> getReviewList(String serviceName) throws ServiceNotFound {
        ArrayList<Review> reviews;
        try {
            reviews = db.getReview(serviceName);
        }catch (SQLException e) {
            throw new ServiceNotFound("Service Not Found");
        }

        return reviews;
    }

}
