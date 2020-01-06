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

    public static final String IMAGE_1_QUANTITY = "%IMAGE-1-QUANTITY%";
    public static final String IMAGE_2_QUANTITY = "%IMAGE-2-QUANTITY%";
    public static final String IMAGE_3_QUANTITY = "%IMAGE-3-QUANTITY%";

    public static final String SINGLE_BREAD_IMAGE_64 = "^GFA,773,6280,40,:Z64:eJztlj9OwzAUxpMCAsFQNkbvLBwhDCBWFsSEOEossSBxiY5VF47QHIGBA3SsWMgAUoSMH/4TP7+ksRsQo78hteJPv3zv2U2cZUlJSUlJSVHlWx2Pi7m6TkEueNR3A0JdGQC8RlyTJYBUv4XywVfYd6LnWx7Adch2Zqa5zqclA7a8NNOV80HAt2NnV+jjw759O1vjKMBrKQ2OVlGewFEVzSdpgsF6wcWK+CR3PFXmESY9nHdteyqM5+U+6UfXV6jiPM8nLdRzaLRShXE8SmZw3mtdgzxKLnRIL6Z9jkLJDL6pz2w3R6HkAmDW7ZwI8GgXp6Y4RxE4WusZsg3vTXE5+jyZAdk2E1uco0iSdApk29jlRIofNYaHAZ9t6InzEbJJzmk8qJDHfVIGJGDbLEeBmecZ38x3T8dAnidb34vvng6NPE/mU1sOKZfw6h5PuL3X4/kRL2xMo7ItboBXMfAFu+IGeCv7rBUpl/BEn1dpHzYLKbLPa0i5AAO82vJq0j6AJ+Q9YFLLkx0fgqHPE6R9AEiBQR5zN6/Qh3WI0v5q3xI29N6/wf1yRFWN9P2GN8JmFjjx/s4bu24V3X8Rcbr/Isr+lyc6/6OwJP2fR1SP9DX0fRVRlY0r2L6g2VZf5/0cVvsBybf53AdpOSpee+wLS7jv0WHct3a+eMXkgBBdEnpOjSTsnDciCdecGvfKgK1/jD5dDtresg3dbiI/jzdt2cHiueuSi8cBm9Hu5Y3Snb5chDxJSUlJSUlaP8K9K1M=:C954\n";
    public static final String SINGLE_MUFFIN_IMAGE_64 = "^GFA,1189,6360,40,:Z64:eJzVmD1u2zAUgCWngYtkcLeMAjo2g29QeegBugQdfYyO4lj0FEamwouPYB2hBXoAjYa7eEhQwXD8av5JFN8j+YwGDcrBiJTPX94PSTHKsv9sXK0Atl+SWD6H0/iZwkZrUGOf4G7AjFk8OIvBUcS4ouOgjkXXY9EIxw4HEc7FoA1i+YA7BLnpgINFiCuG3C6AjYZYsIRjjwtlXPpcQ3OVzwUC9DE4ktgl4ugAJ5gjEy4wV1PcGnNki1G6dIsvMEYmTHFUwkS6ZMJEumTCJcU1TI7oMIVRBSQ5XECyLEQBc5LzfFckJMewgPMgN8CmQWzoq8LczMHIzprRsKIbNNjfBkI+tA0EuEi2ABteeM6EiYbn7NJ0Y7vRFTBWPXA2wSLOdcIywdnKVAnuwXAJzE6ZRLpgW0yvCxxgtLtq7DjlA1vBIskBq3xgEmb4aqZvw2kHmIQZvpbpO7Daawr4nD5mfMD0ATO+F/PVTF/N66+Kj8Od4yuYvuhu6vjS61fXL71+z/Ol9xc9/9L7lXkIM32MBh+YG0z7zBtgc9YG7SR8LElO+A+kHXHkhe7M0Qe4IYtpz0S580VK2D2CnS/af9/c0VjuxvniZaV/fsorL7wsuzY3FvJiZUIV3V8RHThXN04Pxtnp4k7aPvZlcI6U10Y3UqeBd8vlvTMxFz2nIvwuW+3cMxPpyb31egVbIU/4+z4YsxI3GRoyxXp4OdS5AhPO7WnS3Z4uvxGcSvvRuOQkzu8IylYOtsvlyrRBCIzZTtjxgJHs/fLtEvV1gbni92fcf+JsT68RnCu95p4+ZK84PkDn++AaPjB9LdPn5RzcBD2fWXKPc/n5o5KfnyK+jYpTrGWiXyO+WsWpVsZBT2fPZ87QjfKqHbHVq7qhfRedb6d99ZDLXJ8I+8zaanLHB3/v2zm+POJr1S+F5FpdUsH0+Vxpqt/Vr6F9helmZX219mURn0j6Do7v8hwf+vf3BvnG+oY39AI5Zl396gnpG5/pK239hLqD3rFZX2F9WdSnUhW9D70+MAvY8U10g7xhFlzW+6akL0e+IuZTUcn66Ujxw8P6JkZ1DPhGjk8GqSuJX7+Yp63KW6p0JYnXOW580qc2GeJ1jvbVqrVzKa1ivr382MqPX+p6EfD5QyBuzeRKkkNYgMO+4oV85DGfev33hhoE96/HHyG7Leg=:FB92\n";
    public static final String SINGLE_COOKIE_IMAGE_64 = "^GFA,1289,5760,36,:Z64:eJylWD1u2zAUVlIEBpqhU9DRQNcOPYJ8iAYZfYrO4pxeQshUpEOOIB4hN6hGjxpcgEhlsZL59/5IGS2XyMynz9/7+Pjox6oC4/FQrY79uAr5Yq1agVw165jOWtuXITczxOoypl4wZlWNtUMR87JA7KRKmDONtSXIrYPYtoD56DGveci1h5QCuwmYworVAVMQ1ERMVtBthDiHlIDZJMx5yXZFOTNRe/3yVpQzj98Pkt23lg4uaMMwZTk5nuYCHgbheX3DMZpiPlzAs72Ap+MYtvF5WGx/vOMQlo7cZZ6OQlhsnwlhMaNrCUN4hLCoQdcShBh0JWKwQVJYc2Cr9tDAxLDIym9lDBIkQ7CgJgNatccih2R75nFKGCl73EibfgNe3GPQ/d1XarNR3xmXIvZoIUZNbFaC5z3GjJLnA7Z5knLggG02Ug4QHiOZddZz1cBXmOkDtlnkMXi5/o9ng15hcY1kuSR/JswzShjCM4l7ROEVEnOb7K6DEnK7xzxjPJxPv+LkQBT8CQ9Pn+KcyezSIR3358BqATMnzfv4QYm79ITSSos8byhek6/x0f9RrBrn3EtHbCvxaJxXR4lHkbLUep4HgKFlaXB/pscCj2l8KEAX5RlDKHWc8oUwTXgeDdbNH11gIvCkKX90dQmT5dn/i55iXEf/bsqXrD9Fn0fLR4vrQOSBg6x7WC80DN6/Is+IbbZDzTET2b9GwFhUJmWeZX/BInPYCphThU4gwPP29BIf3dNP96lPPFjE8r7/rOMXL2nTEVmUh5fVkFSJp2flufUmtfFVstiARwUel8U1xITkVIFnJJsB8EQJ7jBHNdz4uGKeuAMPBXb0/kQew3mS0jxPUhqDqJhB8RsayCP9iBmiJYcSz9Y96Qt48nr6GEo+Lh0tyfuj4qsG8OxQw5EkuPX6sjxOaP2nFApYd3h8+X+kr/UJfcD9Ici9XXzWuGqA3Ev5rFAVG0DupX2BE1sDT+L+mjBPD73Vvg8bMQ9eo+cuBdKhsNhaYx5f9QlmQnO+gdoTEOLxP+trglHwx7nLK5Y0O6jRQVi3jMqhqsTABrDvYz9HA1PJntgXbgnoNeVh7FbkzgmGle2clhEghRYDND1NDgOapy6HAT1PVpBOmKwglTA5QahrzghCTWpGkIYYWRDuUWVB5DJAFEQuFYQrDtbDS90luwTacgy7meJXQJfc3RwZxJVCOFqOIYe7RBMuEHPmeCIEydzbIUWCmjPRfkXNMpJH048cproLmF0WUlWfHeS5AJk13T98u6eTfwEvjOgy:493C\n";

    public static final String BREAD_IMAGE_64 = "^GFA,373,1456,16,:Z64:eJztkjFOxDAQRc1SWNrGJR17AA4RjkBHyVGSFQV0XANxCYI4AFcIokGi8VYbLcGf+eOs7aSh2IJmvzSSn57tjJwx5ph/yMlVAQu0Dp+Zz9Ba/GQGulOgSWcBL9zuWda9VFf4kpej94vI5+IH3mEjhzUQuMd18fToB+vJDqMf3I68IuseG8jPaIRvZY/TFmu8RB8sW2QjyXfaHD3LwvN6vMv6VeoBvTEW2CQ/qCd/qA/8vDLP3AHGVMC3rL+k3vhGVfSbyMmzQF+jTGMmKA3XMz7Uz78352rGqwmbqQ/6PjnxfXJ6fd8cr8OT0+n/K6+fXLDTEbtJfKnzcZH0fr6uSds8wcsnyeP9n0N/zOH5Bd1x3s8=:794A";
    public static final String MUFFIN_IMAGE_64 = "^GFA,421,888,12,:Z64:eJyNk7FOwzAQhhMqVAmGPIJ5A1Y29yHYeZLK2QoTr8DchUdIpA6sfQNcsbARNisK/rHvLrZFBMJSpU9fbN/57lpVf6yz8SnxHYYZLwG0wipwL6wDO8baBJ6YV0A6sCbmAw2xTddAImhivsgQ80WE8D/9g/gQoN4yI+hrwRjYIHtkXxfcJLaSJCekEvs2MyRJPmwy9xlhCz8U3v3Du998yUUOQ8GuyHPI74LN70Ifm9KJj3WQb7E+Pv4Qi6JwrM6/NiYWeoX2proKZaXG1Ftq8ob7+H7/3O5bnhK8cnu56xhpt0S9zZ76OPuJ29t5Pc6+mdRgxD967eY9C+94HNbRu6XfBc+ssfM6cdj/yfPWRX+a/cukTuw1Dl6/MSscJp38R6yOzWPLDxZe+Is9rfxPovUNDjgcDw==:CF59";
    public static final String COOKIE_IMAGE_64 = "^GFA,465,912,12,:Z64:eJxVkztOxDAURT24iARFSsqwA5Ywy6BkI0j2iI5VULKLCUKsg5RopknBSFEUfHk/f3AxPrrz/D7xtXPOXUdX1mNFj6rfYix8xJRxB8w1HEsNx1bDgfimHIgvT8rglXIa5BN9o7e8b3gQRk0DbTUoj40+lfTaqjfmVjtjGHOnON0xb95OUAuLtxMD/5i+Lyz6rLX/68RbHo04eRuBQ6nGA1fWBlLkEYLF0j6i5piC1aQ6P8BZpqK+UsB7ZlrPhQMOwjrfudFLHjT50dS1lV4g+TFT7BeVSRT32msZZv2mB2zEUfgDaeDrYf5kPeU7WAa+WiveN3qnzP/MzDusXGLykn/m9kfieCMSJqozdrLRFQPrUfWouUU3n3x7sYoMynk281K6l5vXDxPEEdkQ7JRsFHFQ5lgNt1VTqte7Gu6uarh18euqi5fyYFCeEgWt5SWFi+x/m+cz4g==:72BC";

    // ZPL
    public static final String SINGLE_ITEM_ZPL =
            "CT~~CD,~CC^~CT~\n" +
            "^XA\n" +
            "~TA000\n" +
            "~JSN\n" +
            "^LT0\n" +
            "^MNV\n" + // TODO: Change this back to ^MNW
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
            "^FO138,76" + IMAGE_1 + "\n" +
            "^PQ1,0,1,Y\n" +
            "^XZ";

    public static final String MULTI_ITEM_ZPL_OLD =
            "CT~~CD,~CC^~CT~\n" +
            "^XA\n" +
            "~TA000\n" +
            "~JSN\n" +
            "^LT0\n" +
            "^MNV\n" + // TODO: Change this back to ^MNW
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
            "^FT97,232^A0N,51,53^FH\\^CI28^FD" + PRICE + "^FS^CI27\n" +
            "^FT97,60^A0N,51,53^FH\\^CI28^FDDivers Vrac^FS^CI27\n" +
            "^FO8,91" + IMAGE_1 + "\n" +
            "^FT166,397^BQN,2,4\n" +
            "^FH\\^FDMA," + BARCODE + "^FS\n" +
            "^FO175,91" + IMAGE_2 + "\n" +
            "^FO343,91" + IMAGE_3 + "\n" +
            "^PQ1,0,1,Y\n" +
            "^XZ";

    public static final String MULTI_ITEM_ZPL =
            "CT~~CD,~CC^~CT~\n" +
            "^XA\n" +
            "~TA000\n" +
            "~JSN\n" +
            "^LT0\n" +
            "^MNV\n" +
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
            "^FT97,246^A0N,51,53^FH\\^CI28^FD" + PRICE + "^FS^CI27\n" +
            "^FT97,60^A0N,51,53^FH\\^CI28^FDDivers Vrac^FS^CI27\n" +
            "^FO26,106" + IMAGE_1 + "\n" +
            "^FO338,105" + IMAGE_3 + "\n" +
            "^FT166,401^BQN,2,2\n" +
            "^FH\\^FDMA," + BARCODE + "^FS\n" +
            "^FO167,97" + IMAGE_2 + "\n" +
            "^FT208,91^AAN,27,15^FH\\^FD" + IMAGE_1_QUANTITY + "^FS\n" +
            "^FT369,91^AAN,27,15^FH\\^FD" + IMAGE_3_QUANTITY + "^FS\n" +
            "^FT56,91^AAN,27,15^FH\\^FD" + IMAGE_2_QUANTITY + "^FS\n" +
            "^PQ1,0,1,Y\n" +
            "^XZ";
}
