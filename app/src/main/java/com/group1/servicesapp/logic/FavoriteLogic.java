package com.group1.servicesapp.logic;

import android.content.Context;
import android.database.SQLException;

import com.group1.servicesapp.data.IFavoriteDataBase;
import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.logic.Exceptions.ServiceNotFound;
import com.group1.servicesapp.objects.Favorite;

import java.util.ArrayList;

public class FavoriteLogic implements IFavoriteLogic {
    private IFavoriteDataBase db;

    public FavoriteLogic(Context context) {
        this.db = RealDatabase.getInstance(context);
    }

    public FavoriteLogic(MockDatabase ndb) {
        db = ndb;
    }

    public void addFavorite(String userName, String serviceName) {
        db.addFavorite(userName, serviceName);
    }

    public ArrayList<Favorite> getFavoriteList() throws ServiceNotFound {
        ArrayList<Favorite> favorite;
    try{
        favorite = db.getFavorite();
    }catch (
      SQLException e) {
        throw new ServiceNotFound("Favorite Not Found");
    }
        return favorite;
    }
}