package com.group1.servicesapp.logic;

import com.group1.servicesapp.objects.Order;

import java.util.ArrayList;

public interface IOrderLogic {
    public void addOrder( String id,String service, String pName, String cName, String start,String des );
    public void cancelOrder( String orderID );
    public int getDataSize();
    public Order getOrder(String orderID);
    public boolean setDescription(String id, String description);
    public ArrayList<Order> getOrderList(String name);
    public ArrayList<Order> getOrdersByProvider(String name);
    public boolean available(String newTime, String provider);
}
