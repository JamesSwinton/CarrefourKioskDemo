package com.zebra.jamesswinton.kiosklabelprinter.interfaces;

import androidx.annotation.NonNull;

import com.zebra.jamesswinton.kiosklabelprinter.Product;

public interface OnProductAddToCartListener {
    void onProductAddedToCart(@NonNull Product product, @NonNull Integer quantity);
}
