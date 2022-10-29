package com.group1.servicesapp.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.group1.servicesapp.R;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.logic.Exceptions.DuplicateServiceException;
import com.group1.servicesapp.logic.Exceptions.InvalidServiceException;
import com.group1.servicesapp.logic.ISearchLogic;
import com.group1.servicesapp.logic.IServiceLogic;
import com.group1.servicesapp.logic.SearchLogic;
import com.group1.servicesapp.logic.ServiceLogic;
import com.group1.servicesapp.objects.Service;

import java.util.ArrayList;

public class EditService extends Activity {

    private IServiceLogic serviceLogic;
    private String serviceName, serviceUser;
    private String newCategory;
    private String[] serviceInfo;
    private Service myService;

    @Override
    public void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.edit_service);
        Context context = getApplicationContext();
        RealDatabase db = RealDatabase.getInstance(context);
        serviceLogic = new ServiceLogic(db);

        // Retrieve data from intent and rebuild object
        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            serviceUser = intent.getStringExtra("serviceUser");
            serviceName = intent.getStringExtra("serviceName");
            serviceInfo = intent.getStringArrayExtra("serviceInfo");
        }
        myService = new Service(serviceName,serviceUser,serviceInfo[1],Double.parseDouble(serviceInfo[2]),serviceInfo[3],serviceInfo[4],"");

        TextView tv = findViewById(R.id.newServiceName);
        tv.setText(serviceName);

        Spinner s = findViewById(R.id.newServiceCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categories,android.R.layout.simple_dropdown_item_1line);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newCategory = s.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                newCategory = "";
            }
        });
        // Repopulate text boxes with appropriate hints
        EditText priceBox = findViewById(R.id.newServicePrice);
        EditText locationBox = findViewById(R.id.newServiceLocation);
        EditText descBox = findViewById(R.id.newServiceDescription);

        priceBox.setHint(Double.toString(myService.getPrice()));
        locationBox.setHint(myService.getLoc());
        descBox.setHint(myService.getDesc());
    }
    public void registerClicked(View v) {
        double newPrice = 0.0;
        String newLocation, newDesc;
        String oldLocation, oldDesc, oldCategory;
        double oldPrice;
        EditText priceInput, locationInput,descInput;

        // Get old values
        oldLocation = myService.getLoc();
        oldDesc = myService.getDesc();
        oldCategory = myService.getCategory();
        oldPrice = myService.getPrice();

        // Get new values
        priceInput = findViewById(R.id.newServicePrice);
        locationInput = findViewById(R.id.newServiceLocation);
        descInput = findViewById(R.id.newServiceDescription);

        newLocation = locationInput.getText().toString();
        newDesc = descInput.getText().toString();
        String tempPrice = priceInput.getText().toString();
        if ( !tempPrice.equals("") ) {
            newPrice = Double.parseDouble(tempPrice);
        }

        // Compare to see if anything has changed
        if ( !newDesc.equals(oldDesc) || !newLocation.equals(oldLocation) || !newCategory.equals(oldCategory) || newPrice != (oldPrice)) {
            try {
                if ( newLocation.equals("") ) { newLocation = oldLocation; }
                if ( newCategory.equals("") ) { newCategory = oldCategory; }
                if ( newPrice == 0.00 ) { newPrice = oldPrice; }
                if ( newDesc.equals("") ) { newDesc = oldDesc; }

                serviceLogic.editService(serviceName,serviceUser,serviceName,newCategory,newPrice,newDesc,newLocation);
                returnToProfile();
            } catch (DuplicateServiceException e ) {
                String failMSG = "You already have a service by this name";
                Toast errorToast = Toast.makeText(getApplication(),failMSG, Toast.LENGTH_SHORT);
                errorToast.show();
            }
        } else {
            String failMSG = "All fields are the same as before";
            Toast errorToast = Toast.makeText(getApplication(),failMSG, Toast.LENGTH_SHORT);
            errorToast.show();
        }
    }

    public void deleteClicked(View v) {
        try {
            serviceLogic.deleteService(serviceName, serviceUser);
        } catch (InvalidServiceException e) {
            // This should technically never happen since we are already at the service page
            String failMSG = "Service to be deleted does not exist";
            Toast errorToast = Toast.makeText(getApplication(),failMSG, Toast.LENGTH_SHORT);
            errorToast.show();
        }
        returnToProfile();
    }

    public void cancelClicked(View v) { //Ignore everything and return
        returnToProfile();
    }
    private void returnToProfile() {
        Intent i = new Intent(this,ViewBusinessProfile.class);
        i.putExtra("name",serviceUser);
        startActivity(i);
    }
}
