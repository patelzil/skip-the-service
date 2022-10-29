package com.group1.servicesapp.data;

import com.group1.servicesapp.objects.Review;

import java.util.ArrayList;

public interface IReviewDatabase {
    ArrayList<Review> getReview(String provider);

    void addReview(String usr,String service, String rating, String msg);

    long getReviewSize();

}
