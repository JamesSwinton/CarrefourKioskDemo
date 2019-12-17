package com.zebra.jamesswinton.kiosklabelprinter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zebra.jamesswinton.kiosklabelprinter.Product;
import com.zebra.jamesswinton.kiosklabelprinter.R;
import com.zebra.jamesswinton.kiosklabelprinter.interfaces.OnProductAddToCartListener;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class BasketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Debugging
    private static final String TAG = "ProductAdapter";

    // Constants


    // Private Variables
    private HashMap<Product, Integer> mBasket;
    private Product[] mProducts;
    private OnProductAddToCartListener mOnProductAddToCartListener;

    // Public Variables


    public BasketAdapter() {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BasketViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.adapter_basket, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        // Cast ViewHolder to PopulatedViewHolder
        BasketViewHolder vh = (BasketViewHolder) viewHolder;

        // Get Recall
        Product product = mProducts[position];

        // Populate View
        vh.title.setText(product.getName());
        vh.image.setImageDrawable(product.getImage());
        vh.price.setText("€" + getPriceFormatted(product.getPrice()));
    }

    private String getPriceFormatted(double realNumber) {
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
        nf.setMaximumFractionDigits(10);
        return String.format("%s", nf.format(realNumber));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mBasket.size();
    }

    /**
     * Utility Methods
     */

    public void loadBasket(HashMap<Product, Integer> basket) {
        this.mBasket = basket;
        this.mProducts = mBasket.keySet().toArray(new Product[mBasket.size()]);
        notifyDataSetChanged();
    }

    /**
     * View Holder
     */

    public class BasketViewHolder extends RecyclerView.ViewHolder {

        // Views
        private ImageView image;
        private TextView price, title;

        public BasketViewHolder(@NonNull View itemView) {
            super(itemView);

            this.image = itemView.findViewById(R.id.product_image);
            this.title = itemView.findViewById(R.id.product_title);
            this.price = itemView.findViewById(R.id.product_price);

        }
    }
}
