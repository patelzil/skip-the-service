package com.group1.servicesapp.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.group1.servicesapp.R;
import com.group1.servicesapp.logic.AccountLogic;
import com.group1.servicesapp.logic.IAccountLogic;
import com.group1.servicesapp.objects.Account;


public class ViewUserProfile extends Activity{
    private Account account;
    private IAccountLogic accountLogic;

    private TextView nameView, emailView, addressView;
    private String email, address;

    //default values
    private String name = "Plumbing mart";

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.view_profile);

        accountLogic = new AccountLogic(getApplicationContext());

        //fetch from database by username
        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            name = intent.getStringExtra("name");
        }
        account = accountLogic.getAccount(name);
        email = account.getEmail();
        address = account.getAddress();

        //setup contents
        nameView = findViewById(R.id.nameContent);
        emailView = findViewById(R.id.emailContent);
        addressView = findViewById(R.id.addressContent);
        nameView.setText(name);
        emailView.setText(email);
        addressView.setText(address);

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewFavorite:
                Intent intent4 = new Intent(this, BrowseFavorite.class);
                intent4.putExtra("SESSION_USER_ID",name);
                startActivity(intent4);
                break;
            case R.id.viewOrders:
                Intent intent5 = new Intent(this, OrderBrowse.class);
                intent5.putExtra("name", name);
                startActivity(intent5);
                break;
            case R.id.done:
                Intent intent1 = new Intent(this, ServiceBrowse.class);
                intent1.putExtra("SESSION_USER_ID",name);
                startActivity(intent1);
                break;
            case R.id.edit:
                Intent intent2 = new Intent(this, EditUserProfile.class);
                intent2.putExtra("name", name);
                startActivity(intent2);
                break;
            case R.id.changePassword:
                Intent intent3 = new Intent(this, ChangePassword.class);
                intent3.putExtra("name", name);
                intent3.putExtra("business", "false");
                startActivity(intent3);
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }
    }
}
