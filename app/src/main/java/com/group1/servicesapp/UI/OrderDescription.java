package com.group1.servicesapp.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.group1.servicesapp.R;
import com.group1.servicesapp.logic.IOrderLogic;
import com.group1.servicesapp.logic.OrderLogic;

public class OrderDescription extends Activity {
    private IOrderLogic orderLogic;

    private EditText contentInput;
    private String name, id, content;

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.order_description);

        orderLogic = new OrderLogic(getApplicationContext());

        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            name = intent.getStringExtra("name");
            id = intent.getStringExtra("id");
        }
    }

    public void onClick(View v) {
        contentInput = findViewById(R.id.descriptionContent);
        content = contentInput.getText().toString();

        switch(v.getId()){
            case R.id.back:
                pass();
                break;
            case R.id.submit:
                if( content.isEmpty() ){
                    updateFail(v);
                } else {
                    update();
                    pass();
                }
                break;
        }
    }

    //update database
    public void update(){
        orderLogic.setDescription(id, content);
    }

    //Go back to View page
    public void pass(){
        Intent intent = new Intent(this, ViewCurrOrder.class);
        intent.putExtra("name", name);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    //Failed to update database with invalid values
    public void updateFail(View v){
        String msg = "Description is empty. Click BACK to give up sending description.";
        Snackbar mySnackbar = Snackbar.make(v, msg, BaseTransientBottomBar.LENGTH_LONG);
        mySnackbar.show();
    }

}
