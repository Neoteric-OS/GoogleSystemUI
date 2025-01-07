package com.google.zxing.qrcode.decoder;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public enum ErrorCorrectionLevel {
    L(1),
    /* JADX INFO: Fake field, exist only in values array */
    M(0),
    /* JADX INFO: Fake field, exist only in values array */
    Q(3),
    /* JADX INFO: Fake field, exist only in values array */
    H(2);

    private final int bits;

    ErrorCorrectionLevel(int i) {
        this.bits = i;
    }

    public final int getBits() {
        return this.bits;
    }
}
