package com.zebra.jamesswinton.kiosklabelprinter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class QueueBustingQrCodeGenerator {

    // Debugging
    private static final String TAG = "QrCodeGenerator";

    // Constants
    private static final String BARCODE_DELIMITER = "X";
    private static final String END_DELIMITER = "XZ";
    private static final String EAN_ID = "F";

    // Private Variables


    // Public Variables


    public static String generatorQrCodeStringFromBasket(HashMap<Product, Integer> basket) {
        // Barcode Holder Array
        List<BigInteger> basketBarcodes = new ArrayList<>();

        // QR Code String Builder
        StringBuilder qrCodeStringBuilder = new StringBuilder();

        // Loop Basket
        for (Map.Entry entry : basket.entrySet()) {

            // Get Values
            Product product = (Product) entry.getKey();
            Integer quantity = (Integer) entry.getValue();

            // Loop On Quantity
            for (int i = 0; i < quantity; i++) {
                basketBarcodes.add(product.getEan());
            }
        }

        // Loop Barcodes
        for (BigInteger ean : basketBarcodes) {
            qrCodeStringBuilder.append(BARCODE_DELIMITER);
            qrCodeStringBuilder.append(EAN_ID);
            qrCodeStringBuilder.append(ean);
        }

        // Add End Delimiter
        qrCodeStringBuilder.append(END_DELIMITER);

        // Return String
        return qrCodeStringBuilder.toString();
    }

    public static Bitmap generateQrCodeBitmapFromBasket(HashMap<Product, Integer> basket, int width, int height) {
        // Get QR Code As String
        String qrCodeString = generatorQrCodeStringFromBasket(basket);

        try {
            // Convert String to QR Code BitMatrix
            BitMatrix result = new MultiFormatWriter().encode(qrCodeString, BarcodeFormat.QR_CODE, width, height, null);

            // Build QR Code from BitMatrix
            int w = result.getWidth();
            int h = result.getHeight();
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                int offset = y * w;
                for (int x = 0; x < w; x++) {
                    pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
                }
            }

            // Generate Bitmap
            Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, w, h);
            return bitmap;
        } catch (WriterException | IllegalArgumentException e) {
            Log.e(TAG, "Error Generating QR Code: " + e.getMessage());
            return null;
        }
    }

}
