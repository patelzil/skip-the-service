package com.group1.servicesapp.data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.group1.servicesapp.logic.Exceptions.InvalidCategoryException;
import com.group1.servicesapp.logic.Exceptions.InvalidServiceException;
import com.group1.servicesapp.logic.Exceptions.ServiceNotFound;
import com.group1.servicesapp.objects.Account;
import com.group1.servicesapp.objects.Favorite;
import com.group1.servicesapp.objects.Order;
import com.group1.servicesapp.objects.Review;
import com.group1.servicesapp.objects.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;


public class MockDatabase implements IAccountDatabase, IOrderDatabase, IServiceDatabase,
        IReviewDatabase, IFavoriteDataBase
{
    private Hashtable <String,Order>OrderList = new Hashtable<>();
    private ArrayList<Account> accountTable = new ArrayList<>();
    private ArrayList<Service> sList = new ArrayList<>();
    private ArrayList<Review> reviewList= new ArrayList<>();
    private ArrayList<Favorite> favoriteList= new ArrayList<>();

    public MockDatabase() {
        createBusinessData();   //Create business fake
        createOrderData();      //Create order fake
        createServiceList();    //Create service fake
        createUserData();
        createReviewData();
    }
    //ORDER FUNCTIONS
    private void createReviewData(){
        Review r1 = new Review("josh","Plumbing mart","4.5", "good");
        Review r2 = new Review("zil","Service Depot","3.5", "good");
        Review r3 = new Review("wes","Drillway","5", "awesome");
        Review r4 = new Review("yue","Angle Master","4", "good");
        Review r5 = new Review("zelin","Eagle Services","1", "bad");

        reviewList.add(r1);
        reviewList.add(r2);
        reviewList.add(r3);
        reviewList.add(r4);
        reviewList.add(r5);
    }

    private void createOrderData(){
        OrderList.put("1", new Order("1", "Service one", "Provider A", "Customer 1",
                "2021/02/16/11:30", "Sample Description"));
        OrderList.put("2", new Order("2", "Service one", "Provider B", "Customer 2",
                "2021/02/17/12:30", "Sample Description"));
        OrderList.put("3", new Order("3", "Service one", "Provider B", "Customer 3",
                "2021/02/17/15:00", "Sample Description"));
        OrderList.put("4", new Order("4", "Service one", "Provider A", "Customer 3",
                "2021/02/18/15:30", "Sample Description"));
    }

    private void createBusinessData() {
        Account bus1 = new Account("Plumbing mart", "mart.plumbing@info.com", "Adam street, R1N 8V4","12345", true);
        Account bus2 = new Account("Service Depot", "servicedepo@info.com", "Adam street, R1N 8V4", "12345", true);
        Account bus3 = new Account("Drillway",  "drillway@info.com", "Adam street, R1N 8V4", "12345", true);
        Account bus4 = new Account("Angle Master",  "anglemaster@info.com", "Adam street, R1N 8V4", "12345", true);
        Account bus5 = new Account("Plumbing Depot","plumbingdepot@info.com", "Adam street, R1N 8V4", "12345", true);
        Account bus6 = new Account("Plumbing Master","plumbingmaster@info.com", "Adam street, R1N 8V4", "12345", true);
        Account bus7 = new Account("Eagle Services",  "eagleservices@info.com", "Adam street, R1N 8V4", "12345", true);
        Account bus8 = new Account("Drillmart", "drillmart@info.com", "Adam street, R1N 8V4", "12345", true);

        accountTable.add(bus1);
        accountTable.add(bus2);
        accountTable.add(bus3);
        accountTable.add(bus4);
        accountTable.add(bus5);
        accountTable.add(bus6);
        accountTable.add(bus7);
        accountTable.add(bus8);
    }

    private void createServiceList() {
        sList.add(new Service( "Two small men","BusinessUser2", "Moving", 5, "Rob", "mb", "myImg"));
        sList.add(new Service( "Other Service","BusinessUser2", "Raking", 1, "Will", "mb", "myImg"));
        sList.add(new Service( "Some Service","BusinessUser2", "Shoveling", 10, "Josh", "mb", "myImg"));
        sList.add(new Service( "Some Service2","BusinessUser2", "Shoveling", 45, "Josh", "mb", "myImg"));
        sList.add(new Service( "Some Service3","BusinessUser2", "Weeding", 100, "Bill", "mb", "myImg"));
        sList.add(new Service( "Some Service4","BusinessUser", "Snow Removal", 322, "Rob", "mb", "myImg"));
        sList.add(new Service( "Some Service5","BusinessUser", "Cleaning", 344, "Josh", "mb", "myImg"));
        sList.add(new Service( "Some Service6","BusinessUser", "Moving", 2, "John", "mb", "myImg"));
        sList.add(new Service( "Some funky names","BusinessUser", "Moving", 34, "Wesley", "mb", "myImg"));
    }
    //Add some non-business accounts
    private void createUserData(){
        Account user1 = new Account("wes", "123@abc.ca", "Wes Street", "123", false);
        Account user2 = new Account("josh", "abc@abc.ca", "Josh Street", "abc", false);
        Account user3 = new Account("zelin", "dfs@abc.ca", "Zelin Street", "zel", false);
        Account user4 = new Account("zel", "zel@abc.ca", "Zel Street", "Zelin", false);
        Account user5 = new Account("yue", "yue@abc.ca", "Yue Street", "yue", false);

        accountTable.add(user1);
        accountTable.add(user2);
        accountTable.add(user3);
        accountTable.add(user4);
        accountTable.add(user5);
    }
    // ---------------------Order methods---------------------
    public void addOrder(Order o){
        OrderList.put(o.getOrderID(),o);
    }

    public void cancelOrder(String orderID){
        OrderList.remove(orderID);
    }

    public int getListLength(){
        return OrderList.size();
    }

    public Order getOrder(String orderID) {
        Order result = null;
        if( OrderList.containsKey(orderID) )
            result = OrderList.get(orderID);
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setDescription(String id, String description) {
        Order order = getOrder(id);
        Order newOrder = new Order(order.getOrderID(), order.getServiceName(), order.getProviderName(), order.getCustomerName(), order.getStartTime(), description);
        OrderList.replace(id, order, newOrder);
    }

    public ArrayList<Order> getOrderList(String name){
        ArrayList<Order> result = new ArrayList<>();
        ArrayList<Order> src = new ArrayList<>(OrderList.values());
        for( int i = 0; i < src.size(); i++ ){
            Order order = src.get(i);
            if( order.getProviderName().equals(name) || order.getCustomerName().equals(name) )
                result.add(order);
        }
        return result;
    }

    public ArrayList<Order> getByProvider(String name){
        ArrayList<Order> result = new ArrayList<>();
        ArrayList<Order> src = new ArrayList<>(OrderList.values());
        for( int i = 0; i < src.size(); i++ ){
            Order order = src.get(i);
            if( order.getProviderName().equals(name) )
                result.add(order);
        }
        return result;
    }

    // ---------------------Begin account methods---------------------
    //Get the account from the table if it exist
    public Account getAccount(String username) {
        for (int i = 0; i < accountTable.size(); i++) {
            String currentUser = accountTable.get(i).getUsername();
            if (currentUser.equals(username)) {
                return accountTable.get(i);
            }
        }
        return null;
    }

    //add an account if the username isn't already taken
    public void addAccount(Account newAccount){
        Account exist = getAccount(newAccount.getUsername());
        if(exist == null)
            accountTable.add(newAccount);
    }

    //update an account
    public void updateAccount(String targetUser, String newMail, String newAdd, String newPass, boolean busBool){
        Account exist = getAccount(targetUser);
        int index = accountTable.indexOf(exist);
        Account newAccount = new Account(targetUser,newMail,newAdd, newPass, busBool);
        accountTable.set(index, newAccount);
    }

    //Delete an account if it exist
    public void deleteAccount(String deletedAccountUsername){
        try {
            for (int i = 0; i < accountTable.size(); i++) {
                String currentUser = accountTable.get(i).getUsername();
                if (currentUser.equals(deletedAccountUsername))
                    accountTable.remove(i);
            }
        }catch (NullPointerException e){
            System.out.print("Null pointer caught in getAccount. Account doesn't exist");
        }
    }

    //---------------------Begin review methods---------------------
    public void addReview(String usr,String service, String rating, String msg) {
        Review r = new Review(usr,service,rating,msg);
        reviewList.add(r);
    }
    public ArrayList<Review> getReview(String targetService){
        ArrayList<Review> result = new ArrayList<>();
        for (Review r : reviewList) {
            if (r.getServiceName().equals(targetService)) {
                result.add(r);
            }
        }
        return result;

    }

    public int getReviewListSize(){
        return reviewList.size();
    }
    public long getReviewSize(){
        return reviewList.size();
    }
    //---------------------Begin favorite methods---------------------
    public void addFavorite(String usr,String service) {
        Favorite f = new Favorite(usr,service);
        favoriteList.add(f);
    }

    public ArrayList<Favorite> getFavorite() {
        return favoriteList;
    }

    public long getFavoriteSize(){
        return favoriteList.size();
    }
    public int getFavoriteListSize(){
        return favoriteList.size();
    }

    // ---------------------Begin service methods---------------------
    public ArrayList<Service> getServiceFromServiceName(String name) {
        ArrayList<Service> result = new ArrayList<>();
        for (Service s : sList) {
            if (s.getName().equals(name)) {
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Service> getServiceFromName(String username) { // In progress
        ArrayList<Service> result = new ArrayList<>();
        for (Service s : sList) {
            if (s.getUser().equals(username)) {
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Service> getCategory(String category) throws InvalidCategoryException {
        ArrayList<Service> result = new ArrayList<>();
        for (Service s : sList) {
            if (s.getCategory().equals(category)) {
                result.add(s);
            }
        }
        return result;
    }

    public long getServiceSize() {
        return sList.size();
    }

    public int deleteService(String name,String user) {
        int result = 0;
        Iterator<Service> iterator = sList.iterator();
        while (iterator.hasNext()) {
            Service s = iterator.next();
            if ( s.getName().equals(name) && s.getUser().equals(user) ) {
                iterator.remove();
                result++;
            }
        }
        return result;
    }
    public int updateService(String targetName, String targetUser, String newName, String newCat, Double newPrice, String newDesc, String newLoc, String newImg) throws InvalidServiceException {
        Service s = null;
        int result = 0;
        for (Service temp : sList) {
            if (temp.getName().equals(targetName) && temp.getUser().equals(targetUser)) {
                s = temp;
                result++;
            }
        }
        if ( s != null) {
            s.setName(newName);
            s.setCategory(newCat);
            s.setPrice(newPrice);
            s.setDesc(newDesc);
            s.setLoc(newLoc);
            s.setImg(newImg);
        }
        return result;
    }

    public boolean checkService(String targetName) {
        boolean result = false;
        for (Service service : sList) {
            if (service.getName().equals(targetName)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public long addService(String name, String user, String cat, Double price, String desc, String loc,String img) {
        sList.add(new Service(name,user,cat,price,desc,loc,img));
        return 0;
    }

    //get the service providers email
    public String getEmail(String serviceName) throws ServiceNotFound{
        String email = null;
        String username = null;
            //Search the list for the username
            for(int i = 0; i < sList.size(); i++) {
                if (sList.get(i).getUser().equals(serviceName)) {
                    username = sList.get(i).getUser();
                    break;
                } else {
                    throw new ServiceNotFound(serviceName);
                }
            }
            //Search the account table for the username's email
            if (username != null) {
                for (int j = 0; j < accountTable.size(); j++) {
                    if (accountTable.get(j).getUsername().equals(username)) {
                        return accountTable.get(j).getEmail();
                    }
                }
            }
        return email;
    }
}
