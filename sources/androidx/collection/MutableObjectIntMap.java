package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import androidx.collection.internal.RuntimeHelpersKt;
import java.util.Arrays;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutableObjectIntMap {
    public int _capacity;
    public int _size;
    public int growthLimit;
    public Object[] keys;
    public long[] metadata;
    public int[] values;

    public MutableObjectIntMap(int i) {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.keys = ContainerHelpersKt.EMPTY_OBJECTS;
        this.values = IntSetKt.EmptyIntArray;
        if (i >= 0) {
            initializeStorage(ScatterMapKt.unloadedCapacity(i));
        } else {
            RuntimeHelpersKt.throwIllegalArgumentException("Capacity must be a positive value.");
            throw null;
        }
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
        Arrays.fill(this.keys, 0, this._capacity, (Object) null);
        this.growthLimit = ScatterMapKt.loadedCapacity(this._capacity) - this._size;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MutableObjectIntMap)) {
            return false;
        }
        MutableObjectIntMap mutableObjectIntMap = (MutableObjectIntMap) obj;
        if (mutableObjectIntMap._size != this._size) {
            return false;
        }
        Object[] objArr = this.keys;
        int[] iArr = this.values;
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
                            if (iArr[i4] != mutableObjectIntMap.get(objArr[i4])) {
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

    public final int findIndex(Object obj) {
        long j;
        int i;
        long[] jArr;
        Object[] objArr;
        long j2;
        int i2;
        int hashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i3 = hashCode ^ (hashCode << 16);
        int i4 = i3 >>> 7;
        int i5 = i3 & 127;
        int i6 = this._capacity;
        int i7 = i4 & i6;
        int i8 = 0;
        while (true) {
            long[] jArr2 = this.metadata;
            int i9 = i7 >> 3;
            int i10 = (i7 & 7) << 3;
            long j3 = ((jArr2[i9 + 1] << (64 - i10)) & ((-i10) >> 63)) | (jArr2[i9] >>> i10);
            long j4 = i5;
            int i11 = i5;
            long j5 = j3 ^ (j4 * 72340172838076673L);
            long j6 = -9187201950435737472L;
            for (long j7 = (~j5) & (j5 - 72340172838076673L) & (-9187201950435737472L); j7 != 0; j7 &= j7 - 1) {
                int numberOfTrailingZeros = (i7 + (Long.numberOfTrailingZeros(j7) >> 3)) & i6;
                if (Intrinsics.areEqual(this.keys[numberOfTrailingZeros], obj)) {
                    return numberOfTrailingZeros;
                }
            }
            if ((((~j3) << 6) & j3 & (-9187201950435737472L)) != 0) {
                int findFirstAvailableSlot = findFirstAvailableSlot(i4);
                if (this.growthLimit != 0 || ((this.metadata[findFirstAvailableSlot >> 3] >> ((findFirstAvailableSlot & 7) << 3)) & 255) == 254) {
                    j = j4;
                    i = 0;
                } else {
                    int i12 = this._capacity;
                    if (i12 <= 8 || Long.compareUnsigned(this._size * 32, i12 * 25) > 0) {
                        j = j4;
                        i = 0;
                        int nextCapacity = ScatterMapKt.nextCapacity(this._capacity);
                        long[] jArr3 = this.metadata;
                        Object[] objArr2 = this.keys;
                        int[] iArr = this.values;
                        int i13 = this._capacity;
                        initializeStorage(nextCapacity);
                        long[] jArr4 = this.metadata;
                        Object[] objArr3 = this.keys;
                        int[] iArr2 = this.values;
                        int i14 = this._capacity;
                        int i15 = 0;
                        while (i15 < i13) {
                            if (((jArr3[i15 >> 3] >> ((i15 & 7) << 3)) & 255) < 128) {
                                Object obj2 = objArr2[i15];
                                int hashCode2 = (obj2 != null ? obj2.hashCode() : 0) * (-862048943);
                                int i16 = hashCode2 ^ (hashCode2 << 16);
                                int findFirstAvailableSlot2 = findFirstAvailableSlot(i16 >>> 7);
                                jArr = jArr3;
                                objArr = objArr2;
                                long j8 = i16 & 127;
                                int i17 = findFirstAvailableSlot2 >> 3;
                                int i18 = (findFirstAvailableSlot2 & 7) << 3;
                                long j9 = (j8 << i18) | (jArr4[i17] & (~(255 << i18)));
                                jArr4[i17] = j9;
                                jArr4[(((findFirstAvailableSlot2 - 7) & i14) + (i14 & 7)) >> 3] = j9;
                                objArr3[findFirstAvailableSlot2] = obj2;
                                iArr2[findFirstAvailableSlot2] = iArr[i15];
                            } else {
                                jArr = jArr3;
                                objArr = objArr2;
                            }
                            i15++;
                            jArr3 = jArr;
                            objArr2 = objArr;
                        }
                    } else {
                        long[] jArr5 = this.metadata;
                        int i19 = this._capacity;
                        Object[] objArr4 = this.keys;
                        int[] iArr3 = this.values;
                        int i20 = (i19 + 7) >> 3;
                        int i21 = 0;
                        while (i21 < i20) {
                            long j10 = jArr5[i21] & j6;
                            jArr5[i21] = (-72340172838076674L) & ((~j10) + (j10 >>> 7));
                            i21++;
                            j6 = -9187201950435737472L;
                        }
                        int length = jArr5.length;
                        int i22 = length - 1;
                        int i23 = length - 2;
                        long j11 = 72057594037927935L;
                        jArr5[i23] = (jArr5[i23] & 72057594037927935L) | (-72057594037927936L);
                        jArr5[i22] = jArr5[0];
                        int i24 = 0;
                        while (i24 != i19) {
                            int i25 = i24 >> 3;
                            int i26 = (i24 & 7) << 3;
                            long j12 = (jArr5[i25] >> i26) & 255;
                            if (j12 != 128 && j12 == 254) {
                                Object obj3 = objArr4[i24];
                                int hashCode3 = (obj3 != null ? obj3.hashCode() : 0) * (-862048943);
                                int i27 = (hashCode3 ^ (hashCode3 << 16)) >>> 7;
                                int findFirstAvailableSlot3 = findFirstAvailableSlot(i27);
                                int i28 = i27 & i19;
                                if (((findFirstAvailableSlot3 - i28) & i19) / 8 == ((i24 - i28) & i19) / 8) {
                                    jArr5[i25] = (jArr5[i25] & (~(255 << i26))) | ((r9 & 127) << i26);
                                    jArr5[jArr5.length - 1] = (jArr5[0] & j11) | Long.MIN_VALUE;
                                    i24++;
                                } else {
                                    int i29 = i24;
                                    int i30 = findFirstAvailableSlot3 >> 3;
                                    long j13 = jArr5[i30];
                                    int i31 = (findFirstAvailableSlot3 & 7) << 3;
                                    if (((j13 >> i31) & 255) == 128) {
                                        jArr5[i30] = ((r9 & 127) << i31) | (j13 & (~(255 << i31)));
                                        jArr5[i25] = (jArr5[i25] & (~(255 << i26))) | (128 << i26);
                                        objArr4[findFirstAvailableSlot3] = objArr4[i29];
                                        objArr4[i29] = null;
                                        iArr3[findFirstAvailableSlot3] = iArr3[i29];
                                        iArr3[i29] = 0;
                                        j2 = j4;
                                        i2 = i29;
                                    } else {
                                        j2 = j4;
                                        jArr5[i30] = ((r9 & 127) << i31) | (j13 & (~(255 << i31)));
                                        Object obj4 = objArr4[findFirstAvailableSlot3];
                                        objArr4[findFirstAvailableSlot3] = objArr4[i29];
                                        objArr4[i29] = obj4;
                                        int i32 = iArr3[findFirstAvailableSlot3];
                                        iArr3[findFirstAvailableSlot3] = iArr3[i29];
                                        iArr3[i29] = i32;
                                        i2 = i29 - 1;
                                    }
                                    jArr5[jArr5.length - 1] = (jArr5[0] & 72057594037927935L) | Long.MIN_VALUE;
                                    i24 = i2 + 1;
                                    j11 = 72057594037927935L;
                                    j4 = j2;
                                }
                            } else {
                                i24++;
                            }
                        }
                        j = j4;
                        i = 0;
                        this.growthLimit = ScatterMapKt.loadedCapacity(this._capacity) - this._size;
                    }
                    findFirstAvailableSlot = findFirstAvailableSlot(i4);
                }
                this._size++;
                int i33 = this.growthLimit;
                long[] jArr6 = this.metadata;
                int i34 = findFirstAvailableSlot >> 3;
                long j14 = jArr6[i34];
                int i35 = (findFirstAvailableSlot & 7) << 3;
                this.growthLimit = i33 - (((j14 >> i35) & 255) != 128 ? i : 1);
                int i36 = this._capacity;
                long j15 = ((~(255 << i35)) & j14) | (j << i35);
                jArr6[i34] = j15;
                jArr6[(((findFirstAvailableSlot - 7) & i36) + (i36 & 7)) >> 3] = j15;
                return ~findFirstAvailableSlot;
            }
            i8 += 8;
            i7 = (i7 + i8) & i6;
            i5 = i11;
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

    public final int get(Object obj) {
        int findKeyIndex = findKeyIndex(obj);
        if (findKeyIndex >= 0) {
            return this.values[findKeyIndex];
        }
        throw new NoSuchElementException("There is no key " + obj + " in the map");
    }

    public final int hashCode() {
        Object[] objArr = this.keys;
        int[] iArr = this.values;
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
                            Object obj = objArr[i6];
                            i3 += Integer.hashCode(iArr[i6]) ^ (obj != null ? obj.hashCode() : 0);
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
        this.values = new int[max];
    }

    public final void removeValueAt(int i) {
        this._size--;
        long[] jArr = this.metadata;
        int i2 = this._capacity;
        int i3 = i >> 3;
        int i4 = (i & 7) << 3;
        long j = (jArr[i3] & (~(255 << i4))) | (254 << i4);
        jArr[i3] = j;
        jArr[(((i - 7) & i2) + (i2 & 7)) >> 3] = j;
        this.keys[i] = null;
    }

    public final void set(int i, Object obj) {
        int findIndex = findIndex(obj);
        if (findIndex < 0) {
            findIndex = ~findIndex;
        }
        this.keys[findIndex] = obj;
        this.values[findIndex] = i;
    }

    public final String toString() {
        if (this._size == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        Object[] objArr = this.keys;
        int[] iArr = this.values;
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
                            Object obj = objArr[i5];
                            int i6 = iArr[i5];
                            if (obj == this) {
                                obj = "(this)";
                            }
                            sb.append(obj);
                            sb.append("=");
                            sb.append(i6);
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

    public /* synthetic */ MutableObjectIntMap() {
        this(6);
    }
}
