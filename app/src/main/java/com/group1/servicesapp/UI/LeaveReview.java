package com.group1.servicesapp.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.group1.servicesapp.R;
import com.group1.servicesapp.logic.ReviewLogic;


public class LeaveReview extends Activity{
    private String name = "";
    private String service = "";
    ReviewLogic reviewList;
    RatingBar ratingInput;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.leave_review);
        c = getApplicationContext();

        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            name = intent.getStringExtra("Login.EXTRA_TEXT");//need a putEXTRA() in Login
        }

        Intent intent2 = getIntent();
        if( intent.getExtras() != null ){
            service = intent2.getStringExtra("serviceDescription.EXTRA_TEXT");
        }

        Button button =(Button) findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitOnClick();
                toBrowsePage();
            }
        });
    }

    public void submitOnClick(){
        String rating;
        EditText reviewInput;

        //Finding the user input
        reviewInput = findViewById(R.id.reportMessage);
        ratingInput = findViewById(R.id.ratingBar);

        String review = reviewInput.getText().toString();
        rating = String.valueOf(ratingInput.getRating());

        submitReview(name,service,rating,review);


    }

    private void submitReview(String usr, String service, String rating, String msg){

        reviewList = new ReviewLogic(c);
        reviewList.addReview(usr, service, rating, msg);

    }

    private void toBrowsePage(){

        Intent intent = new Intent(this, BrowseReview.class);
        startActivity(intent);
    }

}