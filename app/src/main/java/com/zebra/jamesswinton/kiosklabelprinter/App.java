package com.zebra.jamesswinton.kiosklabelprinter;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class App extends Application {

    // Debugging
    private static final String TAG = "ApplicationClass";

    // Constants


    // Private Variables


    // Public Variables
    public static HashMap<Product, Integer> mBasket = null;
    public static List<Product> mProducts = null;

    @Override
    public void onCreate() {
        super.onCreate();

        // Init Basket
        mBasket = new HashMap<>();
        mProducts = new ArrayList<>(Arrays.asList(
                new Product(Product.PRODUCT_GROUP.PATISSERIE,
                        Product.PRODUCT_TYPE.COOKIE,
                        getDrawable(R.drawable.jpeg_cookies_double_choc), "Cookie Double Chocolat",
                        new BigInteger("3523680301856"), 1.75),

                new Product(Product.PRODUCT_GROUP.PATISSERIE,
                        Product.PRODUCT_TYPE.COOKIE,
                        getDrawable(R.drawable.jpeg_cookies_nature_pepite),"Cookie Nature Pepite",
                        new BigInteger("3523680301849"), 2.61),

                new Product(Product.PRODUCT_GROUP.PATISSERIE,
                        Product.PRODUCT_TYPE.MUFFIN,
                        getDrawable(R.drawable.jpeg_muffins_myrtille),"Muffins Myrtille",
                        new BigInteger("3523680414105"), 2.25),

                new Product(Product.PRODUCT_GROUP.PATISSERIE,
                        Product.PRODUCT_TYPE.MUFFIN,
                        getDrawable(R.drawable.jpeg_muffins_double_choc),"Muffins Double Chocolat",
                        new BigInteger("3523680414112"), 3.15),

                new Product(Product.PRODUCT_GROUP.PAIN,
                        Product.PRODUCT_TYPE.BREAD,
                        getDrawable(R.drawable.jpeg_petit_pain),"Petit Pain Rustique (60g)",
                        new BigInteger("3700135302556"), 0.47),

                new Product(Product.PRODUCT_GROUP.PAIN,
                        Product.PRODUCT_TYPE.BREAD,
                        getDrawable(R.drawable.jpeg_sesame),"Carre Sesame (60g)",
                        new BigInteger("3700135301955"), 0.50)
        ));
    }
}
