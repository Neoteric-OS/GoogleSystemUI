package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import androidx.collection.internal.RuntimeHelpersKt;
import java.util.Arrays;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutableObjectLongMap {
    public int _capacity;
    public int _size;
    public int growthLimit;
    public long[] metadata = ScatterMapKt.EmptyGroup;
    public Object[] keys = ContainerHelpersKt.EMPTY_OBJECTS;
    public long[] values = LongSetKt.EmptyLongArray;

    public MutableObjectLongMap(int i) {
        if (i >= 0) {
            initializeStorage(ScatterMapKt.unloadedCapacity(i));
        } else {
            RuntimeHelpersKt.throwIllegalArgumentException("Capacity must be a positive value.");
            throw null;
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MutableObjectLongMap)) {
            return false;
        }
        MutableObjectLongMap mutableObjectLongMap = (MutableObjectLongMap) obj;
        if (mutableObjectLongMap._size != this._size) {
            return false;
        }
        Object[] objArr = this.keys;
        long[] jArr = this.values;
        long[] jArr2 = this.metadata;
        int length = jArr2.length - 2;
        if (length >= 0) {
            int i = 0;
            while (true) {
                long j = jArr2[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            int i4 = (i << 3) + i3;
                            if (jArr[i4] != mutableObjectLongMap.get(objArr[i4])) {
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

    public final int findKeyIndex(Object obj) {
        int i = 0;
        int hashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i2 = hashCode ^ (hashCode << 16);
        int i3 = i2 & 127;
        int i4 = this._capacity;
        int i5 = i2 >>> 7;
        while (true) {
            int i6 = i5 & i4;
            long[] jArr = this.metadata;
            int i7 = i6 >> 3;
            int i8 = (i6 & 7) << 3;
            long j = ((jArr[i7 + 1] << (64 - i8)) & ((-i8) >> 63)) | (jArr[i7] >>> i8);
            long j2 = (i3 * 72340172838076673L) ^ j;
            for (long j3 = (~j2) & (j2 - 72340172838076673L) & (-9187201950435737472L); j3 != 0; j3 &= j3 - 1) {
                int numberOfTrailingZeros = ((Long.numberOfTrailingZeros(j3) >> 3) + i6) & i4;
                if (Intrinsics.areEqual(this.keys[numberOfTrailingZeros], obj)) {
                    return numberOfTrailingZeros;
                }
            }
            if ((j & ((~j) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i += 8;
            i5 = i6 + i;
        }
    }

    public final long get(Object obj) {
        int findKeyIndex = findKeyIndex(obj);
        if (findKeyIndex >= 0) {
            return this.values[findKeyIndex];
        }
        throw new NoSuchElementException("There is no key " + obj + " in the map");
    }

    public final int hashCode() {
        Object[] objArr = this.keys;
        long[] jArr = this.values;
        long[] jArr2 = this.metadata;
        int length = jArr2.length - 2;
        int i = 0;
        if (length >= 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                long j = jArr2[i2];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i4 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i5 = 0; i5 < i4; i5++) {
                        if ((255 & j) < 128) {
                            int i6 = (i2 << 3) + i5;
                            Object obj = objArr[i6];
                            i3 += (obj != null ? obj.hashCode() : 0) ^ Long.hashCode(jArr[i6]);
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
        this.keys = new Object[max];
        this.values = new long[max];
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0075, code lost:
    
        if (((((~r9) << 6) & r9) & (-9187201950435737472L)) == 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0077, code lost:
    
        r2 = r0.findFirstAvailableSlot(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0080, code lost:
    
        if (r0.growthLimit != 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0094, code lost:
    
        if (((r0.metadata[r2 >> 3] >> ((r2 & 7) << 3)) & 255) != 254) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x009a, code lost:
    
        r2 = r0._capacity;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x009c, code lost:
    
        if (r2 <= 8) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x009e, code lost:
    
        r22 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00b0, code lost:
    
        if (java.lang.Long.compareUnsigned(r0._size * 32, r2 * 25) > 0) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b2, code lost:
    
        r2 = r0.metadata;
        r3 = r0._capacity;
        r4 = r0.keys;
        r8 = r0.values;
        r7 = (r3 + 7) >> 3;
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00bf, code lost:
    
        if (r15 >= r7) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c1, code lost:
    
        r9 = r2[r15] & r13;
        r2[r15] = (-72340172838076674L) & ((~r9) + (r9 >>> 7));
        r15 = r15 + 1;
        r13 = -9187201950435737472L;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d8, code lost:
    
        r7 = r2.length;
        r9 = r7 - 1;
        r7 = r7 - 2;
        r2[r7] = (r2[r7] & 72057594037927935L) | (-72057594037927936L);
        r2[r9] = r2[0];
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00f2, code lost:
    
        if (r9 == r3) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00f4, code lost:
    
        r10 = r9 >> 3;
        r18 = (r9 & 7) << 3;
        r13 = (r2[r10] >> r18) & 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0104, code lost:
    
        if (r13 != 128) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010b, code lost:
    
        if (r13 == 254) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x010e, code lost:
    
        r7 = r4[r9];
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0110, code lost:
    
        if (r7 == null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0112, code lost:
    
        r7 = r7.hashCode();
        r5 = -862048943;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x011e, code lost:
    
        r13 = r7 * r5;
        r13 = ((r13 << 16) ^ r13) >>> 7;
        r14 = r0.findFirstAvailableSlot(r13);
        r13 = r13 & r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0139, code lost:
    
        if ((((r14 - r13) & r3) / 8) != (((r9 - r13) & r3) / 8)) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x015a, code lost:
    
        r6 = r14 >> 3;
        r33 = r2[r6];
        r7 = (r14 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x016a, code lost:
    
        if (((r33 >> r7) & 255) != 128) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x016c, code lost:
    
        r35 = r11;
        r2[r6] = (r33 & (~(255 << r7))) | ((r5 & 127) << r7);
        r2[r10] = (r2[r10] & (~(255 << r18))) | (128 << r18);
        r4[r14] = r4[r9];
        r4[r9] = null;
        r8[r14] = r8[r9];
        r8[r9] = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x01b5, code lost:
    
        r2[r2.length - 1] = (r2[0] & 72057594037927935L) | Long.MIN_VALUE;
        r9 = r9 + 1;
        r0 = r37;
        r11 = r35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0195, code lost:
    
        r35 = r11;
        r2[r6] = ((r5 & 127) << r7) | (r33 & (~(255 << r7)));
        r0 = r4[r14];
        r4[r14] = r4[r9];
        r4[r9] = r0;
        r0 = r8[r14];
        r8[r14] = r8[r9];
        r8[r9] = r0;
        r9 = r9 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x013b, code lost:
    
        r2[r10] = (r2[r10] & (~(255 << r18))) | ((r5 & 127) << r18);
        r2[r2.length - 1] = (r2[0] & 72057594037927935L) | Long.MIN_VALUE;
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x011a, code lost:
    
        r5 = -862048943;
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0106, code lost:
    
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01cb, code lost:
    
        r35 = r11;
        r0.growthLimit = androidx.collection.ScatterMapKt.loadedCapacity(r0._capacity) - r0._size;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x025d, code lost:
    
        r2 = r0.findFirstAvailableSlot(r22);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0261, code lost:
    
        r0._size++;
        r1 = r0.growthLimit;
        r4 = r0.metadata;
        r5 = r2 >> 3;
        r6 = r4[r5];
        r8 = (r2 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x027b, code lost:
    
        if (((r6 >> r8) & 255) != 128) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x027d, code lost:
    
        r15 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0280, code lost:
    
        r0.growthLimit = r1 - r15;
        r1 = r0._capacity;
        r6 = (r6 & (~(255 << r8))) | (r35 << r8);
        r4[r5] = r6;
        r4[(((r2 - 7) & r1) + (r1 & 7)) >> 3] = r6;
        r1 = ~r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x027f, code lost:
    
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01dd, code lost:
    
        r35 = r11;
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01e4, code lost:
    
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
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01fe, code lost:
    
        if (r10 >= r5) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x020f, code lost:
    
        if (((r2[r10 >> 3] >> ((r10 & 7) << 3)) & 255) >= 128) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0211, code lost:
    
        r11 = r3[r10];
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0213, code lost:
    
        if (r11 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0215, code lost:
    
        r12 = r11.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x021f, code lost:
    
        r12 = r12 * (-862048943);
        r12 = r12 ^ (r12 << 16);
        r14 = r0.findFirstAvailableSlot(r12 >>> 7);
        r16 = r8;
        r7 = r12 & 127;
        r12 = r14 >> 3;
        r17 = (r14 & 7) << 3;
        r7 = (r7 << r17) | (r1[r12] & (~(255 << r17)));
        r1[r12] = r7;
        r1[(((r14 - 7) & r9) + (r9 & 7)) >> 3] = r7;
        r6[r14] = r11;
        r16[r14] = r4[r10];
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0257, code lost:
    
        r10 = r10 + 1;
        r8 = r16;
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x021d, code lost:
    
        r12 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0255, code lost:
    
        r16 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01e1, code lost:
    
        r22 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0096, code lost:
    
        r35 = r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void set(long r38, java.lang.Object r40) {
        /*
            Method dump skipped, instructions count: 690
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableObjectLongMap.set(long, java.lang.Object):void");
    }

    public final String toString() {
        int i;
        int i2;
        if (this._size == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        Object[] objArr = this.keys;
        long[] jArr = this.values;
        long[] jArr2 = this.metadata;
        int length = jArr2.length - 2;
        if (length >= 0) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                long j = jArr2[i3];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8 - ((~(i3 - length)) >>> 31);
                    int i6 = 0;
                    while (i6 < i5) {
                        if ((255 & j) < 128) {
                            int i7 = (i3 << 3) + i6;
                            Object obj = objArr[i7];
                            i2 = i3;
                            long j2 = jArr[i7];
                            if (obj == this) {
                                obj = "(this)";
                            }
                            sb.append(obj);
                            sb.append("=");
                            sb.append(j2);
                            i4++;
                            if (i4 < this._size) {
                                sb.append(", ");
                            }
                        } else {
                            i2 = i3;
                        }
                        j >>= 8;
                        i6++;
                        i3 = i2;
                    }
                    int i8 = i3;
                    if (i5 != 8) {
                        break;
                    }
                    i = i8;
                } else {
                    i = i3;
                }
                if (i == length) {
                    break;
                }
                i3 = i + 1;
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
