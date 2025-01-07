package com.google.zxing.common;

import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BitMatrix implements Cloneable {
    public int[] bits;
    public int height;
    public int rowSize;
    public int width;

    public final Object clone() {
        int i = this.width;
        int i2 = this.height;
        int i3 = this.rowSize;
        int[] iArr = (int[]) this.bits.clone();
        BitMatrix bitMatrix = new BitMatrix();
        bitMatrix.width = i;
        bitMatrix.height = i2;
        bitMatrix.rowSize = i3;
        bitMatrix.bits = iArr;
        return bitMatrix;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof BitMatrix)) {
            return false;
        }
        BitMatrix bitMatrix = (BitMatrix) obj;
        return this.width == bitMatrix.width && this.height == bitMatrix.height && this.rowSize == bitMatrix.rowSize && Arrays.equals(this.bits, bitMatrix.bits);
    }

    public final boolean get(int i, int i2) {
        return ((this.bits[(i / 32) + (i2 * this.rowSize)] >>> (i & 31)) & 1) != 0;
    }

    public final int hashCode() {
        int i = this.width;
        return Arrays.hashCode(this.bits) + (((((((i * 31) + i) * 31) + this.height) * 31) + this.rowSize) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.width + 1) * this.height);
        for (int i = 0; i < this.height; i++) {
            for (int i2 = 0; i2 < this.width; i2++) {
                sb.append(get(i2, i) ? "X " : "  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
