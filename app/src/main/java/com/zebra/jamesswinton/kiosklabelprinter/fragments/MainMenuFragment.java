package com.zebra.jamesswinton.kiosklabelprinter.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zebra.jamesswinton.kiosklabelprinter.MainActivity;
import com.zebra.jamesswinton.kiosklabelprinter.R;
import com.zebra.jamesswinton.kiosklabelprinter.databinding.FragmentMainMenuBinding;

public class MainMenuFragment extends Fragment {

    // Debugging
    private static final String TAG = "MainMenuFragment";

    // Constants


    // Private Variables


    // Public Variables
    private FragmentMainMenuBinding mDataBinding;

    public MainMenuFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_menu, container,
                false);
        return mDataBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        // Disable Back in Toolbar
        if (((MainActivity) getActivity()).getSupportActionBar() != null) {
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        // Init Click Listeners
        mDataBinding.breadContainer.setOnClickListener(view -> {
            if (getActivity() != null) {
                ((MainActivity) getActivity()).loadBreadFragment();
            }
        });

        mDataBinding.pastriesContainer.setOnClickListener(view -> {
            if (getActivity() != null) {
                ((MainActivity) getActivity()).loadPastriesFragment();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
