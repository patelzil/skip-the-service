package com.group1.servicesapp.logic;

import com.group1.servicesapp.objects.Favorite;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IFavoriteLogic {
    void addFavorite(String userName, String serviceName);

    ArrayList<Favorite> getFavoriteList();
}
