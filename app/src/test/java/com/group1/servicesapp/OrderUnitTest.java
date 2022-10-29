package com.group1.servicesapp;

import com.group1.servicesapp.objects.Order;

import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class OrderUnitTest {

    private Order order_1 = new Order("1", "Service one", "Provider A", "Customer 1", "2021/02/16/11:30", "Sample description");
    private Order order_2 = new Order("2", "Service one", "Provider B", "Customer 2", "2021/02/17/12:30", "Sample description");
    private Order order_3 = new Order("3", "Service one", "Provider B", "Customer 3", "2021/02/17/15:00", "Sample description");
    private Order order_4 = new Order("4", "Service one", "Provider A", "Customer 3", "2021/02/18/15:30", "Sample description");


    @Test
    public void testInfo() {
        assert order_1.getOrderID().equals("1");
        assert order_2.getServiceName().equals("Service one");
        assert order_3.getProviderName().equals("Provider B");
        assert order_4.getCustomerName().equals("Customer 3");
        assert order_1.getStartTime().equals("2021/02/16/11:30");
        assert order_2.getDescription().equals("Sample description");
    }

}