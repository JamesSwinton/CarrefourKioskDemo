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
        vh.image.setImageDrawable(product.getImage());
        vh.title.setText(product.getName());
        vh.price.setText(getPriceFormatted(product.getPrice()) + "€");

        // Handle Quantity
        vh.removeQuantity.setOnClickListener(view -> {
            int currentQuantity = Integer.parseInt(vh.quantity.getText().toString());

            // Update Quantity
            if (currentQuantity > 0) {
                 currentQuantity--;
            } else {
                currentQuantity = 0;
            }

            // Set Quantity
            vh.quantity.setText(currentQuantity);
        });

        vh.addQuantity.setOnClickListener(view -> {
            int currentQuantity = Integer.parseInt(vh.quantity.getText().toString());

            // Update Quantity
            currentQuantity++;

            // Set Quantity
            vh.quantity.setText(currentQuantity);
        });

        // Handle Add to Basket
        vh.addToBasketButton.setOnClickListener(view -> {
            // Get Quantity
            int quantity = Integer.parseInt(vh.quantity.getText().toString());

            // Update Basket
            mOnProductAddToCartListener.onProductAddedToCart(product, quantity);
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
        ConstraintLayout quantityLayout;
        ImageView addQuantity, removeQuantity;
        TextView quantity;
        Button addToBasketButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find Views
            image = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_title);
            price = itemView.findViewById(R.id.product_price);
            quantityLayout = itemView.findViewById(R.id.number_picker_layout_container);
            addQuantity = quantityLayout.findViewById(R.id.add);
            removeQuantity = quantityLayout.findViewById(R.id.remove);
            quantity = quantityLayout.findViewById(R.id.quantity);
            addToBasketButton = itemView.findViewById(R.id.add_to_basket_button);
        }
    }
}
