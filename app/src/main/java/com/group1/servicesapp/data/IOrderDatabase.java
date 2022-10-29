package com.group1.servicesapp.data;

import com.group1.servicesapp.objects.Order;

import java.util.ArrayList;

public interface IOrderDatabase {
    public void addOrder(Order o);
    public void cancelOrder(String orderID);
    public int getListLength();
    public Order getOrder(String orderID);
    public void setDescription(String id, String description);
    public ArrayList<Order> getOrderList(String name);
    public  ArrayList<Order> getByProvider(String name);
}
