package com.zebra.jamesswinton.kiosklabelprinter.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zebra.jamesswinton.kiosklabelprinter.App;
import com.zebra.jamesswinton.kiosklabelprinter.Product;
import com.zebra.jamesswinton.kiosklabelprinter.R;
import com.zebra.jamesswinton.kiosklabelprinter.interfaces.OnProductAddToCartListener;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Debugging
    private static final String TAG = "ProductAdapter";

    // Constants


    // Private Variables
    private List<Product> mProducts;
    private OnProductAddToCartListener mOnProductAddToCartListener;

    // Public Variables


    public ProductAdapter(OnProductAddToCartListener onProductAddToCartListener) {
        this.mOnProductAddToCartListener = onProductAddToCartListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.adapter_product_constraint, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        // Cast ViewHolder to PopulatedViewHolder
        ProductViewHolder vh = (ProductViewHolder) viewHolder;

        // Get Recall
        Product product = mProducts.get(position);

        // Populate View
        Glide.with(vh.image)
                .load(product.getImage())
                .placeholder(product.getIcon())
                .into(vh.image);
        vh.image.setImageDrawable(product.getImage());
        vh.title.setText(product.getName());
        vh.price.setText(getPriceFormatted(product.getPrice()) + "€");

        // Hide Button if in basket
        if (App.mBasket.containsKey(product)) {
            // Hide Add Button
            vh.addToBasketButton.setVisibility(View.GONE);
            // Show remove Button
            vh.removeFromBasketButton.setVisibility(View.VISIBLE);
        }

        // Handle Add to Basket
        vh.addToBasketButton.setOnClickListener(view -> {
            // Update Basket
            mOnProductAddToCartListener.onProductAddedToCart(product, 1);
            // Show remove Button
            vh.removeFromBasketButton.setVisibility(View.VISIBLE);
            // Hide Add Button
            vh.addToBasketButton.setVisibility(View.GONE);
        });

        // Handle Remove
        vh.removeFromBasketButton.setOnClickListener(v -> {
            // Remvoe Product
            mOnProductAddToCartListener.onProductRemovedFromCart(product);
            // Hide remove Button
            vh.removeFromBasketButton.setVisibility(View.GONE);
            // Show Add Button
            vh.addToBasketButton.setVisibility(View.VISIBLE);
        });
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
        return mProducts.size();
    }

    /**
     * Utility Methods
     */

    public void loadProducts(List<Product> products) {
        this.mProducts = products;
        notifyDataSetChanged();
    }

    /**
     * View Holder
     */

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        // Views
        ImageView image;
        TextView title, price;
        Button addToBasketButton, removeFromBasketButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find Views
            image = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_title);
            price = itemView.findViewById(R.id.product_price);
            addToBasketButton = itemView.findViewById(R.id.add_to_basket_button);
            removeFromBasketButton = itemView.findViewById(R.id.remove_from_basket_button);
        }
    }
}
