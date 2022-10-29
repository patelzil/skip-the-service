package com.group1.servicesapp.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.group1.servicesapp.R;
import com.group1.servicesapp.logic.IReportLogic;
import com.group1.servicesapp.logic.ReportLogic;

public class ReportDescription extends Activity {

    private IReportLogic report;
    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.report_description);
        report = new ReportLogic();
    }

    public void submitReportClick(View v){
        Button submit = (Button) v;
        String message;
        EditText messageInput;

        messageInput = findViewById(R.id.reportMessage);
        message = messageInput.getText().toString();

        if(report.reportPass(message))
            reportPass(v);
        else
            reportFailed(v);
    }

    private void reportPass(View v){
        Intent intent = new Intent(this, ServiceDescription.class);
        startActivity(intent);
    }
    private void reportFailed(View v){
        String failMSG = "Please enter a reason for the report";
        Snackbar mySnackbar = Snackbar.make(v, failMSG, BaseTransientBottomBar.LENGTH_LONG);
        mySnackbar.show();
    }
}
