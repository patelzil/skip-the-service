package com.group1.servicesapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.WindowManager;

import com.group1.servicesapp.logic.Exceptions.InvalidCategoryException;
import com.group1.servicesapp.logic.Exceptions.InvalidServiceException;
import com.group1.servicesapp.logic.Exceptions.ServiceNotFound;
import com.group1.servicesapp.objects.Account;
import com.group1.servicesapp.objects.Favorite;
import com.group1.servicesapp.objects.Order;
import com.group1.servicesapp.objects.Review;
import com.group1.servicesapp.objects.Service;

import org.sqlite.database.sqlite.SQLiteConstraintException;
import org.sqlite.database.sqlite.SQLiteDatabase;
import org.sqlite.database.sqlite.SQLiteOpenHelper;
import org.sqlite.database.SQLException;

import java.util.ArrayList;


public class RealDatabase extends SQLiteOpenHelper implements IServiceDatabase, IAccountDatabase,
        IFavoriteDataBase, IReviewDatabase, IOrderDatabase {
    private static RealDatabase sInstance;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myDatabase";

    //Table names
    private static final String TABLE_ACCOUNT = "accounts";
    private static final String TABLE_SERVICE = "services";
    private static final String TABLE_ORDER = "orders";
    private static final String TABLE_REVIEW = "reviews";
    private static final String TABLE_FAVORITE = "favorites";

    //Account table columns
    private static final String COLUMN_ACCOUNT_USER = "username";
    private static final String COLUMN_ACCOUNT_EMAIL = "email";
    private static final String COLUMN_ACCOUNT_ADDRESS = "address";
    private static final String COLUMN_ACCOUNT_PASS = "password";
    private static final String COLUMN_ACCOUNT_BUSINESS = "isBusiness";

    //Service table columns
    private static final String COLUMN_SERVICE_NAME = "name";
    private static final String COLUMN_SERVICE_USER = "user";
    private static final String COLUMN_SERVICE_CAT = "category";
    private static final String COLUMN_SERVICE_PRICE = "price";
    private static final String COLUMN_SERVICE_LOC = "location";
    private static final String COLUMN_SERVICE_DESC = "description";
    private static final String COLUMN_SERVICE_IMG = "image";

    //Order table columns
    private static final String COLUMN_ORDER_ID = "id";
    private static final String COLUMN_ORDER_SERVICE_NAME = "servName";
    private static final String COLUMN_ORDER_PROV_NAME = "provName";
    private static final String COLUMN_ORDER_CUSTOMER_NAME = "customerName";
    private static final String COLUMN_ORDER_START = "startTime";  //in the form of YYYY/MM/DDTTTT, e.g. 2021/02/16/16:54 = Feb.16, 2021, 16:45 (4:45 pm)
    private static final String COLUMN_ORDER_DESCRIPTION = "description";

    //Review table columns
    private static final String COLUMN_REVIEW_ID = "rID";
    private static final String COLUMN_REVIEW_USERNAME = "user";
    private static final String COLUMN_REVIEW_SERVICE = "service";
    private static final String COLUMN_REVIEW_RATING = "rating";
    private static final String COLUMN_REVIEW_MSG = "message";

    //Favorite table columns
    private static final String COLUMN_FAVORITE_USERNAME = "user";
    private static final String COLUMN_FAVORITE_SERVICE = "service";

    public static synchronized RealDatabase getInstance(Context context) {
        if (sInstance == null ) {
            sInstance = new RealDatabase(context.getApplicationContext());
        }
        return sInstance;
    }

    private RealDatabase(Context context){
        super(context,context.getDatabasePath(DATABASE_NAME).getPath(), null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createServices = "CREATE TABLE " + TABLE_SERVICE +
                "( " +
                    COLUMN_SERVICE_NAME + " TEXT," +
                    COLUMN_SERVICE_USER + " TEXT," +
                    COLUMN_SERVICE_PRICE + " DOUBLE(2)," +
                    COLUMN_SERVICE_CAT + " TEXT," +
                    COLUMN_SERVICE_DESC + " TEXT," +
                    COLUMN_SERVICE_LOC + " TEXT," +
                    COLUMN_SERVICE_IMG + " TEXT," +
                    "PRIMARY KEY(name,user)" +
                ")";
        String createAccounts = "CREATE TABLE " + TABLE_ACCOUNT +
                "( " +
                    COLUMN_ACCOUNT_USER + " TEXT PRIMARY KEY," +
                    COLUMN_ACCOUNT_EMAIL + " TEXT," +
                    COLUMN_ACCOUNT_ADDRESS + " TEXT," +
                    COLUMN_ACCOUNT_PASS + " TEXT," +
                    COLUMN_ACCOUNT_BUSINESS + " INTEGER" +
                ")";

        String createOrders = "CREATE TABLE " + TABLE_ORDER +
                "( " +
                    COLUMN_ORDER_ID + " TEXT PRIMARY KEY," +
                    COLUMN_ORDER_SERVICE_NAME + " TEXT," +
                    COLUMN_ORDER_PROV_NAME + " TEXT," +
                    COLUMN_ORDER_CUSTOMER_NAME + " TEXT," +
                    COLUMN_ORDER_START + " TEXT," +
                    COLUMN_ORDER_DESCRIPTION + " TEXT" +
                ")";
        String createReviews = "CREATE TABLE " + TABLE_REVIEW +
                "( " +
                COLUMN_REVIEW_SERVICE + " TEXT PRIMARY KEY," +
                COLUMN_REVIEW_USERNAME + " TEXT," +
                COLUMN_REVIEW_RATING + " TEXT," +
                COLUMN_REVIEW_MSG + " TEXT" +
                ")";
        String createFavorites = "CREATE TABLE " + TABLE_FAVORITE +
                "( " +
                COLUMN_FAVORITE_USERNAME + " TEXT PRIMARY KEY," +
                COLUMN_FAVORITE_SERVICE + " TEXT" +
                ")";

        db.execSQL(createServices);
        db.execSQL(createAccounts);
        db.execSQL(createOrders);
        db.execSQL(createReviews);
        db.execSQL(createFavorites);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion ) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE);

            onCreate(db);
        }
    }

    public void resetTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE);

        onCreate(db);
    }

    //-----------------------------BEGIN SERVICE METHODS-----------------------------


    // Begin service methods
    public long addService(String name, String user, String cat, Double price, String desc, String loc, String img) throws SQLiteConstraintException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICE_NAME, name);
        values.put(COLUMN_SERVICE_USER,user);
        values.put(COLUMN_SERVICE_CAT,cat);
        values.put(COLUMN_SERVICE_PRICE,price);
        values.put(COLUMN_SERVICE_DESC,desc);
        values.put(COLUMN_SERVICE_LOC,loc);
        values.put(COLUMN_SERVICE_IMG,img);
        return db.insertOrThrow(TABLE_SERVICE,null,values);
    }
    //Returns a service object based on a name assumed to be unique
    public ArrayList<Service> getServiceFromServiceName(String targetName) throws SQLException{
        ArrayList<Service> result = new ArrayList<>();
        String name;
        String mail;
        String cat;
        double price;
        String desc;
        String loc;
        String img;
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT * FROM " + TABLE_SERVICE +
                " WHERE " + COLUMN_SERVICE_NAME + " LIKE '%s'", "%" + targetName + "%");
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
             do {
                 name = cursor.getString(0);
                 mail = cursor.getString(1);
                 price = cursor.getDouble(2);
                 cat = cursor.getString(3);
                 desc = cursor.getString(4);
                 loc = cursor.getString(5);
                 img = cursor.getString(6);
                 result.add(new Service(name, mail, cat, price, desc, loc, img));
             } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public ArrayList<Service> getServiceFromName(String targetName) throws SQLException {
        ArrayList<Service> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT * FROM " + TABLE_SERVICE +
                " WHERE " + COLUMN_SERVICE_USER + " = '%s'",targetName);
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String mail = cursor.getString(1);
                Double price = cursor.getDouble(2);
                String cat = cursor.getString(3);
                String desc = cursor.getString(4);
                String loc = cursor.getString(5);
                String img = cursor.getString(6);
                result.add(new Service(name, mail, cat, price, desc, loc, img));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }


    public boolean checkService(String targetName) {
        boolean found = false;
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT * FROM " + TABLE_SERVICE +
                " WHERE " + COLUMN_SERVICE_NAME + " ='%s'", targetName);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            found = true;
        }
        cursor.close();
        return found;
    }

    public int deleteService(String targetName,String targetUser) {
        SQLiteDatabase db = getWritableDatabase();
        String whereString = COLUMN_SERVICE_NAME + " =? AND " + COLUMN_SERVICE_USER + " =?";
        return db.delete(TABLE_SERVICE,whereString,new String[]{targetName, targetUser});
    }

    public ArrayList<Service> getCategory(String targetCat) throws InvalidCategoryException {
        ArrayList<Service> result = new ArrayList<>();
        Service tempService;
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT * FROM " + TABLE_SERVICE +
                " WHERE " + COLUMN_SERVICE_CAT + " ='%s'", targetCat);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                tempService = new Service(cursor.getString(0),cursor.getString(1),cursor.getString(3),
                        cursor.getDouble(2),cursor.getString(4),cursor.getString(5),cursor.getString(6));
                result.add(tempService);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public long getServiceSize() {
        SQLiteDatabase db = getReadableDatabase();
        int size = 0;
        String query = "SELECT * FROM " + TABLE_SERVICE;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                size++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return size;
    }

    public int updateService(String targetName,String targetUser, String newName, String newCat,
                              Double newPrice, String newDesc, String newLoc, String newImg) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues newValues = new ContentValues();
        String whereString = COLUMN_SERVICE_NAME + " =? AND " + COLUMN_SERVICE_USER + " =?";

        newValues.put(COLUMN_SERVICE_NAME, newName);
        newValues.put(COLUMN_SERVICE_PRICE, newPrice);
        newValues.put(COLUMN_SERVICE_CAT, newCat);
        newValues.put(COLUMN_SERVICE_DESC, newDesc);
        newValues.put(COLUMN_SERVICE_LOC, newLoc);
        newValues.put(COLUMN_SERVICE_IMG,newImg);

        return db.update(TABLE_SERVICE,newValues,whereString,new String[]{targetName,targetUser});
    }

    //Get the service providers email
    public String getEmail(String serviceName) throws ServiceNotFound {
        SQLiteDatabase db = getReadableDatabase();
        String email = null;
        String username;
        Account account;
        try{
            ArrayList<Service> serviceList = getServiceFromServiceName(serviceName);
            username = serviceList.get(0).getUser();
            account = getAccount(username);
            return account.getEmail();
        }catch(SQLException e){
             throw new ServiceNotFound(serviceName);
        }
    }
    // -----------------------------End service methods-----------------------------

    // -----------------------------START ORDER METHODS-----------------------------
    public void addOrder(Order o) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_ID, o.getOrderID());
        values.put(COLUMN_ORDER_SERVICE_NAME, o.getServiceName());
        values.put(COLUMN_ORDER_PROV_NAME,o.getProviderName());
        values.put(COLUMN_ORDER_CUSTOMER_NAME,o.getCustomerName());
        values.put(COLUMN_ORDER_START,o.getStartTime());
        values.put(COLUMN_ORDER_DESCRIPTION, o.getDescription());
        db.insert(TABLE_ORDER,null,values);

    }
    public void cancelOrder(String orderID) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM " + TABLE_ORDER +
                " WHERE " + COLUMN_ORDER_ID + " ='%s'", orderID);
        db.execSQL(query);
    }
    public int getListLength() {
        int result = 0;
        SQLiteDatabase db = getReadableDatabase();
        String query = ("SELECT * FROM " + TABLE_ORDER);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                result++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public Order getOrder(String orderID) throws SQLException{
        Order result;
        String service = "";
        String provName = "";
        String cusName = "";
        String start = "";
        String description = "";
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT * FROM " + TABLE_ORDER +
                " WHERE " + COLUMN_ORDER_ID + " ='%s'", orderID);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                service = cursor.getString(1);
                provName = cursor.getString(2);
                cusName = cursor.getString(3);
                start = cursor.getString(4);
                description = cursor.getString(5);
            } while (cursor.moveToNext());
        }
        cursor.close(); // free up the cursor

        result = new Order(orderID, service, provName, cusName, start, description);

        return result;
    }

    public void setDescription(String id, String description){
        SQLiteDatabase db = getWritableDatabase();
        Order order = getOrder(id);
        String whereString = COLUMN_ORDER_ID + " =?";

        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_SERVICE_NAME, order.getServiceName());
        values.put(COLUMN_ORDER_PROV_NAME, order.getProviderName());
        values.put(COLUMN_ORDER_CUSTOMER_NAME, order.getCustomerName());
        values.put(COLUMN_ORDER_START, order.getStartTime());
        values.put(COLUMN_ORDER_DESCRIPTION, description);
        db.update(TABLE_ORDER, values, whereString, new String[]{id});
    }

    public ArrayList<Order> getOrderList(String name){
        ArrayList<Order> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT * FROM " + TABLE_ORDER +
                " WHERE " + COLUMN_ORDER_CUSTOMER_NAME + " ='%s'" +
                " OR " + COLUMN_ORDER_PROV_NAME + " ='%s'", name, name);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                result.add( new Order(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5)) );
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public  ArrayList<Order> getByProvider(String name){
        ArrayList<Order> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT * FROM " + TABLE_ORDER +
                " WHERE " + COLUMN_ORDER_PROV_NAME + " ='%s'", name);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                result.add( new Order(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5)) );
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    // END ORDER METHODS


    //------------------------Begin Favorite Methods---------------------


    public void addFavorite(String usr, String service) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_FAVORITE_USERNAME, usr);
        values.put(COLUMN_FAVORITE_SERVICE, service);
        db.insert(TABLE_FAVORITE, null, values);}

    public ArrayList<Favorite> getFavorite() throws SQLException{
        ArrayList<Favorite> result = new ArrayList<>();
        String usr;
        String service;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_FAVORITE;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                usr = cursor.getString(0);
                service = cursor.getString(1);

                result.add(new Favorite( usr, service));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public long getFavoriteSize() {
        SQLiteDatabase db = getReadableDatabase();
        int size = 0;
        String query = "SELECT * FROM " + TABLE_FAVORITE;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                size++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return size;
    }

    //------------------------Begin Account Methods---------------------
    public void addAccount(Account newAccount) {
        SQLiteDatabase db = getWritableDatabase();
        String userName = newAccount.getUsername();
        String address = newAccount.getAddress();
        String email = newAccount.getEmail();
        String password = newAccount.getPassword();
        int isBusiness = newAccount.isBusiness() ? 1 : 0;

        ContentValues values = new ContentValues();
        values.put(COLUMN_ACCOUNT_USER, userName);         //Add username
        values.put(COLUMN_ACCOUNT_EMAIL, email);            //Add email
        values.put(COLUMN_ACCOUNT_ADDRESS, address);        //Add address
        values.put(COLUMN_ACCOUNT_PASS, password);         //Add password
        values.put(COLUMN_ACCOUNT_BUSINESS, isBusiness);         //Add isBusiness boolean

        db.insert(TABLE_ACCOUNT, null, values);
    }

    public Account getAccount(String userName) {
        Account result;
        String newUserName;
        String newAddress;
        String newEmail;
        String newPass;
        int busInt;
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT * FROM " + TABLE_ACCOUNT +
                " WHERE " + COLUMN_ACCOUNT_USER + " ='%s'", userName);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                newUserName = cursor.getString(0);
                newEmail = cursor.getString(1);
                newAddress = cursor.getString(2);
                newPass = cursor.getString(3);
                busInt = cursor.getInt(4);
            } while (cursor.moveToNext());
        } else {
            return null;
        }
        cursor.close(); // free up the cursor
        boolean isBusiness = busInt == 1; // true if not 1 then false

        result = new Account(newUserName, newEmail, newAddress, newPass, isBusiness);

        return result;
    }
    public void updateAccount(String targetUser, String newMail, String newAdd, String newPass, boolean busBool) throws InvalidServiceException {
        SQLiteDatabase db = getWritableDatabase();
        int isBusiness = busBool ? 1 : 0;
        ContentValues newValues = new ContentValues();
        String whereString = COLUMN_ACCOUNT_USER + " =?";

        newValues.put(COLUMN_ACCOUNT_EMAIL, newMail);            //Add email
        newValues.put(COLUMN_ACCOUNT_ADDRESS, newAdd);        //Add address
        newValues.put(COLUMN_ACCOUNT_PASS, newPass);         //Add password
        newValues.put(COLUMN_ACCOUNT_BUSINESS, isBusiness);         //Add isBusiness boolean
        db.update(TABLE_ACCOUNT,newValues,whereString,new String[]{targetUser});
    }

    public void deleteAccount(String user) throws SQLException{
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("DELETE FROM " + TABLE_ACCOUNT +
                " WHERE " + COLUMN_ACCOUNT_USER + " ='%s'", user);
        db.execSQL(query);
    }

    // -----------------------------Start Review Methods-----------------------------


    public ArrayList<Review> getReview(String targetName) throws SQLException{
        ArrayList<Review> result = new ArrayList<>();
        String usr;
        String service;
        String rate;
        String msg;
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT * FROM " + TABLE_REVIEW +
                " WHERE " + COLUMN_REVIEW_SERVICE + " ='%s'", targetName);
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                usr = cursor.getString(0);
                service = cursor.getString(1);
                rate = cursor.getString(2);
                msg = cursor.getString(3);
                result.add(new Review(usr,service,rate,msg));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public void addReview(String usr, String service, String rating, String msg) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_REVIEW_USERNAME, usr);
        values.put(COLUMN_REVIEW_SERVICE, service);
        values.put(COLUMN_REVIEW_RATING, rating);
        values.put(COLUMN_REVIEW_MSG, msg);
        db.insert(TABLE_REVIEW, null, values);}

    public long getReviewSize() {
        SQLiteDatabase db = getReadableDatabase();
        int size = 0;
        String query = "SELECT * FROM " + TABLE_REVIEW;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                size++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return size;
    }
}

