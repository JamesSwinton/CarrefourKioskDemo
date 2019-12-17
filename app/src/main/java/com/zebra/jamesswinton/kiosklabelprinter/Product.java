package com.zebra.jamesswinton.kiosklabelprinter;

import android.graphics.drawable.Drawable;

import java.math.BigInteger;

public class Product {

    // Product Enum
    public enum PRODUCT_GROUP { VIENNOISERIE, PATISSERIE, PAIN }

    // Variables
    private PRODUCT_GROUP group;
    private Drawable image;
    private String name;
    private BigInteger ean;
    private double price;

    // Constructor
    public Product(PRODUCT_GROUP group, Drawable image, String name, BigInteger ean, double price) {
        this.group = group;
        this.image = image;
        this.name = name;
        this.ean = ean;
        this.price = price;
    }

    // Getters & Setters
    public PRODUCT_GROUP getGroup() {
        return group;
    }

    public void setGroup(PRODUCT_GROUP group) {
        this.group = group;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getEan() {
        return ean;
    }

    public void setEan(BigInteger ean) {
        this.ean = ean;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
