package com.group1.servicesapp;

import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.logic.IDeleteAccount;
import com.group1.servicesapp.logic.IReviewLogic;
import com.group1.servicesapp.logic.ReviewLogic;
import com.group1.servicesapp.objects.Review;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddReviewTest {

    private MockDatabase db;
    private IReviewLogic reviewLogic;

    @Before
    public void setup(){
        db = new MockDatabase();
        reviewLogic = new ReviewLogic(db);
    }

    @Test
    public void testAddReview() {
        int originalSize = db.getReviewListSize();
        reviewLogic.addReview("josh", "Plumbing mart", "4.5", "good");
        reviewLogic.addReview("zil", "Service Depot", "3.5", "good");
        reviewLogic.addReview("wes", "Drillway", "5", "awesome");
        reviewLogic.addReview("yue", "Angle Master", "4", "good");
        reviewLogic.addReview("zelin", "Eagle Services", "10", "stellar");

        int size = db.getReviewListSize();
        assertEquals("add error", 5, size - originalSize);
    }

    @Test
    public void getReviews() {
        reviewLogic.addReview("josh","Unique Name","4.5", "good");
        reviewLogic.addReview("someone else","Unique Name","4.5", "horrible");
        assert(reviewLogic.getReviewList("Unique Name").size() == 2);
    }
}
