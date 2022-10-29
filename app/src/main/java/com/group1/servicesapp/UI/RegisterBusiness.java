package com.group1.servicesapp.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.group1.servicesapp.R;
import com.group1.servicesapp.logic.AccountLogic;
import com.group1.servicesapp.logic.Exceptions.InvalidCredentialException;
import com.group1.servicesapp.objects.Account;

public class RegisterBusiness extends Activity
{
    private AccountLogic accountLogic;

    @Override
    protected void onCreate(Bundle savedInstancesState)
    {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.register_business);
    }


    public void registerOnClick(View v)
    {
        EditText name, emailAddress, postalAddress, pass, conPass;
        String userName, email, address, password, conPassword;
        accountLogic = new AccountLogic(getApplicationContext());

        name = findViewById(R.id.newBusinessName);
        postalAddress = findViewById(R.id.postalAddress);
        emailAddress = findViewById(R.id.emailAddress);
        pass = findViewById(R.id.password);
        conPass = findViewById(R.id.confirmPassword);

        userName = name.getText().toString();
        address = postalAddress.getText().toString();
        email = emailAddress.getText().toString();
        password = pass.getText().toString();
        conPassword = conPass.getText().toString();

        if(accountLogic.checkPassword(password, conPassword)){
            //check to see if the credential are valid
            Account newAccount = new Account (userName, email, address, password, true);
            try {
                accountLogic.validateCredentials(newAccount);
                showMessage(v, "Business Successfully Registered");
                creationComplete(email, userName, password, address);
            }catch(InvalidCredentialException e){
                showMessage(v, "Invalid input");
            }

        }
        else
        {
            showMessage(v, "Passwords do not match");
        }
    }

    private void creationComplete(String email, String username, String password, String address){
        accountLogic.addAccount(username, email, address, password, true);
        Intent i = new Intent(this, ServiceBrowse.class);
        i.putExtra("SESSION_USER_ID", username);
        i.putExtra("business", "true");
        startActivity(i);
    }

    private void showMessage(View v, String message){
        Snackbar mySnackbar = Snackbar.make(v, message, BaseTransientBottomBar.LENGTH_LONG);
        mySnackbar.show();
    }
}
