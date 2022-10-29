package com.group1.servicesapp;

import com.group1.servicesapp.data.MockDatabase;
import com.group1.servicesapp.data.RealDatabase;
import com.group1.servicesapp.logic.IOrderLogic;
import com.group1.servicesapp.objects.Order;
import com.group1.servicesapp.logic.OrderLogic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class OrderLogicUnitTest {
    private MockDatabase db;
    private RealDatabase rdb;
    private IOrderLogic logic;

    @Before
    public void setup(){
        db = new MockDatabase();
        logic = new OrderLogic(db);
    }

    @Test
    public void testAdd(){
        assert logic.getDataSize() == 4;
        logic.addOrder("5", "Service two", "Provider A", "Customer 3", "2021/04/04/11:00", "Sample description");
        assert logic.getDataSize() == 5;
    }

    @Test
    public void testDelete(){
        assert logic.getDataSize() == 4;
        logic.cancelOrder("4");
        assert logic.getDataSize() == 3;
    }

    @Test
    public void testGetOrder(){
        Assert.assertNull(db.getOrder("10"));
        Assert.assertNotNull(db.getOrder("1"));
    }

    @Test
    public void testSetDescription(){
        Order order = logic.getOrder("1");
        Assert.assertEquals(order.getDescription(), "Sample Description");

        logic.setDescription("1", "New Description");
        order = logic.getOrder("1");
        Assert.assertEquals(order.getDescription(), "New Description");
    }

    @Test
    public void testGetList(){
        ArrayList<Order> newList = logic.getOrderList("Customer 1");
        assert newList.size() == 1;
        ArrayList<Order> listByProv = logic.getOrdersByProvider("Provider B");
        assert listByProv.size() == 2;
    }

    @Test
    public void testAvailable(){
        Assert.assertFalse( logic.available("2021/02/16/11:30", "Provider A") );
        Assert.assertTrue( logic.available("2021/04/09/10:30", "Provider A") );
    }
}
