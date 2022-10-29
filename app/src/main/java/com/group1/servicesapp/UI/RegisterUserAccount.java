package com.group1.servicesapp.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.group1.servicesapp.R;
import com.group1.servicesapp.logic.AccountLogic;
import com.group1.servicesapp.logic.Exceptions.InvalidCredentialException;
import com.group1.servicesapp.logic.IAccountLogic;
import com.group1.servicesapp.objects.Account;

public class RegisterUserAccount extends Activity{

    private IAccountLogic add;
    private Context c;
    private Account newAccount;
    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.register_user_account);
        c = getApplicationContext();
        add = new AccountLogic(c);
    }

    public void registerOnClick(View v){
        Button register = (Button) v;
        String username, password, confirmPass, address, email;
        EditText emailInput, userInput, passInput, confirmPassInput, addressInput;

        //Finding the user input
        emailInput = findViewById(R.id.userAccountEmail);
        userInput = findViewById(R.id.userAccountUsername);
        passInput = findViewById(R.id.userAccountPassword);
        addressInput = findViewById(R.id.userAccountAddress);
        confirmPassInput = findViewById(R.id.confirmPass);

        //turning the user input into strings
        username = userInput.getText().toString();
        email = emailInput.getText().toString();
        password= passInput.getText().toString();
        address = addressInput.getText().toString();
        confirmPass = confirmPassInput.getText().toString();

        //Creation complete or Creation Failed
        //Need to check if any of the inputs are empty

        if(add.checkPassword(password, confirmPass)) {
            newAccount = new Account(username, email, password, address, false);
            try {
                add.validateCredentials(newAccount);
                creationComplete(email, username, password, address);
            }catch(InvalidCredentialException e){
                creationFailed(v, "Invalid Input");
            }
        }
        else
            creationFailed(v, "Passwords do not match");
    }

    private void creationComplete(String email, String username, String password, String address){
        add.addAccount(username, email, address, password, false);
        Intent i = new Intent(this,ServiceBrowse.class);
        i.putExtra("SESSION_USER_ID",username);
        i.putExtra("business", "false");
        startActivity(i);
    }

    private void creationFailed(View v, String failMSG){
        Snackbar mySnackbar = Snackbar.make(v, failMSG, BaseTransientBottomBar.LENGTH_LONG);
        mySnackbar.show();
    }
}
