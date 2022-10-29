package com.group1.servicesapp.logic;

import com.group1.servicesapp.objects.Review;

import java.util.ArrayList;

public interface IReviewLogic {
     void addReview(String userName, String serviceName, String rating, String message);
    ArrayList<Review> getReviewList(String serviceName);
}
