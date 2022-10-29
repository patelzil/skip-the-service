package com.group1.servicesapp.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.group1.servicesapp.R;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.logic.AccountLogic;
import com.group1.servicesapp.logic.IAccountLogic;
import com.group1.servicesapp.logic.ISearchLogic;
import com.group1.servicesapp.logic.SearchLogic;
import com.group1.servicesapp.objects.Account;
import com.group1.servicesapp.objects.Service;

import java.util.ArrayList;

public class ViewBusinessProfile extends Activity
{
    private Account account;
    private IAccountLogic accountLogic;
    private ISearchLogic searchLogic;
    private TextView nameView, emailView, addressView, noService;
    private String email, address;
    private String username = "Plumbing mart";

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.view_business_profile);

        accountLogic = new AccountLogic(getApplicationContext());
        searchLogic = new SearchLogic(RealDatabase.getInstance(getApplicationContext()));

        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            username = intent.getStringExtra("name");
        }

        account = accountLogic.getAccount(username);
        email = account.getEmail();
        address = account.getAddress();

        nameView = findViewById(R.id.unameContent);
        emailView = findViewById(R.id.busEmailContent);
        addressView = findViewById(R.id.busAddressContent);
        noService = findViewById(R.id.no_service_text);
        nameView.setText(username);
        emailView.setText(email, TextView.BufferType.EDITABLE);
        addressView.setText(address, TextView.BufferType.EDITABLE);

        viewServiceList();
    }

    private void viewServiceList() {
        ArrayList<Service> serviceList = searchLogic.getServicesFromUser(username);
        ArrayList<String> serviceNames = new ArrayList<>();
        for ( int i = 0; i < serviceList.size(); i++ ) {
            serviceNames.add(serviceList.get(i).getName());
        }

        if (serviceNames.size() == 0) {
            String msg = "No services provided currently";
            noService.setText(msg);
            noService.setTextSize(16);
            noService.setVisibility(View.VISIBLE);
        }else {
            noService.setVisibility(View.INVISIBLE);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.service_item, serviceNames);
            ListView listView = (ListView) findViewById(R.id.services);
            listView.setAdapter(dataAdapter);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                Service s = serviceList.get(position);
                viewService(s);
            });
        }
    }

    private void viewService(Service s) {
        Intent i = new Intent(this,EditService.class);
        i.putExtra("serviceUser",username);
        i.putExtra("serviceName",s.getName());
        String[] serviceInfo = new String[]{s.getUser(), s.getCategory(), String.valueOf(s.getPrice()),s.getDesc(), s.getLoc()};
        i.putExtra("serviceInfo", serviceInfo);
        startActivity(i);
    }

    public void onClickEdit(View v) {
        Intent intent = new Intent(this, EditBusinessProfile.class);
        intent.putExtra("name", username);
        startActivity(intent);
    }

    public void onClickPassword(View v) {
        Intent intent = new Intent(this, ChangePassword.class);
        intent.putExtra("name", username);
        intent.putExtra("business", "true");
        startActivity(intent);
    }

    public void onAddService(View v) {
        Intent i = new Intent(this,AddService.class);
        i.putExtra("username",username);
        startActivity(i);
    }

    public void onClickBack(View v) {
        Intent intent = new Intent(this, ServiceBrowse.class);
        intent.putExtra("SESSION_USER_ID", username);
        startActivity(intent);
    }


}
