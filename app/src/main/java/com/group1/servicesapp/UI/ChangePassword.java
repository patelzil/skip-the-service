package com.group1.servicesapp.UI;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.group1.servicesapp.R;
import com.group1.servicesapp.objects.Account;
import com.group1.servicesapp.logic.IAccountLogic;
import com.group1.servicesapp.logic.AccountLogic;

public class ChangePassword extends Activity {
    private Account account;
    private IAccountLogic accountLogic;

    private String oldPassword, newPassword, confirmPassword;
    private EditText oldInput, newInput, confirmInput;
    //default values
    private String password = "old12345";
    private String name;

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.change_password);

        accountLogic = new AccountLogic(getApplicationContext());

        //fetch from database
        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            name = intent.getStringExtra("name");
        }
        account = accountLogic.getAccount(name);
        password = account.getPassword();
    }

    public void onClick(View v){
        oldInput = findViewById(R.id.oldPass);
        newInput = findViewById(R.id.newPass);
        confirmInput = findViewById(R.id.confirmPass);
        oldPassword = oldInput.getText().toString();
        newPassword = newInput.getText().toString();
        confirmPassword = confirmInput.getText().toString();

        switch(v.getId()){
            case R.id.back:
                pass(); //do nothing, go back to view page
                break;
            case R.id.submit:
                if(oldPassword.isEmpty() || !oldPassword.equals(password) ){
                    checkAccountFail(v);    //check correct old password for safety
                } else {
                    if( newPassword.isEmpty() || !newPassword.equals(confirmPassword) ){
                        confirmFail(v);     //confirm password should be non-empty and identical to new password
                    } else {
                        if( newPassword.equals(oldPassword) ){
                            createNewFail(v);   //new created password should be different from old password
                        } else {
                            update();       //update password in database
                            pass();         //CHANGE PASSWORD SUCCESSFULLY
                        }
                    }
                }
                break;
        }//end switch
    }

    //update database
    public void update(){
        accountLogic.updateAccount(name, account.getEmail(), account.getAddress(), newPassword, account.isBusiness());
    }

    //Go back to View page
    public void pass(){
        Intent intent = getIntent();
        if (intent.getStringExtra("business").equals("true"))
        {
            intent = new Intent(this, ViewBusinessProfile.class);
        }
        else if(intent.getStringExtra("business").equals("false"))
        {
            intent = new Intent(this, ViewUserProfile.class);
        }
        intent.putExtra("name",name);
        startActivity(intent);
    }

    //Password and current username do not match
    public void checkAccountFail(View v){
        String msg = "Account is not verified";
        Snackbar mySnackbar = Snackbar.make(v, msg, BaseTransientBottomBar.LENGTH_LONG);
        mySnackbar.show();
    }

    //New password is identical to the old one
    public void createNewFail(View v){
        String msg = "Invalid new password";
        Snackbar mySnackbar = Snackbar.make(v, msg, BaseTransientBottomBar.LENGTH_LONG);
        mySnackbar.show();
    }

    //New password is not comfirmed
    public void confirmFail(View v){
        String msg = "New passwords do not match or are empty";
        Snackbar mySnackbar = Snackbar.make(v, msg, BaseTransientBottomBar.LENGTH_LONG);
        mySnackbar.show();
    }

}
