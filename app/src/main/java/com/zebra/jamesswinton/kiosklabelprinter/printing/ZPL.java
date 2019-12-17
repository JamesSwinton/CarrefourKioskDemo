package com.zebra.jamesswinton.kiosklabelprinter.printing;

public class ZPL {

    // Content Place Holders
    public static final String PRICE = "%PRICE%";
    public static final String ITEM = "%ITEM%";
    public static final String QUANTITY = "%QUANTITY%";
    public static final String BARCODE = "%BARCODE%";

    // ZPL
    public static final String SINGLE_ITEM_ZPL =
            "CT~~CD,~CC^~CT~\n" +
            "^XA\n" +
            "~TA000\n" +
            "~JSN\n" +
            "^LT0\n" +
            "^MNW\n" +
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
            "^FO138,76^GFA,837,3600,24,:Z64:eJztlsut3CAUhvEwEptINBDJHaQFp5NbiinldhKKSAFeZsnSiiwTzhPwQ3M32Q0Ly/70A/95MIwx7/Ee/3E8P35f4UfO+e8FnwvP6YR9xhEu5ecJlnDeD3xknmPPBee1w4PyreNOef4St4p7Q8MNf63v/ZiXfP0an4WnG97pn41+aPgPMwlf7GezSpN/W3ewOVS9qxG73OhdzajPwQsvb7rB1HPdIPdcQhhwVSnXqKlz4Fr5pKX04LrRSzNOjX7HL81ZkoJt+KW5V74DX8XOQb9xdkCh6c/SE05kynPDWz0Z7fS0UeCwyDZyq3xsOQUYX3EtGCW28iDcH7i/0ct5P+ilYKZyX9eVfYLkrepHzZutvjmOXevV86T15bww/0N81DxyXqP2SV6Fqx0qABdyRT33lW15tYMNwQVL9P4EGkhT+Wq+AY/grdUv5jusvoJRLhiGFw3s4DY0RGld8NWAo3L+QMR6B1Mt8oyGahlgPkaLhoKWYUVUHhG8EIdnKlMjZG2BtSuHzwV4whPmpFwwPUL2N+ALcZhp8IRNxdigfOQvrFYYONIdZzvlZcUkWcXdEp3e8sCC7YY+gM/kWPWRT/BMEWKl4IHRE4etlNN5NlQsS51QNqHskh4yvvHPxIrVYL6YGQtWui5Rmwkf6TdhmKN0KLWN5578FRGwvhiSnqSrg/luHtxjDzoOzNtLy3X6Omy3fjOYs606yDjy2PLxhnviY764W7EfLu5K0vd330B6f3UnEj/fcQH50nPsHNg+9nxEg/aKB+Sh5x6Nn7mjgE7/VSxdDEf7hSB3pz9JHz/h+TjiC/Ie73Ez/gFBdoeI:F2DE\n" +
            "^PQ1,0,1,Y\n" +
            "^XZ";

    public static final String MULTI_ITEM_ZPL =
            "CT~~CD,~CC^~CT~\n" +
            "^XA\n" +
            "~TA000\n" +
            "~JSN\n" +
            "^LT0\n" +
            "^MNW\n" +
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
            "^FO8,91^GFA,409,1152,16,:Z64:eJzdkrtNBDEQhr1YYIngloQUt0HmKqjHRtcIneBSHCIiBwQOVp6b5+4JQQGwwWo+/Zp/XnbuH3z3n+UabwG+rvkdAN4ODIiwHZyI4XCI3/hn/chfCeevfBK+M36S+sF4euKxNsEFFuY4zO3MnKZVZ71HsO4KcUtQmTNU5qzMIf+62AtXZc+pQCZD9U4DvsKuD9LPqqP/IJ18tP7AAWmIqv1txME2tMIU1v49a1tQu5PjXPQYJ07HAguNDzWyfUlcG8dMdIJYI/eG42fWMbPQDOhCet4WWkDLPXBDeTpoxG0VxjzaTUafKutJjXZYQPUe+uPLsjnj4WnQZ28sL+chGM/96ppvd2ROuiaqVJirclYubj8kr8/ehUTr/pAkCsPYcyM3H8buiP7QdwGc7TiG:C430\n" +
            "^FT166,397^BQN,2,4\n" +
            "^FH\\^FDMA," + BARCODE + "^FS\n" +
            "^FO175,91^GFA,409,1152,16,:Z64:eJzdkrtNBDEQhr1YYIngloQUt0HmKqjHRtcIneBSHCIiBwQOVp6b5+4JQQGwwWo+/Zp/XnbuH3z3n+UabwG+rvkdAN4ODIiwHZyI4XCI3/hn/chfCeevfBK+M36S+sF4euKxNsEFFuY4zO3MnKZVZ71HsO4KcUtQmTNU5qzMIf+62AtXZc+pQCZD9U4DvsKuD9LPqqP/IJ18tP7AAWmIqv1txME2tMIU1v49a1tQu5PjXPQYJ07HAguNDzWyfUlcG8dMdIJYI/eG42fWMbPQDOhCet4WWkDLPXBDeTpoxG0VxjzaTUafKutJjXZYQPUe+uPLsjnj4WnQZ28sL+chGM/96ppvd2ROuiaqVJirclYubj8kr8/ehUTr/pAkCsPYcyM3H8buiP7QdwGc7TiG:C430\n" +
            "^FO343,91^GFA,409,1152,16,:Z64:eJzdkrtNBDEQhr1YYIngloQUt0HmKqjHRtcIneBSHCIiBwQOVp6b5+4JQQGwwWo+/Zp/XnbuH3z3n+UabwG+rvkdAN4ODIiwHZyI4XCI3/hn/chfCeevfBK+M36S+sF4euKxNsEFFuY4zO3MnKZVZ71HsO4KcUtQmTNU5qzMIf+62AtXZc+pQCZD9U4DvsKuD9LPqqP/IJ18tP7AAWmIqv1txME2tMIU1v49a1tQu5PjXPQYJ07HAguNDzWyfUlcG8dMdIJYI/eG42fWMbPQDOhCet4WWkDLPXBDeTpoxG0VxjzaTUafKutJjXZYQPUe+uPLsjnj4WnQZ28sL+chGM/96ppvd2ROuiaqVJirclYubj8kr8/ehUTr/pAkCsPYcyM3H8buiP7QdwGc7TiG:C430\n" +
            "^PQ1,0,1,Y\n" +
            "^XZ";
}
