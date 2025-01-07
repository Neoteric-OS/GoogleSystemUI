package com.google.zxing;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EncodeHintType {
    public static final /* synthetic */ EncodeHintType[] $VALUES;
    public static final EncodeHintType AZTEC_LAYERS = null;
    public static final EncodeHintType CHARACTER_SET;
    public static final EncodeHintType CODE128_COMPACT = null;
    public static final EncodeHintType DATA_MATRIX_COMPACT = null;
    public static final EncodeHintType DATA_MATRIX_SHAPE = null;
    public static final EncodeHintType ERROR_CORRECTION;
    public static final EncodeHintType FORCE_C40 = null;
    public static final EncodeHintType FORCE_CODE_SET = null;
    public static final EncodeHintType GS1_FORMAT;
    public static final EncodeHintType MARGIN;
    public static final EncodeHintType MAX_SIZE = null;
    public static final EncodeHintType MIN_SIZE = null;
    public static final EncodeHintType PDF417_AUTO_ECI = null;
    public static final EncodeHintType PDF417_COMPACT = null;
    public static final EncodeHintType PDF417_COMPACTION = null;
    public static final EncodeHintType PDF417_DIMENSIONS = null;
    public static final EncodeHintType QR_COMPACT;
    public static final EncodeHintType QR_MASK_PATTERN;
    public static final EncodeHintType QR_VERSION;

    static {
        EncodeHintType encodeHintType = new EncodeHintType("ERROR_CORRECTION", 0);
        ERROR_CORRECTION = encodeHintType;
        EncodeHintType encodeHintType2 = new EncodeHintType("CHARACTER_SET", 1);
        CHARACTER_SET = encodeHintType2;
        EncodeHintType encodeHintType3 = new EncodeHintType("DATA_MATRIX_SHAPE", 2);
        EncodeHintType encodeHintType4 = new EncodeHintType("DATA_MATRIX_COMPACT", 3);
        EncodeHintType encodeHintType5 = new EncodeHintType("MIN_SIZE", 4);
        EncodeHintType encodeHintType6 = new EncodeHintType("MAX_SIZE", 5);
        EncodeHintType encodeHintType7 = new EncodeHintType("MARGIN", 6);
        MARGIN = encodeHintType7;
        EncodeHintType encodeHintType8 = new EncodeHintType("PDF417_COMPACT", 7);
        EncodeHintType encodeHintType9 = new EncodeHintType("PDF417_COMPACTION", 8);
        EncodeHintType encodeHintType10 = new EncodeHintType("PDF417_DIMENSIONS", 9);
        EncodeHintType encodeHintType11 = new EncodeHintType("PDF417_AUTO_ECI", 10);
        EncodeHintType encodeHintType12 = new EncodeHintType("AZTEC_LAYERS", 11);
        EncodeHintType encodeHintType13 = new EncodeHintType("QR_VERSION", 12);
        QR_VERSION = encodeHintType13;
        EncodeHintType encodeHintType14 = new EncodeHintType("QR_MASK_PATTERN", 13);
        QR_MASK_PATTERN = encodeHintType14;
        EncodeHintType encodeHintType15 = new EncodeHintType("QR_COMPACT", 14);
        QR_COMPACT = encodeHintType15;
        EncodeHintType encodeHintType16 = new EncodeHintType("GS1_FORMAT", 15);
        GS1_FORMAT = encodeHintType16;
        $VALUES = new EncodeHintType[]{encodeHintType, encodeHintType2, encodeHintType3, encodeHintType4, encodeHintType5, encodeHintType6, encodeHintType7, encodeHintType8, encodeHintType9, encodeHintType10, encodeHintType11, encodeHintType12, encodeHintType13, encodeHintType14, encodeHintType15, encodeHintType16, new EncodeHintType("FORCE_CODE_SET", 16), new EncodeHintType("FORCE_C40", 17), new EncodeHintType("CODE128_COMPACT", 18)};
    }

    public static EncodeHintType valueOf(String str) {
        return (EncodeHintType) Enum.valueOf(EncodeHintType.class, str);
    }

    public static EncodeHintType[] values() {
        return (EncodeHintType[]) $VALUES.clone();
    }
}
