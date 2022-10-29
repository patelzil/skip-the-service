package com.group1.servicesapp.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.group1.servicesapp.R;
import com.group1.servicesapp.logic.AccountLogic;
import com.group1.servicesapp.logic.IAccountLogic;
import com.group1.servicesapp.objects.Account;

public class EditBusinessProfile extends Activity
{
    private Account account;
    private IAccountLogic accountLogic;
    private TextView nameView;
    private EditText emailEdit, addressEdit;
    private String email, address;
    private String username = "Plumbing mart";

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.edit_business_profile);

        accountLogic = new AccountLogic(getApplicationContext());

        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            username = intent.getStringExtra("name");
        }

        account = accountLogic.getAccount(username);
        email = account.getEmail();
        address = account.getAddress();

        // name not supposed to be edited
        nameView = findViewById(R.id.unameContent);
        nameView.setText(username);

        // fields that need to be edited
        emailEdit = findViewById(R.id.newBusEmail);
        addressEdit= findViewById(R.id.newBusAddress);
        emailEdit.setText(email, TextView.BufferType.EDITABLE);
        addressEdit.setText(address, TextView.BufferType.EDITABLE);

    }

    public void onClickSubmit(View v) {
        email = emailEdit.getText().toString();
        address = addressEdit.getText().toString();

        if(email.isEmpty() || address.isEmpty())
        {
            Snackbar mySnackbar = Snackbar.make(v, "Enter both Email and address fields", BaseTransientBottomBar.LENGTH_LONG);
            mySnackbar.show();
        }
        else
        {
            accountLogic.updateAccount(username, email, address, account.getPassword(), account.isBusiness());
            Intent intent = new Intent(this, ViewBusinessProfile.class);
            intent.putExtra("name", username);
            startActivity(intent);
        }
    }

}
