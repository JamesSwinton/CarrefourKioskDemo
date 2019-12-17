package com.zebra.jamesswinton.kiosklabelprinter.printing;

public class ZPL {

    // Content Place Holders
    public static final String PRICE = "%PRICE%";
    public static final String ITEM = "%ITEM%";
    public static final String QUANTITY = "%QUANTITY%";
    public static final String BARCODE = "%BARCODE%";

    public static final String IMAGE_1 = "%IMAGE-1%";
    public static final String IMAGE_2 = "%IMAGE-2%";
    public static final String IMAGE_3 = "%IMAGE-3%";

    // ZPL
    public static final String SINGLE_ITEM_ZPL =
            "CT~~CD,~CC^~CT~\n" +
            "^XA\n" +
            "~TA000\n" +
            "~JSN\n" +
            "^LT0\n" +
            "^MNV\n" + // Change this back to ^MNW
            "^MTD\n" +
            "^PON\n" +
            "^PMN\n" +
            "^LH0,0\n" +
            "^JMA\n" +
            "^PR6,6\n" +
            "~SD15\n" +
            "^JUS\n" +
            "^LRN\n" +
            "^CI27\n" +
            "^PA0,1,1,0\n" +
            "^XZ\n" +
            "^XA\n" +
            "^MMT\n" +
            "^PW448\n" +
            "^LL406\n" +
            "^LS0\n" +
            "^FT93,286^A0N,51,53^FH\\^CI28^FD" + PRICE + "^FS^CI27\n" +
            "^FT60,56^A0N,51,53^FH\\^CI28^FD" + ITEM + QUANTITY + "^FS^CI27\n" +
            "^BY3,2,67^FT90,366^BEN,,Y,N\n" +
            "^FH\\^FD" + BARCODE + "^FS\n" +
            "^FO138,76^GFA,837,3600,24,:Z64:" + IMAGE_1 + "\n" +
            "^PQ1,0,1,Y\n" +
            "^XZ";

    public static final String MULTI_ITEM_ZPL =
            "CT~~CD,~CC^~CT~\n" +
            "^XA\n" +
            "~TA000\n" +
            "~JSN\n" +
            "^LT0\n" +
            "^MNV\n" + // Change this back to ^MNW
            "^MTD\n" +
            "^PON\n" +
            "^PMN\n" +
            "^LH0,0\n" +
            "^JMA\n" +
            "^PR4,4\n" +
            "~SD20\n" +
            "^JUS\n" +
            "^LRN\n" +
            "^CI27\n" +
            "^PA0,1,1,0\n" +
            "^XZ\n" +
            "^XA\n" +
            "^MMT\n" +
            "^PW448\n" +
            "^LL406\n" +
            "^LS0\n" +
            "^FT94,232^A0N,51,53^FH\\^CI28^FD" + PRICE + "^FS^CI27\n" +
            "^FT97,60^A0N,51,53^FH\\^CI28^FDDivers Vrac^FS^CI27\n" +
            "^FO8,91^GFA,409,1152,16,:Z64:" + IMAGE_1 + "\n" +
            "^FT166,397^BQN,2,4\n" +
            "^FH\\^FDMA," + BARCODE + "^FS\n" +
            "^FO175,91^GFA,409,1152,16,:Z64:" + IMAGE_2 + "\n" +
            "^FO343,91^GFA,409,1152,16,:Z64:" + IMAGE_3 + "\n" +
            "^PQ1,0,1,Y\n" +
            "^XZ";
}
