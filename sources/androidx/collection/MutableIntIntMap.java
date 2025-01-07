package androidx.collection;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import java.util.Arrays;
import java.util.NoSuchElementException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutableIntIntMap {
    public int _capacity;
    public int _size;
    public int growthLimit;
    public int[] keys;
    public long[] metadata = ScatterMapKt.EmptyGroup;
    public int[] values;

    public MutableIntIntMap() {
        int[] iArr = IntSetKt.EmptyIntArray;
        this.keys = iArr;
        this.values = iArr;
        initializeStorage(ScatterMapKt.unloadedCapacity(6));
    }

    public final void clear() {
        this._size = 0;
        long[] jArr = this.metadata;
        if (jArr != ScatterMapKt.EmptyGroup) {
            Arrays.fill(jArr, 0, jArr.length, -9187201950435737472L);
            long[] jArr2 = this.metadata;
            int i = this._capacity;
            int i2 = i >> 3;
            long j = 255 << ((i & 7) << 3);
            jArr2[i2] = (jArr2[i2] & (~j)) | j;
        }
        this.growthLimit = ScatterMapKt.loadedCapacity(this._capacity) - this._size;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MutableIntIntMap)) {
            return false;
        }
        MutableIntIntMap mutableIntIntMap = (MutableIntIntMap) obj;
        if (mutableIntIntMap._size != this._size) {
            return false;
        }
        int[] iArr = this.keys;
        int[] iArr2 = this.values;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            int i4 = (i << 3) + i3;
                            int i5 = iArr[i4];
                            int i6 = iArr2[i4];
                            int findKeyIndex = mutableIntIntMap.findKeyIndex(i5);
                            if (findKeyIndex < 0) {
                                throw new NoSuchElementException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i5, "Cannot find value for key "));
                            }
                            if (i6 != mutableIntIntMap.values[findKeyIndex]) {
                                return false;
                            }
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        break;
                    }
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return true;
    }

    public final int findFirstAvailableSlot(int i) {
        int i2 = this._capacity;
        int i3 = i & i2;
        int i4 = 0;
        while (true) {
            long[] jArr = this.metadata;
            int i5 = i3 >> 3;
            int i6 = (i3 & 7) << 3;
            long j = ((jArr[i5 + 1] << (64 - i6)) & ((-i6) >> 63)) | (jArr[i5] >>> i6);
            long j2 = j & ((~j) << 7) & (-9187201950435737472L);
            if (j2 != 0) {
                return (i3 + (Long.numberOfTrailingZeros(j2) >> 3)) & i2;
            }
            i4 += 8;
            i3 = (i3 + i4) & i2;
        }
    }

    public final int findKeyIndex(int i) {
        int hashCode = Integer.hashCode(i) * (-862048943);
        int i2 = hashCode ^ (hashCode << 16);
        int i3 = i2 & 127;
        int i4 = this._capacity;
        int i5 = (i2 >>> 7) & i4;
        int i6 = 0;
        while (true) {
            long[] jArr = this.metadata;
            int i7 = i5 >> 3;
            int i8 = (i5 & 7) << 3;
            long j = ((jArr[i7 + 1] << (64 - i8)) & ((-i8) >> 63)) | (jArr[i7] >>> i8);
            long j2 = (i3 * 72340172838076673L) ^ j;
            for (long j3 = (~j2) & (j2 - 72340172838076673L) & (-9187201950435737472L); j3 != 0; j3 &= j3 - 1) {
                int numberOfTrailingZeros = ((Long.numberOfTrailingZeros(j3) >> 3) + i5) & i4;
                if (this.keys[numberOfTrailingZeros] == i) {
                    return numberOfTrailingZeros;
                }
            }
            if ((j & ((~j) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i6 += 8;
            i5 = (i5 + i6) & i4;
        }
    }

    public final int getOrDefault(int i) {
        int findKeyIndex = findKeyIndex(i);
        if (findKeyIndex >= 0) {
            return this.values[findKeyIndex];
        }
        return -1;
    }

    public final int hashCode() {
        int[] iArr = this.keys;
        int[] iArr2 = this.values;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        int i = 0;
        if (length >= 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                long j = jArr[i2];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i4 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i5 = 0; i5 < i4; i5++) {
                        if ((255 & j) < 128) {
                            int i6 = (i2 << 3) + i5;
                            int i7 = iArr[i6];
                            i3 += Integer.hashCode(iArr2[i6]) ^ Integer.hashCode(i7);
                        }
                        j >>= 8;
                    }
                    if (i4 != 8) {
                        return i3;
                    }
                }
                if (i2 == length) {
                    i = i3;
                    break;
                }
                i2++;
            }
        }
        return i;
    }

    public final void initializeStorage(int i) {
        long[] jArr;
        int max = i > 0 ? Math.max(7, ScatterMapKt.normalizeCapacity(i)) : 0;
        this._capacity = max;
        if (max == 0) {
            jArr = ScatterMapKt.EmptyGroup;
        } else {
            int i2 = ((max + 15) & (-8)) >> 3;
            long[] jArr2 = new long[i2];
            Arrays.fill(jArr2, 0, i2, -9187201950435737472L);
            jArr = jArr2;
        }
        this.metadata = jArr;
        int i3 = max >> 3;
        long j = 255 << ((max & 7) << 3);
        jArr[i3] = (jArr[i3] & (~j)) | j;
        this.growthLimit = ScatterMapKt.loadedCapacity(this._capacity) - this._size;
        this.keys = new int[max];
        this.values = new int[max];
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0071, code lost:
    
        if (((((~r9) << 6) & r9) & (-9187201950435737472L)) == 0) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0073, code lost:
    
        r2 = r0.findFirstAvailableSlot(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x007c, code lost:
    
        if (r0.growthLimit != 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0090, code lost:
    
        if (((r0.metadata[r2 >> 3] >> ((r2 & 7) << 3)) & 255) != 254) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0096, code lost:
    
        r2 = r0._capacity;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0098, code lost:
    
        if (r2 <= 8) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x009a, code lost:
    
        r20 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00ac, code lost:
    
        if (java.lang.Long.compareUnsigned(r0._size * 32, r2 * 25) > 0) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ae, code lost:
    
        r2 = r0.metadata;
        r3 = r0._capacity;
        r4 = r0.keys;
        r8 = r0.values;
        r7 = (r3 + 7) >> 3;
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00bb, code lost:
    
        if (r15 >= r7) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00bd, code lost:
    
        r9 = r2[r15] & r13;
        r2[r15] = (-72340172838076674L) & ((~r9) + (r9 >>> 7));
        r15 = r15 + 1;
        r13 = -9187201950435737472L;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d4, code lost:
    
        r7 = r2.length;
        r9 = r7 - 1;
        r7 = r7 - 2;
        r2[r7] = (r2[r7] & 72057594037927935L) | (-72057594037927936L);
        r2[r9] = r2[0];
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ee, code lost:
    
        if (r9 == r3) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00f0, code lost:
    
        r10 = r9 >> 3;
        r18 = (r9 & 7) << 3;
        r13 = (r2[r10] >> r18) & 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0100, code lost:
    
        if (r13 != 128) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0107, code lost:
    
        if (r13 == 254) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x010a, code lost:
    
        r13 = java.lang.Integer.hashCode(r4[r9]) * (-862048943);
        r13 = ((r13 << 16) ^ r13) >>> 7;
        r14 = r0.findFirstAvailableSlot(r13);
        r13 = r13 & r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x012e, code lost:
    
        if ((((r14 - r13) & r3) / 8) != (((r9 - r13) & r3) / 8)) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x014f, code lost:
    
        r6 = r14 >> 3;
        r31 = r2[r6];
        r7 = (r14 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x015f, code lost:
    
        if (((r31 >> r7) & 255) != 128) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0161, code lost:
    
        r33 = r11;
        r2[r6] = (r31 & (~(255 << r7))) | ((r5 & 127) << r7);
        r2[r10] = (r2[r10] & (~(255 << r18))) | (128 << r18);
        r4[r14] = r4[r9];
        r4[r9] = 0;
        r8[r14] = r8[r9];
        r8[r9] = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x01aa, code lost:
    
        r2[r2.length - 1] = (r2[0] & 72057594037927935L) | Long.MIN_VALUE;
        r9 = r9 + 1;
        r0 = r35;
        r11 = r33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x018a, code lost:
    
        r33 = r11;
        r2[r6] = ((r5 & 127) << r7) | (r31 & (~(255 << r7)));
        r0 = r4[r14];
        r4[r14] = r4[r9];
        r4[r9] = r0;
        r0 = r8[r14];
        r8[r14] = r8[r9];
        r8[r9] = r0;
        r9 = r9 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0130, code lost:
    
        r2[r10] = (r2[r10] & (~(255 << r18))) | ((r5 & 127) << r18);
        r2[r2.length - 1] = (r2[0] & 72057594037927935L) | Long.MIN_VALUE;
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0102, code lost:
    
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01c0, code lost:
    
        r33 = r11;
        r0.growthLimit = androidx.collection.ScatterMapKt.loadedCapacity(r0._capacity) - r0._size;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x024d, code lost:
    
        r2 = r0.findFirstAvailableSlot(r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0251, code lost:
    
        r0._size++;
        r1 = r0.growthLimit;
        r4 = r0.metadata;
        r5 = r2 >> 3;
        r6 = r4[r5];
        r8 = (r2 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x026b, code lost:
    
        if (((r6 >> r8) & 255) != 128) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x026d, code lost:
    
        r15 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0270, code lost:
    
        r0.growthLimit = r1 - r15;
        r1 = r0._capacity;
        r6 = (r6 & (~(255 << r8))) | (r33 << r8);
        r4[r5] = r6;
        r4[(((r2 - 7) & r1) + (r1 & 7)) >> 3] = r6;
        r1 = ~r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x026f, code lost:
    
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x01d2, code lost:
    
        r33 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x01d9, code lost:
    
        r1 = androidx.collection.ScatterMapKt.nextCapacity(r0._capacity);
        r2 = r0.metadata;
        r3 = r0.keys;
        r4 = r0.values;
        r5 = r0._capacity;
        r0.initializeStorage(r1);
        r1 = r0.metadata;
        r6 = r0.keys;
        r8 = r0.values;
        r9 = r0._capacity;
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x01f3, code lost:
    
        if (r10 >= r5) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0204, code lost:
    
        if (((r2[r10 >> 3] >> ((r10 & 7) << 3)) & 255) >= 128) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0206, code lost:
    
        r11 = r3[r10];
        r12 = java.lang.Integer.hashCode(r11) * (-862048943);
        r12 = r12 ^ (r12 << 16);
        r14 = r0.findFirstAvailableSlot(r12 >>> 7);
        r18 = r8;
        r7 = r12 & 127;
        r12 = r14 >> 3;
        r21 = (r14 & 7) << 3;
        r7 = (r7 << r21) | (r1[r12] & (~(255 << r21)));
        r1[r12] = r7;
        r1[(((r14 - 7) & r9) + (r9 & 7)) >> 3] = r7;
        r6[r14] = r11;
        r18[r14] = r4[r10];
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0247, code lost:
    
        r10 = r10 + 1;
        r8 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0245, code lost:
    
        r18 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01d6, code lost:
    
        r20 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0092, code lost:
    
        r33 = r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void set(int r36, int r37) {
        /*
            Method dump skipped, instructions count: 674
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableIntIntMap.set(int, int):void");
    }

    public final String toString() {
        if (this._size == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        int[] iArr = this.keys;
        int[] iArr2 = this.values;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i = 0;
            int i2 = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j) < 128) {
                            int i5 = (i << 3) + i4;
                            int i6 = iArr[i5];
                            int i7 = iArr2[i5];
                            sb.append(i6);
                            sb.append("=");
                            sb.append(i7);
                            i2++;
                            if (i2 < this._size) {
                                sb.append(", ");
                            }
                        }
                        j >>= 8;
                    }
                    if (i3 != 8) {
                        break;
                    }
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
