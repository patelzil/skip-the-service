package com.group1.servicesapp.objects;

public interface IService {


    double getPrice();
    void setPrice(double newPrice);

    String getName();
    void setName(String newName);

    String getUser();
    void setUser(String newMail);

    String getCategory();
    void setCategory(String newCategory);

    String getDesc();
    void setDesc(String newDesc);

    String getLoc();
    void setLoc(String newLoc);

    String getImg();
    void setImg(String newImg);
}
