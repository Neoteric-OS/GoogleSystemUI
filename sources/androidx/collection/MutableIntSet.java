package androidx.collection;

import androidx.collection.internal.RuntimeHelpersKt;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutableIntSet {
    public int _capacity;
    public int _size;
    public int[] elements;
    public int growthLimit;
    public long[] metadata;

    public MutableIntSet(int i) {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.elements = IntSetKt.EmptyIntArray;
        if (i >= 0) {
            initializeStorage(ScatterMapKt.unloadedCapacity(i));
        } else {
            RuntimeHelpersKt.throwIllegalArgumentException("Capacity must be a positive value.");
            throw null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0076, code lost:
    
        if (((((~r8) << 6) & r8) & (-9187201950435737472L)) == 0) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0078, code lost:
    
        r3 = findFirstAvailableSlot(r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0083, code lost:
    
        if (r32.growthLimit != 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0097, code lost:
    
        if (((r32.metadata[r3 >> 3] >> ((r3 & 7) << 3)) & 255) != 254) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x009f, code lost:
    
        r3 = r32._capacity;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00a1, code lost:
    
        if (r3 <= 8) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b3, code lost:
    
        if (java.lang.Long.compareUnsigned(r32._size * 32, r3 * 25) > 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b5, code lost:
    
        r3 = r32.metadata;
        r5 = r32._capacity;
        r6 = r32.elements;
        r8 = (r5 + 7) >> 3;
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c0, code lost:
    
        if (r9 >= r8) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c2, code lost:
    
        r1 = r3[r9] & r12;
        r3[r9] = (-72340172838076674L) & ((~r1) + (r1 >>> 7));
        r9 = r9 + 1;
        r2 = r2;
        r12 = -9187201950435737472L;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
    
        r20 = r2;
        r1 = r3.length;
        r2 = r1 - 1;
        r1 = r1 - 2;
        r3[r1] = (r3[r1] & 72057594037927935L) | (-72057594037927936L);
        r3[r2] = r3[0];
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00fa, code lost:
    
        if (r1 == r5) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00fc, code lost:
    
        r2 = r1 >> 3;
        r26 = (r1 & 7) << 3;
        r8 = (r3[r2] >> r26) & 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x010c, code lost:
    
        if (r8 != 128) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0113, code lost:
    
        if (r8 == 254) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0116, code lost:
    
        r8 = java.lang.Integer.hashCode(r6[r1]) * (-862048943);
        r9 = (r8 ^ (r8 << 16)) >>> 7;
        r21 = findFirstAvailableSlot(r9);
        r9 = r9 & r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0139, code lost:
    
        if ((((r21 - r9) & r5) / 8) != (((r1 - r9) & r5) / 8)) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0161, code lost:
    
        r9 = r21 >> 3;
        r12 = r3[r9];
        r14 = (r21 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0171, code lost:
    
        if (((r12 >> r14) & 255) != 128) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0173, code lost:
    
        r30 = r10;
        r3[r9] = ((r8 & 127) << r14) | ((~(255 << r14)) & r12);
        r3[r2] = (r3[r2] & (~(255 << r26))) | (128 << r26);
        r6[r21] = r6[r1];
        r6[r1] = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x01ac, code lost:
    
        r3[r3.length - 1] = (r3[0] & 72057594037927935L) | Long.MIN_VALUE;
        r1 = r1 + 1;
        r10 = r30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0195, code lost:
    
        r30 = r10;
        r3[r9] = ((r8 & 127) << r14) | ((~(255 << r14)) & r12);
        r2 = r6[r21];
        r6[r21] = r6[r1];
        r6[r1] = r2;
        r1 = r1 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x013b, code lost:
    
        r3[r2] = ((r8 & 127) << r26) | (r3[r2] & (~(255 << r26)));
        r3[r3.length - 1] = (r3[0] & 72057594037927935L) | Long.MIN_VALUE;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x010e, code lost:
    
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01c2, code lost:
    
        r30 = r10;
        r32.growthLimit = androidx.collection.ScatterMapKt.loadedCapacity(r32._capacity) - r32._size;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x023e, code lost:
    
        r1 = findFirstAvailableSlot(r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0244, code lost:
    
        r32._size++;
        r2 = r32.growthLimit;
        r3 = r32.metadata;
        r4 = r1 >> 3;
        r5 = r3[r4];
        r7 = (r1 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x025d, code lost:
    
        if (((r5 >> r7) & 255) != 128) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x025f, code lost:
    
        r8 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0262, code lost:
    
        r32.growthLimit = r2 - r8;
        r2 = r32._capacity;
        r5 = (r5 & (~(255 << r7))) | (r30 << r7);
        r3[r4] = r5;
        r3[(((r1 - 7) & r2) + (r2 & 7)) >> 3] = r5;
        r18 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0261, code lost:
    
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x01d1, code lost:
    
        r20 = r2;
        r30 = r10;
        r1 = androidx.collection.ScatterMapKt.nextCapacity(r32._capacity);
        r2 = r32.metadata;
        r3 = r32.elements;
        r5 = r32._capacity;
        initializeStorage(r1);
        r1 = r32.metadata;
        r6 = r32.elements;
        r7 = r32._capacity;
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x01ec, code lost:
    
        if (r9 >= r5) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01fd, code lost:
    
        if (((r2[r9 >> 3] >> ((r9 & 7) << 3)) & 255) >= 128) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01ff, code lost:
    
        r10 = r3[r9];
        r11 = java.lang.Integer.hashCode(r10) * (-862048943);
        r11 = r11 ^ (r11 << 16);
        r13 = findFirstAvailableSlot(r11 >>> 7);
        r14 = r9;
        r8 = r11 & 127;
        r11 = r13 >> 3;
        r19 = (r13 & 7) << 3;
        r8 = (r8 << r19) | (r1[r11] & (~(255 << r19)));
        r1[r11] = r8;
        r1[(((r13 - 7) & r7) + (r7 & 7)) >> 3] = r8;
        r6[r13] = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x023a, code lost:
    
        r9 = r14 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0239, code lost:
    
        r14 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0099, code lost:
    
        r20 = r2;
        r30 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0243, code lost:
    
        r1 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean add(int r33) {
        /*
            Method dump skipped, instructions count: 667
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableIntSet.add(int):boolean");
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

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0066, code lost:
    
        if (((r6 & ((~r6) << 6)) & (-9187201950435737472L)) == 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0068, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean contains(int r19) {
        /*
            r18 = this;
            r0 = r18
            int r1 = java.lang.Integer.hashCode(r19)
            r2 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r1 = r1 * r2
            int r2 = r1 << 16
            r1 = r1 ^ r2
            r2 = r1 & 127(0x7f, float:1.78E-43)
            int r3 = r0._capacity
            int r1 = r1 >>> 7
            r1 = r1 & r3
            r4 = 0
            r5 = r4
        L16:
            long[] r6 = r0.metadata
            int r7 = r1 >> 3
            r8 = r1 & 7
            int r8 = r8 << 3
            r9 = r6[r7]
            long r9 = r9 >>> r8
            r11 = 1
            int r7 = r7 + r11
            r6 = r6[r7]
            int r12 = 64 - r8
            long r6 = r6 << r12
            long r12 = (long) r8
            long r12 = -r12
            r8 = 63
            long r12 = r12 >> r8
            long r6 = r6 & r12
            long r6 = r6 | r9
            long r8 = (long) r2
            r12 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r8 = r8 * r12
            long r8 = r8 ^ r6
            long r12 = r8 - r12
            long r8 = ~r8
            long r8 = r8 & r12
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r12
        L41:
            r14 = 0
            int r10 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r10 == 0) goto L5f
            int r10 = java.lang.Long.numberOfTrailingZeros(r8)
            int r10 = r10 >> 3
            int r10 = r10 + r1
            r10 = r10 & r3
            int[] r14 = r0.elements
            r14 = r14[r10]
            r15 = r19
            if (r14 != r15) goto L58
            goto L69
        L58:
            r16 = 1
            long r16 = r8 - r16
            long r8 = r8 & r16
            goto L41
        L5f:
            long r8 = ~r6
            r10 = 6
            long r8 = r8 << r10
            long r6 = r6 & r8
            long r6 = r6 & r12
            int r6 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r6 == 0) goto L6d
            r10 = -1
        L69:
            if (r10 < 0) goto L6c
            r4 = r11
        L6c:
            return r4
        L6d:
            int r5 = r5 + 8
            int r1 = r1 + r5
            r1 = r1 & r3
            goto L16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableIntSet.contains(int):boolean");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MutableIntSet)) {
            return false;
        }
        MutableIntSet mutableIntSet = (MutableIntSet) obj;
        if (mutableIntSet._size != this._size) {
            return false;
        }
        int[] iArr = this.elements;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128 && !mutableIntSet.contains(iArr[(i << 3) + i3])) {
                            return false;
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

    public final int hashCode() {
        int[] iArr = this.elements;
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
                            i3 = Integer.hashCode(iArr[(i2 << 3) + i5]) + i3;
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
        this.elements = new int[max];
    }

    public final void removeElementAt(int i) {
        this._size--;
        long[] jArr = this.metadata;
        int i2 = this._capacity;
        int i3 = i >> 3;
        int i4 = (i & 7) << 3;
        long j = (jArr[i3] & (~(255 << i4))) | (254 << i4);
        jArr[i3] = j;
        jArr[(((i - 7) & i2) + (i2 & 7)) >> 3] = j;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "[");
        int[] iArr = this.elements;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i = 0;
            int i2 = 0;
            loop0: while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j) < 128) {
                            int i5 = iArr[(i << 3) + i4];
                            if (i2 == -1) {
                                sb.append((CharSequence) "...");
                                break loop0;
                            }
                            if (i2 != 0) {
                                sb.append((CharSequence) ", ");
                            }
                            sb.append(i5);
                            i2++;
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
        sb.append((CharSequence) "]");
        return sb.toString();
    }

    public /* synthetic */ MutableIntSet() {
        this(6);
    }
}
