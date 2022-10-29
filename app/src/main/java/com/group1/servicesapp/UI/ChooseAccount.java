package com.group1.servicesapp.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group1.servicesapp.R;

public class ChooseAccount extends Activity {

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.choose_account);
    }

    public void createUserAccountClick(View v){
        Intent intent = new Intent(this, RegisterUserAccount.class);
        startActivity(intent);
        //Call the register class
    }

    public void createBusinessAccountClick(View v){
        Intent intent = new Intent(this, RegisterBusiness.class);
        startActivity(intent);
        //Call the register class
    }
}
