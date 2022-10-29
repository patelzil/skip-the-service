package com.group1.servicesapp.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.group1.servicesapp.R;
import com.group1.servicesapp.logic.IOrderLogic;
import com.group1.servicesapp.logic.OrderLogic;

public class OrderConfirm extends Activity {
    private IOrderLogic orderLogic;

    private TextView servView, provView, catView, priceView, locView, scheduleView;
    private EditText desView;
    private String provider, category, price, location, schedule;
    //default value
    private String service = "Service one";
    private String name = "Customer 3";
    private String description = "Leave a message to the provider. (Optional)";
    private String[] serviceInfo = {"Provider A", "sample_category", "sample_price", "sample_location"};

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.order_confirm);

        orderLogic = new OrderLogic(getApplicationContext());

        //fetch info from previous activity
        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            name = intent.getStringExtra("name");
            service = intent.getStringExtra("service");
            schedule = intent.getStringExtra("schedule");
            serviceInfo = intent.getStringArrayExtra("serviceInfo");
        }
        provider = serviceInfo[0];
        category = serviceInfo[1];
        price = serviceInfo[2];
        location = serviceInfo[3];

        //setup contents
        servView = findViewById(R.id.serviceNameContent);
        catView = findViewById(R.id.catagoryContent);
        provView = findViewById(R.id.provNameContent);
        priceView = findViewById(R.id.priceContent);
        locView = findViewById(R.id.locationContent);
        scheduleView = findViewById(R.id.time);
        desView = findViewById(R.id.descriptionContent);

        servView.setText(service);
        catView.setText(category);
        provView.setText(provider);
        priceView.setText(price);
        locView.setText(location);
        scheduleView.setText(schedule);
        description = desView.getText().toString();
    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
                Intent intent1 = new Intent(this, ScheduleDateTime.class);
                intent1.putExtra("name",name);
                intent1.putExtra("service", service);
                intent1.putExtra("serviceInfo", serviceInfo);
                startActivity(intent1);
                break;
            case R.id.submit:
                update();
                Intent intent2 = new Intent(this, ProcessPayment.class);
                intent2.putExtra("name",name);
                startActivity(intent2);
                break;
        }
    }

    public void update(){
        long longStamp = System.currentTimeMillis()/1000;
        String stamp = String.valueOf(longStamp);
        orderLogic.addOrder(stamp,service,provider,name,schedule,description);
    }
}
