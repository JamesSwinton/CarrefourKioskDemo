package com.zebra.jamesswinton.kiosklabelprinter.printing;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.util.Log;

import com.zebra.jamesswinton.kiosklabelprinter.utilities.CustomDialog;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class PrintHandler {

    // Debugging
    private static final String TAG = "PrintHandler";

    // Constants


    // Private Variables
    private static final String PRINT_CONNECT_PACKAGE = "com.zebra.printconnect";
    private static final String TEMPLATE_DATA = "com.zebra.printconnect.PrintService.TEMPLATE_DATA";
    private static final String VARIABLE_DATA = "com.zebra.printconnect.PrintService.VARIABLE_DATA";
    private static final String RESULT_RECEIVER = "com.zebra.printconnect.PrintService.RESULT_RECEIVER";
    private static final String PRINT_WITH_CONTENT_SERVICE = "com.zebra.printconnect.print.TemplatePrintWithContentService";

    // Public Variables


    // Sends Print job via intent to PrintConnect service - Uses template & variable data
    public static void sendPrintJobWithContent(Context context, byte[] templateBytes,
                                               HashMap<String, String> variableData,
                                               ResultReceiver resultReceiver) {
        Intent sendPrintJob = new Intent();
        sendPrintJob.setComponent(new ComponentName(PRINT_CONNECT_PACKAGE, PRINT_WITH_CONTENT_SERVICE));
        sendPrintJob.putExtra(TEMPLATE_DATA, templateBytes); // Template ZPL as UTF-8 encoded byte array
        sendPrintJob.putExtra(VARIABLE_DATA, variableData);
        sendPrintJob.putExtra(RESULT_RECEIVER, buildSafeReceiver(resultReceiver));
        context.startService(sendPrintJob);
    }

    // This method makes your ResultReceiver safe for inter-process communication
    private static ResultReceiver buildSafeReceiver(ResultReceiver actualReceiver) {
        Parcel parcel = Parcel.obtain();
        actualReceiver.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        ResultReceiver receiverForSending = ResultReceiver.CREATOR.createFromParcel(parcel);
        parcel.recycle();
        return receiverForSending;
    }

    public static void testPrint(Context cx) {
        // Convert ZPL to Byte[]
        byte[] templateBytes = ZPL.SINGLE_ITEM_ZPL.getBytes(StandardCharsets.UTF_8);

        // Set Variable Data
        HashMap<String, String> variableData = new HashMap<>();
        variableData.put(ZPL.ITEM, "TEST");
        variableData.put(ZPL.PRICE, "€0.00");
        variableData.put(ZPL.QUANTITY, "X2");

        sendPrintJobWithContent(cx, templateBytes, variableData, buildSafeReceiver(new ResultReceiver(null) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                super.onReceiveResult(resultCode, resultData);

                Log.i(TAG, "Result Code: " + resultCode);

                String errorMessage = resultData.getString("com.zebra.printconnect.PrintService.ERROR_MESSAGE");
                CustomDialog.showCustomDialog(cx, CustomDialog.DialogType.ERROR,
                        "Print Failed!", errorMessage);
            }
        }));
    }
}
