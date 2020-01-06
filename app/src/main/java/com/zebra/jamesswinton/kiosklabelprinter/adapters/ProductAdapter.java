package com.zebra.jamesswinton.kiosklabelprinter.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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

import java.text.DecimalFormat;
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
        vh.price.setText(getPriceFormatted(product.getPrice()));

        // Get Quantity
        Integer qty = App.mBasket.get(product) == null ? 0 : App.mBasket.get(product);
        int mQuantity = qty == null ? 0 : qty;
        vh.quantity.setText(String.valueOf(mQuantity));

        // Enable / Disable Remove
        vh.reduceQuantity.setEnabled(mQuantity != 0);

        // Add / Remove Listeners
        vh.addQuantity.setOnClickListener(view -> {
            mOnProductAddToCartListener.onProductAddedToCart(product, 1);

            // Update Quantity
            vh.quantity.setText(String.valueOf(mQuantity + 1));

            // Set Button
            vh.reduceQuantity.setEnabled(true);

            // Update
            notifyItemChanged(position);
        });

        vh.reduceQuantity.setOnClickListener(view -> {
            mOnProductAddToCartListener.onProductRemovedFromCart(product);

            // Update Quantity
            vh.quantity.setText(String.valueOf(mQuantity - 1));

            // Set Button State
            vh.reduceQuantity.setEnabled(mQuantity - 1 != 0);

            // Update
            notifyItemChanged(position);
        });
    }

    private String getPriceFormatted(double realNumber) {
        return new DecimalFormat("##,##0.00€").format(realNumber);
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
        TextView quantity;
        ImageButton addQuantity, reduceQuantity;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find Views
            image = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_title);
            price = itemView.findViewById(R.id.product_price);
            quantity = itemView.findViewById(R.id.quantity);
            addQuantity = itemView.findViewById(R.id.add_quantity);
            reduceQuantity = itemView.findViewById(R.id.remove_quantity);
        }
    }
}
