package com.zebra.jamesswinton.kiosklabelprinter.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zebra.jamesswinton.kiosklabelprinter.App;
import com.zebra.jamesswinton.kiosklabelprinter.MainActivity;
import com.zebra.jamesswinton.kiosklabelprinter.Product;
import com.zebra.jamesswinton.kiosklabelprinter.adapters.ProductAdapter;
import com.zebra.jamesswinton.kiosklabelprinter.R;
import com.zebra.jamesswinton.kiosklabelprinter.databinding.FragmentPastriesBinding;

import java.util.ArrayList;
import java.util.List;

public class PastriesFragment extends Fragment {

    // Debugging
    private static final String TAG = "BreadFragment";

    // Constants


    // Private Variables
    private FragmentPastriesBinding mDataBinding;
    private ProductAdapter mProductAdapter;

    // Public Variables


    public PastriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pastries, container,
                false);
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
        mProductAdapter = new ProductAdapter((MainActivity) getActivity());

        // Set RecyclerView Layout Manager & Adapter
        mDataBinding.productRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mDataBinding.productRecyclerView.setAdapter(mProductAdapter);

        // Get Bread Products
        List<Product> pastryProducts = new ArrayList<>();
        for (Product product : App.mProducts) {
            if (product.getGroup() == Product.PRODUCT_GROUP.PATISSERIE) {
                pastryProducts.add(product);
            }
        }

        // Show Products in Adapter
        mProductAdapter.loadProducts(pastryProducts);
    }

}
