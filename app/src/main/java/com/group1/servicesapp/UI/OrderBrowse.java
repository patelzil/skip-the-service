package com.group1.servicesapp.UI;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.group1.servicesapp.R;
import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.logic.AccountLogic;
import com.group1.servicesapp.logic.IAccountLogic;
import com.group1.servicesapp.logic.OrderLogic;
import com.group1.servicesapp.logic.IOrderLogic;
import com.group1.servicesapp.objects.Account;
import com.group1.servicesapp.objects.Order;

import java.util.ArrayList;

public class OrderBrowse extends Activity {
    private IOrderLogic orderLogic;
    private ArrayList<Order> orders;
    private IAccountLogic accountLogic;
    private Account account;
    //default value
    private String name = "Customer 3";

    private LinearLayout orderLayout;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.order_browse);

        context = getApplicationContext();
        accountLogic = new AccountLogic(context);

        //fetch order from database
        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            name = intent.getStringExtra("name");
        }
        account = accountLogic.getAccount(name);

        orderLogic = new OrderLogic(getApplicationContext());
        orders = orderLogic.getOrderList(name);

        orderLayout = findViewById(R.id.OrderLayout);
        createList(orders);  //show orders related to the user
    }

    public void onClick(View v) {
        Intent intent;
        if( account.isBusiness() )
            intent = new Intent(this, ViewBusinessProfile.class);
        else
            intent = new Intent(this, ViewUserProfile.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }

    public void createList(ArrayList<Order> orders){
        for( int i = 0; i < orders.size(); i++) {
            //Create card view
            CardView card = new CardView(context);
            TextView textView = new TextView(context);

            String id = (orders.get(i)).getOrderID();
            String provName = (orders.get(i)).getProviderName();
            String cusName = (orders.get(i)).getCustomerName();

            card.setTag(id);

            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            cardParams.weight = 1;
            cardParams.setMargins(0,0,0,50);
            card.setLayoutParams(cardParams);
            card.setPadding(10, 10, 10, 10);
            card.setCardBackgroundColor(Color.parseColor("#E6FF9696"));

            //Create text view
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            textParams.weight = 1;
            textParams.setMargins(10, 10, 10, 10);
            textView.setLayoutParams(textParams);
            textView.setTextColor(Color.parseColor("#FF000000"));
            String textString = "Order " + id + "\nProvided by: : " + provName;
            textView.setText(textString);

            //Set card
            card.addView(textView);
            orderLayout.addView(card);

            //set card clickable
            card.setClickable(true);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(OrderBrowse.this, ViewCurrOrder.class);
                    intent2.putExtra("name", name);
                    intent2.putExtra("id", id);
                    startActivity(intent2);
                }
            });
        }//end for
    }
}
