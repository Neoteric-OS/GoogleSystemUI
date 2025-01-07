package com.google.zxing;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BarcodeFormat {
    public static final /* synthetic */ BarcodeFormat[] $VALUES;
    public static final BarcodeFormat AZTEC = null;
    public static final BarcodeFormat CODABAR = null;
    public static final BarcodeFormat CODE_128 = null;
    public static final BarcodeFormat CODE_39 = null;
    public static final BarcodeFormat CODE_93 = null;
    public static final BarcodeFormat DATA_MATRIX = null;
    public static final BarcodeFormat EAN_13 = null;
    public static final BarcodeFormat EAN_8 = null;
    public static final BarcodeFormat ITF = null;
    public static final BarcodeFormat PDF_417 = null;
    public static final BarcodeFormat QR_CODE;
    public static final BarcodeFormat UPC_A = null;
    public static final BarcodeFormat UPC_E = null;

    /* JADX INFO: Fake field, exist only in values array */
    BarcodeFormat EF0;

    static {
        BarcodeFormat barcodeFormat = new BarcodeFormat("AZTEC", 0);
        BarcodeFormat barcodeFormat2 = new BarcodeFormat("CODABAR", 1);
        BarcodeFormat barcodeFormat3 = new BarcodeFormat("CODE_39", 2);
        BarcodeFormat barcodeFormat4 = new BarcodeFormat("CODE_93", 3);
        BarcodeFormat barcodeFormat5 = new BarcodeFormat("CODE_128", 4);
        BarcodeFormat barcodeFormat6 = new BarcodeFormat("DATA_MATRIX", 5);
        BarcodeFormat barcodeFormat7 = new BarcodeFormat("EAN_8", 6);
        BarcodeFormat barcodeFormat8 = new BarcodeFormat("EAN_13", 7);
        BarcodeFormat barcodeFormat9 = new BarcodeFormat("ITF", 8);
        BarcodeFormat barcodeFormat10 = new BarcodeFormat("MAXICODE", 9);
        BarcodeFormat barcodeFormat11 = new BarcodeFormat("PDF_417", 10);
        BarcodeFormat barcodeFormat12 = new BarcodeFormat("QR_CODE", 11);
        QR_CODE = barcodeFormat12;
        $VALUES = new BarcodeFormat[]{barcodeFormat, barcodeFormat2, barcodeFormat3, barcodeFormat4, barcodeFormat5, barcodeFormat6, barcodeFormat7, barcodeFormat8, barcodeFormat9, barcodeFormat10, barcodeFormat11, barcodeFormat12, new BarcodeFormat("RSS_14", 12), new BarcodeFormat("RSS_EXPANDED", 13), new BarcodeFormat("UPC_A", 14), new BarcodeFormat("UPC_E", 15), new BarcodeFormat("UPC_EAN_EXTENSION", 16)};
    }

    public static BarcodeFormat valueOf(String str) {
        return (BarcodeFormat) Enum.valueOf(BarcodeFormat.class, str);
    }

    public static BarcodeFormat[] values() {
        return (BarcodeFormat[]) $VALUES.clone();
    }
}
