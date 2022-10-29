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
import android.widget.Toast;

import com.group1.servicesapp.R;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.logic.Exceptions.DuplicateServiceException;
import com.group1.servicesapp.logic.Exceptions.InvalidServiceException;
import com.group1.servicesapp.logic.IServiceLogic;
import com.group1.servicesapp.logic.ServiceLogic;

public class AddService extends Activity {
    private IServiceLogic serviceLogic;
    private String username;
    private String category;

    @Override
    public void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.add_service);
        Context context = getApplicationContext();
        RealDatabase db = RealDatabase.getInstance(context);
        serviceLogic = new ServiceLogic(db);

        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            username = intent.getStringExtra("username");
        }

        Spinner s = findViewById(R.id.newServiceCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categories,android.R.layout.simple_dropdown_item_1line);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = s.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Nothing
            }
        });
    }

    public void registerClicked(View v) {
        Double price = 0.0;
        String name, location, desc;
        EditText nameInput, priceInput, locationInput,descInput;

        nameInput = findViewById(R.id.newServiceName);
        priceInput = findViewById(R.id.newServicePrice);
        locationInput = findViewById(R.id.newServiceLocation);
        descInput = findViewById(R.id.newServiceDescription);

        name = nameInput.getText().toString();
        location = locationInput.getText().toString();
        String tempPrice = priceInput.getText().toString();
        if ( !tempPrice.equals("") ) {
            price = Double.parseDouble(tempPrice);
        }
        desc = descInput.getText().toString();

        try {
            serviceLogic.addService(name, username, category, location, desc, price, "");
            Intent i = new Intent(this,ViewBusinessProfile.class);
            i.putExtra("name",username);
            startActivity(i);
        } catch (DuplicateServiceException e) {
            e.printStackTrace();
            String failMSG = "This service already exists, please try a new name";
            Toast errorToast = Toast.makeText(getApplication(),failMSG, Toast.LENGTH_SHORT);
            errorToast.show();
        } catch (InvalidServiceException e) {
            e.printStackTrace();
            String failMSG = "Please enter something into the required fields";
            Toast errorToast = Toast.makeText(getApplication(),failMSG, Toast.LENGTH_SHORT);
            errorToast.show();
        }

    }

}
