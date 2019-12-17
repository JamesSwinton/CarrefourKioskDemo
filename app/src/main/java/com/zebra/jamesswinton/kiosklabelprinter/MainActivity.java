package com.zebra.jamesswinton.kiosklabelprinter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zebra.jamesswinton.kiosklabelprinter.databinding.ActivityMainBinding;
import com.zebra.jamesswinton.kiosklabelprinter.fragments.BasketFragment;
import com.zebra.jamesswinton.kiosklabelprinter.fragments.BreadFragment;
import com.zebra.jamesswinton.kiosklabelprinter.fragments.MainMenuFragment;
import com.zebra.jamesswinton.kiosklabelprinter.fragments.PastriesFragment;
import com.zebra.jamesswinton.kiosklabelprinter.interfaces.OnProductAddToCartListener;
import com.zebra.jamesswinton.kiosklabelprinter.utilities.CustomDialog;

import static com.zebra.jamesswinton.kiosklabelprinter.App.mBasket;

public class MainActivity extends AppCompatActivity implements OnProductAddToCartListener {

    // Debugging
    private static final String TAG = "MainActivity";

    // Constants
    private static final String BREAD_FRAGMENT = "bread";
    private static final String PASTRIES_FRAGMENT = "pastries";
    private static final String BASKET_FRAGMENT = "basket";

    private static final int PRINT_FAILURE = 0;
    private static final String PRINT_ERROR_STRING = "com.zebra.printconnect.PrintService.ERROR_MESSAGE";

    // Private Variables
    private ActivityMainBinding mDataBinding;
    private FragmentManager mFragmentManager;

    private TextView mBasketQuantityTextView;

    // Public Variables


    /**
     * LifeCycle Methods
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Init FragmentManager
        mFragmentManager = getSupportFragmentManager();

        // Configure Toolbar
        setSupportActionBar(mDataBinding.toolbarLayout.toolbar);

        // Show Main Fragment
        MainMenuFragment mainFragment = new MainMenuFragment();
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, mainFragment)
                .commit();
    }

    /**
     * Basket Methods
     */

    @Override
    public void onProductAddedToCart(@NonNull Product product, @NonNull Integer quantity) {
        // Check if Quantity needs to be Added
        Integer totalQuantity = quantity;
        if (mBasket.containsKey(product)) {
            totalQuantity = totalQuantity + mBasket.get(product);
        }

        // Update Basket
        mBasket.put(product, totalQuantity);

        // Update Counter
        Integer basketQuantity = 0;
        for (Product basketProduct : mBasket.keySet()) {
            if (mBasket.get(basketProduct) != null) {
                basketQuantity = basketQuantity + mBasket.get(basketProduct);
            }
        } mBasketQuantityTextView.setText(String.valueOf(basketQuantity));

        // Set Visibility if Required
        if (mBasketQuantityTextView.getVisibility() == View.GONE) {
            mBasketQuantityTextView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Fragment Navigation Methods
     */

    public void loadBreadFragment() {
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, new BreadFragment())
                .addToBackStack(BREAD_FRAGMENT)
                .commit();
    }

    public void loadPastriesFragment() {
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, new PastriesFragment())
                .addToBackStack(PASTRIES_FRAGMENT)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Configuration Methods
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate Menu
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        // Get Basket Badge
        RelativeLayout basketLayout = (RelativeLayout) menu.findItem(R.id.basket).getActionView();
        mBasketQuantityTextView = basketLayout.findViewById(R.id.toolbar_basket_counter);

        // Hide Basket Badge on Start-up
        if (mBasket == null || mBasket.isEmpty()) {
            mBasketQuantityTextView.setVisibility(View.GONE);
        }

        // Set Click Listener to Show BasketFragment
        basketLayout.setOnClickListener(view ->
                mFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, new BasketFragment(mBasket))
                .addToBackStack(BASKET_FRAGMENT)
                .commit()
        );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle Navigation Events
        switch(item.getItemId()) {
            case R.id.basket:
                Log.i(TAG, "Basket Clicked");
                break;
        } return true;
    }

    /**
     * ZPL Result Receiver
     */

    public ResultReceiver printResultReceiver = new ResultReceiver(null) {
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            Log.i(TAG, "Print Result Code: " + resultCode);

            // Handle Print Result
            if (resultCode != PRINT_FAILURE) {
                // Handle Success
                CustomDialog.showCustomDialog(MainActivity.this, CustomDialog.DialogType.SUCCESS,
                        "Receipt Printed", "Successfully printed receipt. Please " +
                                "check your printer for your label & then attach to your product.");
            } else {
                // Handle Failure
                String errorMessage = resultData.getString(PRINT_ERROR_STRING);
                CustomDialog.showCustomDialog(MainActivity.this, CustomDialog.DialogType.ERROR,
                        "Print Failed!", errorMessage);

                // TODO: Add re-try print to Error Dialog
            }
        }
    };

    /**
     * Print Job Utility Methods
     */


}
