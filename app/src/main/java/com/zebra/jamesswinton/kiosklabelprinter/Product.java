package com.zebra.jamesswinton.kiosklabelprinter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.zebra.jamesswinton.kiosklabelprinter.printing.ZPL;

import java.math.BigInteger;

public class Product {

    // Product Enum
    public enum PRODUCT_GROUP { VIENNOISERIE, PATISSERIE, PAIN }
    public enum PRODUCT_TYPE { MUFFIN, COOKIE, BREAD }

    // Variables
    private PRODUCT_GROUP group;
    private PRODUCT_TYPE type;
    private Drawable image;
    private String name;
    private BigInteger ean;
    private double price;

    // Constructor
    public Product(PRODUCT_GROUP group, PRODUCT_TYPE type, Drawable image, String name, BigInteger ean, double price) {
        this.group = group;
        this.type = type;
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

    public PRODUCT_TYPE getType() {
        return type;
    }

    public void setType(PRODUCT_TYPE type) {
        this.type = type;
    }

    public int getIcon() {
        int icon;
        switch (type) {
            case MUFFIN:
                icon = R.drawable.ic_muffin;
                break;
            case COOKIE:
                icon = R.drawable.ic_cookie;
                break;
            case BREAD:
                icon = R.drawable.ic_bread;
                break;
            default:
                icon = R.drawable.ic_error;
        }

        return icon;
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public String getSingleIconBase64() {
        switch (type) {
            case MUFFIN:
                return ZPL.SINGLE_MUFFIN_IMAGE_64;
            case COOKIE:
                return ZPL.SINGLE_COOKIE_IMAGE_64;
            case BREAD:
                return ZPL.SINGLE_BREAD_IMAGE_64;
            default:
                return "";
        }
    }

    public String getMultipleIconBase64() {
        switch (type) {
            case MUFFIN:
                return ZPL.MUFFIN_IMAGE_64;
            case COOKIE:
                return ZPL.COOKIE_IMAGE_64;
            case BREAD:
                return ZPL.BREAD_IMAGE_64;
            default:
                return "";
        }
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
