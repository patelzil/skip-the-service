package com.group1.servicesapp.UI;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.group1.servicesapp.R;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.objects.Account;
import com.group1.servicesapp.logic.IAccountLogic;
import com.group1.servicesapp.logic.AccountLogic;

public class EditUserProfile extends Activity{
    private IAccountLogic accountLogic;
    private Account account;

    private EditText emailInput, addressInput;
    //default values
    private String name = "Name";
    private String email = "Email";
    private String address = "Address";


    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.edit_profile);

        accountLogic = new AccountLogic(getApplicationContext());

        //fetch from database
        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            name = intent.getStringExtra("name");
        }
        account = accountLogic.getAccount(name);
        email = account.getEmail();
        address = account.getAddress();

        TextView tv = findViewById(R.id.nameContent);
        tv.setText(name);
        //set default filling in the blanks
        emailInput = findViewById(R.id.newEmail);
        addressInput = findViewById(R.id.newAddr);
        emailInput.setText(email, TextView.BufferType.EDITABLE);
        addressInput.setText(address, TextView.BufferType.EDITABLE);
    }

    public void onCLick(View v){
        email = emailInput.getText().toString();
        address = addressInput.getText().toString();

        switch (v.getId()){
            case R.id.back:
                pass(); //do nothing, go back to view page
                break;
            case R.id.submit:
                if( email.isEmpty() || address.isEmpty() ){
                    updateFail(v);
                } else {
                    update();       //update database
                    pass();         //SUBMIT SUCCESSFULLY, go back to view page
                }
                break;
        }
    }

    //update database
    public void update(){
        accountLogic.updateAccount(name, email, address, account.getPassword(), account.isBusiness()); //update database
    }

    //Go back to View page
    public void pass(){
        Intent intent = new Intent(this, ViewUserProfile.class);
        intent.putExtra("name",name);
        startActivity(intent);
    }

    //Failed to update database with invalid values
    public void updateFail(View v){
        String msg = "Email or address missing";
        Snackbar mySnackbar = Snackbar.make(v, msg, BaseTransientBottomBar.LENGTH_LONG);
        mySnackbar.show();
    }

}
