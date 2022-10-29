package com.group1.servicesapp.logic;

import android.content.Context;
import android.database.SQLException;

import com.group1.servicesapp.data.IOrderDatabase;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.logic.Exceptions.OrderNotFound;
import com.group1.servicesapp.objects.Order;

import java.util.ArrayList;
import java.util.Enumeration;

public class OrderLogic implements IOrderLogic {
    private IOrderDatabase db;

    // null constructor
    public OrderLogic(){
        db = null;
    }

    // Constructor for the RealDatabase
    public OrderLogic(Context context) { this.db = RealDatabase.getInstance(context); }

    // Constructor for the MockDatabase
    public OrderLogic(MockDatabase mock) { this.db = mock; }


    public void addOrder( String id, String service, String pName, String cName, String start, String des ){
        Order newOrder = new Order(id, service, pName, cName, start, des);
        db.addOrder(newOrder);
    }

    public void cancelOrder(String orderID ){
        db.cancelOrder(orderID);
    }

    public int getDataSize(){
        return db.getListLength();
    }

    public Order getOrder(String orderID) throws OrderNotFound
    {
        Order result = null;

        try {
            result = db.getOrder(orderID);
        } catch (SQLException e) {
            throw new OrderNotFound("Order doesn't exist");
        }

        return result;
    }


    public boolean setDescription(String id, String description){
        boolean result = true;

        if( getOrder(id) == null )
            result = false;
        else
            db.setDescription(id, description);

        return result;
    }

    //get orders as a provider OR a customer
    public ArrayList<Order> getOrderList(String name){
        ArrayList<Order> result = db.getOrderList(name);
        return result;
    }

    //get orders as a provider
    public ArrayList<Order> getOrdersByProvider(String name){
        ArrayList<Order> result = db.getByProvider(name);
        return result;
    }

    public boolean available(String newTime, String provider){
        boolean result = true;
        ArrayList<Order> list = getOrdersByProvider(provider);

        if( list.size() > 0 ){
            for( int i = 0; i < list.size(); i++ ){
                Order order = list.get(i);
                String time = order.getStartTime();
                if( time.equals(newTime) )
                    result = false;
            }
        }

        return result;
    }

}
