package com.group1.servicesapp.UI;

import android.app.Activity;
import android.util.Log;
import android.os.Bundle;
import android.widget.ListView;

import com.group1.servicesapp.R;
import com.group1.servicesapp.logic.FavoriteLogic;
import com.group1.servicesapp.logic.IFavoriteLogic;
import com.group1.servicesapp.objects.Favorite;
import java.util.ArrayList;


public class BrowseFavorite extends Activity{
    private static final String TAG="BrowseFavorite";
    private IFavoriteLogic fLogic;

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.review);//using same layout with review
        fLogic = new FavoriteLogic(getApplicationContext());

        Log.d(TAG, "onCreate: Started.");
        ListView mListFavorite;
        mListFavorite = (ListView) findViewById(R.id.listView);

        ArrayList<Favorite> favoritesList = fLogic.getFavoriteList();

        FavoriteAdapter adapter = new FavoriteAdapter(this, R.layout.adapter_favorite, favoritesList);
        mListFavorite.setAdapter(adapter);

    }

}