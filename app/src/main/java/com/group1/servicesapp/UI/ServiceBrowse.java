package com.group1.servicesapp.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.group1.servicesapp.R;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.logic.AccountLogic;
import com.group1.servicesapp.logic.Exceptions.ServiceNotFound;
import com.group1.servicesapp.logic.IAccountLogic;
import com.group1.servicesapp.logic.ISearchLogic;
import com.group1.servicesapp.logic.SearchLogic;
import com.group1.servicesapp.objects.Account;
import com.group1.servicesapp.objects.Service;

import java.util.ArrayList;

public class ServiceBrowse extends Activity implements SearchView.OnQueryTextListener {
    private Context context;
    private LinearLayout serviceLayout;
    private ISearchLogic sLogic;
    private String username;
    private IAccountLogic aLogic;
    private Account account;

    @Override
    public void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.service_browse);
        context = getApplicationContext();

        SearchView mySearchView = (SearchView) findViewById(R.id.searchText); // initiate a search view
        mySearchView.setOnQueryTextListener(this);
        sLogic = new SearchLogic(RealDatabase.getInstance(context));
        aLogic = new AccountLogic(getApplicationContext());

        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            username = intent.getStringExtra("SESSION_USER_ID");
        }
        account = aLogic.getAccount(username);

        serviceLayout = findViewById(R.id.ServiceLayout);
        createCategories();

        //fake entry
        RealDatabase db = RealDatabase.getInstance(context);
        try {
            if ( !db.checkService("Snow Men") ) {
                db.addService("Snow Men", "mail", "Snow Removal", 10.00, "test business", "wpg", "myImg");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void logoutClicked(View v) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    public void viewProfileClicked(View v) {
        Intent intent;
        if( account.isBusiness() )
            intent = new Intent(this, ViewBusinessProfile.class);
        else
            intent = new Intent(this, ViewUserProfile.class);
        intent.putExtra("name",username);
        startActivity(intent);
    }

    public void createCategories() {
        TextView myText = (TextView)findViewById(R.id.displayText);
        LinearLayout categoryLayout = (LinearLayout)findViewById(R.id.categoryLayout);
        ArrayList<Service> resultList;
        String[] categoryList = {"Cleaning","Snow Removal","Mechanic","Health Services","Pool Maintenance", "Lawn Care", "Gardening", "Plumbing"};
        int[] imageList = {R.drawable.mop,R.drawable.shovel,R.drawable.wrench,R.drawable.heartbeat,R.drawable.swimming_pool,R.drawable.grass,R.drawable.plant,R.drawable.wrench};
        for (int i = 0; i < categoryList.length; i++ ) {
            ImageButton tempImage = new ImageButton(context);
            int catIndex = i;
            int myImage = imageList[i];

            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(250, ViewGroup.LayoutParams.MATCH_PARENT);
            cardParams.weight = 1;
            tempImage.setLayoutParams(cardParams);
            tempImage.setImageResource(myImage);
            tempImage.setScaleType(ImageView.ScaleType.FIT_XY);
            tempImage.setTag(categoryList[i]);
            tempImage.setOnClickListener(v -> {
                String myCategory = categoryList[catIndex];
                myText.setText(myCategory);
                ArrayList<Service> resultList1;
                serviceLayout.removeAllViews();
                resultList1 = sLogic.search(categoryList[catIndex]);
                createCards(resultList1);
            });
            categoryLayout.addView(tempImage);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        TextView myText = (TextView)findViewById(R.id.displayText);
        try {
            ArrayList<Service> resultList = sLogic.search(query); // If search was found returns list of all results
            String categoryString = "Category:\n" + resultList.get(0).getCategory();
            myText.setText(categoryString);
            serviceLayout.removeAllViews();
            createCards(resultList);

        } catch (ServiceNotFound e) {
            e.printStackTrace();
            String failMSG = "Search Not Found";
            Toast errorToast = Toast.makeText(getApplication(),failMSG, Toast.LENGTH_SHORT);
            errorToast.show();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public void createCards(ArrayList<Service> services) {
        for ( int i = 0; i < services.size(); i++) {
            //Create card view
            CardView card = new CardView(context);
            TextView nameView = new TextView(context);
            ImageView imageView = new ImageView(context);

            Service service = services.get(i);
            String name = services.get(i).getName();
            String location = services.get(i).getLoc();
            String category = services.get(i).getCategory();

            int num = 0;
            Resources res = getResources();
            String[] categories = res.getStringArray(R.array.categories);
            int[] imageList = {R.drawable.mop,R.drawable.shovel,R.drawable.wrench,R.drawable.heartbeat,R.drawable.swimming_pool,R.drawable.grass,R.drawable.plant,R.drawable.wrench};
            for ( int j = 0; j < categories.length; j++ ) {
                if ( categories[j].equals(category) ) { // find the index of the category
                    num = j;
                }
            }
            int image = imageList[num];


            card.setTag(service.getName() + " " + service.getUser());

            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            cardParams.weight = 1;
            cardParams.setMargins(0,0,0,50);
            card.setLayoutParams(cardParams);
            card.setPadding(10, 10, 10, 10);
            card.setCardBackgroundColor(Color.parseColor("#e39b96"));
            card.setOnClickListener(v -> {
                //At the moment does nothing.  Will transfer to service page
                viewService(service);
            });

            //Create text view
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            textParams.weight = 1;
            textParams.setMargins(10, 10, 10, 10);
            nameView.setLayoutParams(textParams);
            String textString = name + "\nLocation: " + location;
            nameView.setText(textString);

            //Create image view
            RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(100,100);
            imageParams.leftMargin = 900;
            imageParams.topMargin = 10;
            imageView.setImageResource(image);
            imageView.setLayoutParams(imageParams);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            card.addView(nameView);
            card.addView(imageView);
            serviceLayout.addView(card);
        }
    }
    private void viewService(Service s) {
        Intent i = new Intent(this, ServiceDescription.class);
        i.putExtra("SESSION_USER_ID",username);
        i.putExtra("serviceName",s.getName());
        String[] serviceInfo = new String[]{s.getUser(), s.getCategory(), String.valueOf(s.getPrice()), s.getLoc()};
        i.putExtra("serviceInfo", serviceInfo);
        startActivity(i);
    }


}
