package com.zebra.jamesswinton.kiosklabelprinter.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zebra.jamesswinton.kiosklabelprinter.App;
import com.zebra.jamesswinton.kiosklabelprinter.MainActivity;
import com.zebra.jamesswinton.kiosklabelprinter.Product;
import com.zebra.jamesswinton.kiosklabelprinter.QueueBustingQrCodeGenerator;
import com.zebra.jamesswinton.kiosklabelprinter.R;
import com.zebra.jamesswinton.kiosklabelprinter.adapters.BasketAdapter;
import com.zebra.jamesswinton.kiosklabelprinter.adapters.ProductAdapter;
import com.zebra.jamesswinton.kiosklabelprinter.databinding.FragmentBasketBinding;
import com.zebra.jamesswinton.kiosklabelprinter.interfaces.OnProductAddToCartListener;
import com.zebra.jamesswinton.kiosklabelprinter.printing.PrintHandler;
import com.zebra.jamesswinton.kiosklabelprinter.printing.ZPL;

import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static com.zebra.jamesswinton.kiosklabelprinter.App.mBasket;
import static com.zebra.jamesswinton.kiosklabelprinter.printing.ZPL.IMAGE_1;

public class BasketFragment extends Fragment {

    // Debugging
    private static final String TAG = "BasketFragment";

    // Constants


    // Private Variables


    // Public Variables
    private FragmentBasketBinding mDataBinding = null;
    private BasketAdapter mBasketAdapter = null;
    private double mBasketTotal = 0.00;

    public BasketFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false);
        return mDataBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        // Init Back in Toolbar
        if (((MainActivity) getActivity()).getSupportActionBar() != null) {
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Init Product Adapter
        mBasketAdapter = new BasketAdapter(new OnProductAddToCartListener() {
            @Override
            public void onProductAddedToCart(@NonNull Product product, @NonNull Integer quantity) {

            }

            @Override
            public void onProductRemovedFromCart(@NonNull Product product) {
                // Remove Product
                mBasket.remove(product);

                // Recalc
                setBasketTotal();

                // UpdateCounter
                ((MainActivity) getContext()).updateBasketCounter();

                // Show Products in Adapter
                mBasketAdapter.loadBasket(mBasket);
            }
        });

        // Set RecyclerView Layout Manager & Adapter
        mDataBinding.productRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.productRecyclerView.setAdapter(mBasketAdapter);

        // Show Products in Adapter
        mBasketAdapter.loadBasket(mBasket);

        // Set Total
        setBasketTotal();

        // Set Print Label Click Listener
        mDataBinding.printLabelButton.setOnClickListener(view -> printLabel());
    }

    private void setBasketTotal() {
        mBasketTotal = 0;
        for (Product product : mBasket.keySet()) {
            mBasketTotal = mBasketTotal + product.getPrice() * mBasket.get(product);
        } mDataBinding.basketTotal.setText(getPriceFormatted(mBasketTotal) + "€");
    }

    private void printLabel() {
        // Get Products
        Product[] productsInBasket = mBasket.keySet().toArray(new Product[(mBasket.keySet().size())]);

        // Handle Single or Multiple Item Labels
        if (productsInBasket.length == 0) {
            Log.i(TAG, "No products in basket");
        } else if (productsInBasket.length == 1) {
            // Convert ZPL to Byte[]
            byte[] templateBytes = ZPL.SINGLE_ITEM_ZPL.getBytes(StandardCharsets.UTF_8);

            // Get Product
            Product product = productsInBasket[0];

            // Set Variable Data
            HashMap<String, String> variableData = new HashMap<>();
            variableData.put(ZPL.ITEM, product.getName());
            variableData.put(ZPL.PRICE, getPriceFormatted(product.getPrice()) + "€");
            variableData.put(ZPL.QUANTITY, String.valueOf(mBasket.get(product)));
            variableData.put(ZPL.BARCODE, product.getEan().toString());
            variableData.put(ZPL.IMAGE_1, product.getIconBase64(getContext()));

            PrintHandler.sendPrintJobWithContent(getContext(), templateBytes, variableData,
                    ((MainActivity) getContext()).printResultReceiver);
        } else {
            // Convert ZPL to Byte[]
            byte[] templateBytes = ZPL.MULTI_ITEM_ZPL.getBytes(StandardCharsets.UTF_8);

            // Set Variable Data
            HashMap<String, String> variableData = new HashMap<>();
            variableData.put(ZPL.PRICE, getPriceFormatted(mBasketTotal) + "€");
            variableData.put(ZPL.BARCODE, QueueBustingQrCodeGenerator
                    .generatorQrCodeStringFromBasket(mBasket));
            variableData.put(ZPL.IMAGE_1, productsInBasket[0].getIconBase64(getContext()));
            variableData.put(ZPL.IMAGE_2, productsInBasket[1].getIconBase64(getContext()));
            if (productsInBasket.length > 2) {
                variableData.put(ZPL.IMAGE_3, productsInBasket[2].getIconBase64(getContext()));
            }

            PrintHandler.sendPrintJobWithContent(getContext(), templateBytes, variableData,
                    ((MainActivity) getContext()).printResultReceiver);
        }
    }

    private String getPriceFormatted(double realNumber) {
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
        nf.setMaximumFractionDigits(10);
        return String.format("%s", nf.format(realNumber));
    }
}
