package com.group1.servicesapp.objects;

public class Review implements IReview{

    private String userName;
    private String serviceName;
    private String rating;
    private String message;

    public Review( String userName, String serviceName, String rating, String message) {

        this.userName = userName;
        this.serviceName = serviceName;
        this.rating = rating;
        this.message = message;
    }



    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }

    public String getRating() {
        return rating;
    }

    public String getServiceName() {
        return serviceName;
    }

}
