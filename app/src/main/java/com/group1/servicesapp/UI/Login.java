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
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.logic.DefaultData;
import com.group1.servicesapp.logic.IDefaultData;
import com.group1.servicesapp.logic.ILoginLogic;
import com.group1.servicesapp.logic.LoginLogic;

public class Login extends Activity {

    private RealDatabase db;
    private ILoginLogic logLogic;
    private IDefaultData dataConstructor;
    //Constructor for Login
    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        System.loadLibrary("sqliteX");
        setContentView(R.layout.login);
        Context context = getApplicationContext();
        db = RealDatabase.getInstance(this);
        logLogic  = new LoginLogic(context);
        dataConstructor = new DefaultData(db);
        dataConstructor.addAccountData();
        dataConstructor.addServiceData();
        dataConstructor.addReviewData();
    }

    //When the sign in button is clicked
    public void signInOnClick(View v){
        Button signIn = (Button) v;
        String username, password;
        EditText userInput, passInput;

        //Setting the strings to the user input
        userInput = findViewById(R.id.username);
        passInput = findViewById(R.id.password);
        username = userInput.getText().toString();
        password = passInput.getText().toString();

        //if the password matches the username, then log in
        if(logLogic.userMatch(username, password))
            loginPass(username);
        else
            loginFail(v);
    }

    //when the register button is clicked
    public void registerButtonClick(View v){
        Intent intent = new Intent(this, ChooseAccount.class);
        startActivity(intent);
        //Call the register class
    }

    //When the correct username/password is inputted
    public void loginPass(String username) {
        Intent intent = new Intent(this, ServiceBrowse.class);
        intent.putExtra("SESSION_USER_ID",username);
        if(logLogic.accountBusiness()) {
            intent.putExtra("business","true");
        }else{
            intent.putExtra("business","false");
        }
        startActivity(intent);
    }

    //When the wrong username/password is inputted
    private void loginFail(View v){
        String failMSG = "Wrong Username or Password";
        Snackbar mySnackbar = Snackbar.make(v, failMSG, BaseTransientBottomBar.LENGTH_LONG);
        mySnackbar.show();
    }
}
