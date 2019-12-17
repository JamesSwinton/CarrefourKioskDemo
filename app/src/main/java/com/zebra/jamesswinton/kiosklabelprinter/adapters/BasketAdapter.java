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
    private static final int POPULATED = 0;
    private static final int EMPTY = 1;

    // Private Variables
    private HashMap<Product, Integer> mBasket;
    private Product[] mProducts;
    private OnProductAddToCartListener mOnProductAddToCartListener;

    // Public Variables


    public BasketAdapter(OnProductAddToCartListener onProductAddToCartListener) {
        this.mOnProductAddToCartListener = onProductAddToCartListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case EMPTY:
                return new EmptyViewHolder(LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.adapter_empty_basket, parent, false));
            case POPULATED:
            default:
                return new BasketViewHolder(LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.adapter_basket, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof BasketViewHolder) {
            // Cast ViewHolder to PopulatedViewHolder
            BasketViewHolder vh = (BasketViewHolder) viewHolder;

            // Get Recall
            Product product = mProducts[position];

            // Populate View
            vh.title.setText(product.getName());
            vh.price.setText(getPriceFormatted(product.getPrice()) + "€");
            vh.quantity.setText("x" + mBasket.get(product));
            vh.deleteProduct.setOnClickListener(v -> {
                mOnProductAddToCartListener.onProductRemovedFromCart(product);
            });
        }
    }

    private String getPriceFormatted(double realNumber) {
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
        nf.setMaximumFractionDigits(10);
        return String.format("%s", nf.format(realNumber));
    }

    @Override
    public int getItemViewType(int position) {
        return mBasket == null || mBasket.isEmpty() ? EMPTY : POPULATED;
    }

    @Override
    public int getItemCount() {
        return mBasket.size() == 0 || mBasket.isEmpty() ? 1 : mBasket.size();
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
        private TextView quantity;
        private TextView price, title;
        private ImageView deleteProduct;

        public BasketViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.product_image);
            this.title = itemView.findViewById(R.id.product_title);
            this.price = itemView.findViewById(R.id.product_price);
            this.quantity = itemView.findViewById(R.id.product_quantity);
            this.deleteProduct = itemView.findViewById(R.id.delete);
        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
