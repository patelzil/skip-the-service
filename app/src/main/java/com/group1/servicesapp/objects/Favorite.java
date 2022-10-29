package com.group1.servicesapp.objects;

public class Favorite implements IFavorite{

    private String userName;
    private String serviceName;


    public Favorite( String userName, String serviceName) {

        this.userName = userName;
        this.serviceName = serviceName;

    }

    public String getUserName() {
        return userName;
    }

    public String getServiceName() {
        return serviceName;
    }

}
