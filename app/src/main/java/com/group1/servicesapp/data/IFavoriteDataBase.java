package com.group1.servicesapp.data;

import com.group1.servicesapp.objects.Favorite;

import java.util.ArrayList;

public interface IFavoriteDataBase {

    ArrayList<Favorite> getFavorite();

    void addFavorite(String usr,String service);

    long getFavoriteSize();
}
