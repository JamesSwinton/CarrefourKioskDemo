package com.zebra.jamesswinton.kiosklabelprinter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zebra.jamesswinton.kiosklabelprinter.databinding.ActivityMainBinding;
import com.zebra.jamesswinton.kiosklabelprinter.fragments.BasketFragment;
import com.zebra.jamesswinton.kiosklabelprinter.fragments.BreadFragment;
import com.zebra.jamesswinton.kiosklabelprinter.fragments.MainMenuFragment;
import com.zebra.jamesswinton.kiosklabelprinter.fragments.PastriesFragment;
import com.zebra.jamesswinton.kiosklabelprinter.interfaces.OnProductAddToCartListener;
import com.zebra.jamesswinton.kiosklabelprinter.printing.PrintHandler;
import com.zebra.jamesswinton.kiosklabelprinter.printing.ZPL;
import com.zebra.jamesswinton.kiosklabelprinter.utilities.CustomDialog;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.HashMap;

import static com.zebra.jamesswinton.kiosklabelprinter.App.mBasket;

public class MainActivity extends AppCompatActivity implements OnProductAddToCartListener {

    // Debugging
    private static final String TAG = "MainActivity";

    // Constants
    private static final String BREAD_FRAGMENT = "bread";
    private static final String PASTRIES_FRAGMENT = "pastries";
    private static final String BASKET_FRAGMENT = "basket";

    private static final int PRINT_SUCCESS = 0;
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
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, new MainMenuFragment())
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
    }

    @Override
    public void onProductRemovedFromCart(@NonNull Product product) {
        if (mBasket.containsKey(product)) {
            // Remove Product
            mBasket.remove(product);

            // Update Counter
            updateBasketCounter();
        }
    }

    public void updateBasketCounter() {
        Integer basketQuantity = 0;
        for (Product basketProduct : mBasket.keySet()) {
            if (mBasket.get(basketProduct) != null) {
                basketQuantity = basketQuantity + mBasket.get(basketProduct);
            }
        } mBasketQuantityTextView.setText(String.valueOf(basketQuantity));
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

        // Set Click Listener to Show BasketFragment
        basketLayout.setOnClickListener(view ->
                mFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, new BasketFragment())
                .addToBackStack(BASKET_FRAGMENT)
                .commit()
        );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle Navigation Events
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
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
            runOnUiThread(() -> {
                if (resultCode == PRINT_SUCCESS) {
                    // Handle Success (Show success dialog, restart app on confirm)
                    CustomDialog.showCustomDialog(MainActivity.this, CustomDialog.DialogType.SUCCESS,
                            "Receipt Printed", "Successfully printed receipt. Please " +
                                    "check your printer for your label & then attach to your product.",
                            "OK", (dialog, which) -> restartKioskState(),
                            "", null);
                } else {
                    // Handle Failure
                    String errorMessage = resultData.getString(PRINT_ERROR_STRING);
                    CustomDialog.showCustomDialog(MainActivity.this, CustomDialog.DialogType.ERROR,
                            "Print Failed!", errorMessage == null || errorMessage.isEmpty() ? "" : errorMessage);

                    // TODO: Add re-try print to Error Dialog
                }
            });
        }
    };

    private void restartKioskState() {
        // Clear Current Basket
        mBasket.clear();

        // Update Basket counter
        updateBasketCounter();

        // Remove Fragments from BackStack
        for (Fragment fragment : mFragmentManager.getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }

        // Show Main Fragment
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, new MainMenuFragment())
                .commit();
    }

    /**
     * Print Job Utility Methods
     */

    public void printReceiptSingleItem(Product product) {
        // Convert ZPL to Byte[]
        byte[] templateBytes = ZPL.SINGLE_ITEM_ZPL.getBytes(StandardCharsets.UTF_8);

        // Set Variable Data
        HashMap<String, String> variableData = new HashMap<>();
        variableData.put(ZPL.ITEM, product.getName());
        variableData.put(ZPL.PRICE, getPriceFormatted(product.getPrice()));
        variableData.put(ZPL.QUANTITY, String.valueOf(mBasket.get(product)));
        variableData.put(ZPL.BARCODE, product.getEan().toString());
        variableData.put(ZPL.IMAGE_1, product.getSingleIconBase64());

        PrintHandler.sendPrintJobWithContent(this, templateBytes, variableData,
                printResultReceiver);
    }

    public void printReceiptMultipleItems(Product[] products, double basketTotal) {
        // Convert ZPL to Byte[]
        byte[] templateBytes = ZPL.MULTI_ITEM_ZPL.getBytes(StandardCharsets.UTF_8);

        // Set Variable Data
        HashMap<String, String> variableData = new HashMap<>();
        variableData.put(ZPL.PRICE, getPriceFormatted(basketTotal));
        variableData.put(ZPL.BARCODE, QueueBustingQrCodeGenerator
                .generatorQrCodeStringFromBasket(mBasket));
        variableData.put(ZPL.IMAGE_1, products[0].getMultipleIconBase64());
        variableData.put(ZPL.IMAGE_2, products[1].getMultipleIconBase64());
        if (products.length > 2) {
            variableData.put(ZPL.IMAGE_3, products[2].getMultipleIconBase64());
        }

        // Send Print Job
        PrintHandler.sendPrintJobWithContent(this, templateBytes, variableData,
                printResultReceiver);
    }

    private String getPriceFormatted(double realNumber) {
        return new DecimalFormat("##,##0.00€").format(realNumber);
    }

}
