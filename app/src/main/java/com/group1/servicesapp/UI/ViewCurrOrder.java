package com.group1.servicesapp.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.group1.servicesapp.R;
import com.group1.servicesapp.logic.IOrderLogic;
import com.group1.servicesapp.logic.OrderLogic;
import com.group1.servicesapp.objects.Order;

public class ViewCurrOrder extends Activity {
    private IOrderLogic orderLogic;
    private Order order;

    private TextView servView, provView, cusView, startView, desView;
    private String servName, provName, cusName, startTime, desContent;
    //default value
    private String name = "Customer 3";
    private String id = "1";

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.view_curr_order);

        //fetch order from database
        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            name = intent.getStringExtra("name");
            id = intent.getStringExtra("id");
        }

        orderLogic = new OrderLogic(getApplicationContext());
        order = orderLogic.getOrder(id);

        servName = order.getServiceName();
        provName = order.getProviderName();
        cusName = order.getCustomerName();
        startTime = order.getStartTime();
        desContent = order.getDescription();

        //setup contents
        servView = findViewById(R.id.serviceNameContent);
        provView = findViewById(R.id.provNameContent);
        cusView = findViewById(R.id.custNameContent);
        startView = findViewById(R.id.startTime);
        desView = findViewById(R.id.descriptionContent);
        servView.setText(servName);
        provView.setText(provName);
        cusView.setText(cusName);
        startView.setText(startTime);
        desView.setText(desContent);
    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.done:
                backToOrderList();
                break;
            case R.id.edit:
                Intent intent2 = new Intent(this, OrderDescription.class);
                intent2.putExtra("name",name);
                intent2.putExtra("id", id);
                startActivity(intent2);
                break;
            case R.id.delete:
                openDialog();
                break;
        }
    }

    public void backToOrderList(){
        Intent intent1 = new Intent(this, OrderBrowse.class);
        intent1.putExtra("name",name);
        startActivity(intent1);
    }

    public void openDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewCurrOrder.this);
        builder.setTitle("Confirmation")
                .setMessage("Are you sure to delete this order?")
                .setCancelable(true)
                .setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        orderLogic.cancelOrder(id);
                        backToOrderList();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
