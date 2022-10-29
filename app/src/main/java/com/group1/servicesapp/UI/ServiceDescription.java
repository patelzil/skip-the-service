

package com.group1.servicesapp.UI;

 import android.app.Activity;
 import android.content.Context;
 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.ImageButton;
 import android.widget.TextView;

 import com.google.android.material.snackbar.BaseTransientBottomBar;
 import com.google.android.material.snackbar.Snackbar;
 import com.group1.servicesapp.R;
 import com.group1.servicesapp.data.RealDatabase;
 import com.group1.servicesapp.logic.AccountLogic;
 import com.group1.servicesapp.logic.FavoriteLogic;
 import com.group1.servicesapp.logic.IAccountLogic;
 import com.group1.servicesapp.logic.IServiceCommunicationLogic;
 import com.group1.servicesapp.logic.ReviewLogic;
 import com.group1.servicesapp.logic.ServiceCommunicationLogic;
 import com.group1.servicesapp.objects.Account;
 import com.group1.servicesapp.objects.Service;

public class ServiceDescription extends Activity {
    public static final String CURRENT_SERVICE_NAME="current.service.name";
    private String userName = "";
    private String serviceName = "";
    private String[] serviceInfo;
    private FavoriteLogic favoriteList;
    private TextView serviceNameView, categoryView, priceView, locationView;
    private IServiceCommunicationLogic service;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.service_description);
        c = getApplicationContext();

        service = new ServiceCommunicationLogic(c);

        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            userName = intent.getStringExtra("SESSION_USER_ID");
            serviceName = intent.getStringExtra("serviceName");
            serviceInfo = intent.getStringArrayExtra("serviceInfo");
        }

        serviceNameView = findViewById(R.id.serviceNameContent);
        categoryView = findViewById(R.id.catagoryContent);
        priceView = findViewById(R.id.priceContent);
        locationView = findViewById(R.id.locationContent);
        serviceNameView.setText(serviceName);
        categoryView.setText(serviceInfo[1]);
        priceView.setText(serviceInfo[2]);
        locationView.setText(serviceInfo[3]);

        Button button = findViewById(R.id.checkReview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowseReviewPage();
            }
        });

        Button button2 = findViewById(R.id.favoriteButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFavoriteList();
                Snackbar mySnackbar = Snackbar.make(v, "Added to Favorites",
                        BaseTransientBottomBar.LENGTH_LONG);
                mySnackbar.show();
            }
        });

        Button button3 = findViewById(R.id.leaveReview);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLeaveReviewPage();
            }
        });
    }

    public void openBrowseReviewPage(){
        Intent intent3 = new Intent(this, BrowseReview.class);
        intent3.putExtra(CURRENT_SERVICE_NAME, serviceName);
        startActivity(intent3);
    }

    public void openLeaveReviewPage(){
        Intent intent = new Intent(this, LeaveReview.class);
        intent.putExtra(CURRENT_SERVICE_NAME, serviceName);
        startActivity(intent);
    }

    public void addToFavoriteList(){
        favoriteList = new FavoriteLogic(c);
        favoriteList.addFavorite(userName, serviceName);
    }

    public void startBooking(View v){
        IAccountLogic accountLogic = new AccountLogic(getApplicationContext());
        Account account = accountLogic.getAccount(userName);
        if( account.isBusiness() ){
            String msg = "Cannot book services as a business";
            Snackbar mySnackbar = Snackbar.make(v, msg, BaseTransientBottomBar.LENGTH_LONG);
            mySnackbar.show();
        } else {
            Intent intent = new Intent(this, ScheduleDateTime.class);
            intent.putExtra("service", serviceName);
            intent.putExtra("name",userName);
            intent.putExtra("serviceInfo", serviceInfo);
            startActivity(intent);
        }
    }

    public void reportClick(View v){
        ImageButton reportButton = (ImageButton) v;
        Intent intent = new Intent(this, ReportDescription.class);
        startActivity(intent);
    }

    public void contactClick(View v){
        Button contact = (Button) v;
        String contactInfo = service.contactServiceProvider(serviceName);
        Snackbar mySnackbar = Snackbar.make(v, "Service Provider's Email: " + contactInfo,
                BaseTransientBottomBar.LENGTH_LONG);
        mySnackbar.show();
    }
}
