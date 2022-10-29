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
import com.group1.servicesapp.logic.PaymentLogic;
import com.group1.servicesapp.objects.Account;
import com.group1.servicesapp.objects.Payment;

public class ProcessPayment extends Activity {

    private PaymentLogic paymentLogic;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstancesState)
    {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.process_payment);

        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            name = intent.getStringExtra("name");
        }
    }

    public void checkoutOnClick(View v)
    {
        EditText cardNumber, cvcNumber, validDate, cardHolderName;
        cardNumber = findViewById(R.id.cardNumber);
        cvcNumber = findViewById(R.id.cvcNum);
        validDate = findViewById(R.id.validDate);
        cardHolderName = findViewById(R.id.cardHolder);

        String cardNum, cvcNum, vDate, name;
        cardNum = cardNumber.getText().toString();
        cvcNum = cvcNumber.getText().toString();
        vDate = validDate.getText().toString();
        name = cardHolderName.getText().toString();

        Payment newPayment = new Payment(cardNum, cvcNum, vDate, name);
        paymentLogic=new PaymentLogic();

        try {
            paymentLogic.formatCheck(newPayment);
            showMessage(v, "Payment processed");
            checkoutPass(v);

        }catch(InvalidCredentialException e){
            showMessage(v, "Invalid input");
        }
    }

    private void showMessage(View v, String message){
        Snackbar s= Snackbar.make(v, message, BaseTransientBottomBar.LENGTH_LONG);
        s.show();
    }

    private void checkoutPass(View v){
        Intent intent = new Intent(this, ServiceBrowse.class);
        intent.putExtra("SESSION_USER_ID",name);
        startActivity(intent);
    }

}
