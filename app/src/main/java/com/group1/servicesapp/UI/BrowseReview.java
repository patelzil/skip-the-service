package com.group1.servicesapp.UI;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.widget.ListView;

import com.group1.servicesapp.R;
import com.group1.servicesapp.logic.IReviewLogic;
import com.group1.servicesapp.logic.ReviewLogic;
import com.group1.servicesapp.objects.Review;

import java.util.ArrayList;

public class BrowseReview extends Activity{

    private static final String TAG="BrowseReview";
    private IReviewLogic rLogic;

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.review);
        rLogic = new ReviewLogic(getApplicationContext());

        Log.d(TAG, "onCreate: Started.");
        ListView mListView;
        mListView = (ListView) findViewById(R.id.listView);

        Intent intent =getIntent();
        String currentServiceName =intent.getStringExtra(ServiceDescription.CURRENT_SERVICE_NAME);

        ArrayList<Review> reviewList = rLogic.getReviewList(currentServiceName);

        ReviewAdapter adapter = new ReviewAdapter(this, R.layout.adapter_review, reviewList);
        mListView.setAdapter(adapter);

    }

}