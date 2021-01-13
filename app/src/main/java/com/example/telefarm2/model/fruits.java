package com.example.telefarm2.model;

public class fruits {

    private String desc;
    private String id;
    private String image;
    private String name;
    private double price;
    private double quantity;

    public fruits(String desc, String id, String image, String name, double price, double quantity) {
        this.desc = desc;
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public fruits() {
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
