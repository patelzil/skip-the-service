package com.group1.servicesapp.objects;

public class Order {

    private String orderID;
    private String serviceName;
    private String providerName;
    private String customerName;
    private String startTime;  //in the form of YYYY/MM/DD/TTTT, e.g. 2021/02/16/16:54 = Feb.16, 2021, 16:45 (4:45 pm)
    private String words;

    public Order(String newID, String newService, String newProvider, String newCustomer, String start, String description){
        orderID = newID;
        serviceName = newService;
        providerName = newProvider;
        customerName = newCustomer;
        startTime = start;
        words = description;
    }


    public String getOrderID(){
        return orderID;
    }

    public String getServiceName() { return serviceName; }

    public String getProviderName(){
        return providerName;
    }

    public String getCustomerName(){
        return customerName;
    }

    public String getStartTime(){
        return startTime;
    }

    public String getDescription() { return words; }


//    public String getDate(){
//        return startTime.substring(0,10);
//    } // e.g. 2021/02/16/16:54

//    public int getHour( String time ){
//        String hour = time.substring(11,13);
//        return Integer.parseInt(hour);
//    }
//
//    public int getMinute( String time ){
//        String minute = time.substring(14,16);
//        return Integer.parseInt(minute);
//    }
//
//
//    public void updateSchedule(String start, String end){
//        startTime = start;
//        endTime = end;
//    }

}
