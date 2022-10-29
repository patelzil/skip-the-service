package com.group1.servicesapp.objects;

public class Service implements IService {
    String name;
    String user;
    String category;
    double price;
    String desc;
    String loc;
    String img;

    public Service(String newName, String username,String newCategory, double newPrice, String newDesc, String newLoc, String newImg) {
        name = newName;
        user = username;
        category = newCategory;
        price = newPrice;
        desc = newDesc;
        loc = newLoc;
        img = newImg;
    }

    public double getPrice() {
        return this.price;
    }
    public void setPrice(double newPrice) { this.price = newPrice; }

    public String getName() { return this.name; }
    public void setName(String newName) { this.name = newName; }

    public String getUser() { return this.user; }
    public void setUser(String newUser) { this.user = newUser; }

    public String getCategory() { return this.category; }
    public void setCategory(String newCategory) { this.category = newCategory; }

    public String getDesc() {  return this.desc; }
    public void setDesc(String newDesc) { this.desc = newDesc; }

    public String getLoc() { return this.loc; }
    public void setLoc(String newLoc) { this.loc = newLoc; }

    public String getImg() { return this.img; }
    public void setImg(String newImg) { this.img = newImg; }
}
