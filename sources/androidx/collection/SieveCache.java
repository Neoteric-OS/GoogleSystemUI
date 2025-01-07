package androidx.collection;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SieveCache {
    public int _capacity;
    public int _count;
    public final int _maxSize;
    public int _size;
    public final Function1 createValueFromKey;
    public int growthLimit;
    public int hand;
    public int head;
    public Object[] keys;
    public long[] metadata;
    public long[] nodes;
    public final Function4 onEntryRemoved;
    public final Function2 sizeOf;
    public int tail;
    public Object[] values;

    public SieveCache() {
        AnonymousClass1 anonymousClass1 = new Function2() { // from class: androidx.collection.SieveCache.1
            @Override // kotlin.jvm.functions.Function2
            public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return 1;
            }
        };
        AnonymousClass2 anonymousClass2 = new Function1() { // from class: androidx.collection.SieveCache.2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return null;
            }
        };
        AnonymousClass3 anonymousClass3 = new Function4() { // from class: androidx.collection.SieveCache.3
            @Override // kotlin.jvm.functions.Function4
            public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                ((Boolean) obj4).booleanValue();
                return Unit.INSTANCE;
            }
        };
        this.sizeOf = anonymousClass1;
        this.createValueFromKey = anonymousClass2;
        this.onEntryRemoved = anonymousClass3;
        this.metadata = ScatterMapKt.EmptyGroup;
        Object[] objArr = ContainerHelpersKt.EMPTY_OBJECTS;
        this.keys = objArr;
        this.values = objArr;
        this.nodes = SieveCacheKt.EmptyNodes;
        this.head = Integer.MAX_VALUE;
        this.tail = Integer.MAX_VALUE;
        this.hand = Integer.MAX_VALUE;
        this._maxSize = 16;
        initializeStorage(ScatterMapKt.unloadedCapacity(16));
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SieveCache)) {
            return false;
        }
        SieveCache sieveCache = (SieveCache) obj;
        if (sieveCache._size != this._size || sieveCache._count != this._count) {
            return false;
        }
        Object[] objArr = this.keys;
        Object[] objArr2 = this.values;
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
                            if (!objArr2[i4].equals(sieveCache.get(objArr[i4]))) {
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

    public final Object get(Object obj) {
        int findKeyIndex = findKeyIndex(obj);
        if (findKeyIndex >= 0) {
            long[] jArr = this.nodes;
            jArr[findKeyIndex] = (jArr[findKeyIndex] & 4611686018427387903L) | 4611686018427387904L;
            return this.values[findKeyIndex];
        }
        Object invoke = this.createValueFromKey.invoke(obj);
        if (invoke == null) {
            return null;
        }
        put(obj, invoke);
        return invoke;
    }

    public final int hashCode() {
        Object[] objArr = this.keys;
        Object[] objArr2 = this.values;
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
                            i3 += objArr2[i6].hashCode() ^ obj.hashCode();
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
        long[] jArr2;
        int max = i > 0 ? Math.max(7, ScatterMapKt.normalizeCapacity(i)) : 0;
        this._capacity = max;
        if (max == 0) {
            jArr = ScatterMapKt.EmptyGroup;
        } else {
            int i2 = ((max + 15) & (-8)) >> 3;
            long[] jArr3 = new long[i2];
            Arrays.fill(jArr3, 0, i2, -9187201950435737472L);
            int i3 = max >> 3;
            long j = 255 << ((max & 7) << 3);
            jArr3[i3] = (jArr3[i3] & (~j)) | j;
            jArr = jArr3;
        }
        this.metadata = jArr;
        this.growthLimit = ScatterMapKt.loadedCapacity(this._capacity) - this._count;
        Object[] objArr = ContainerHelpersKt.EMPTY_OBJECTS;
        this.keys = max == 0 ? objArr : new Object[max];
        if (max != 0) {
            objArr = new Object[max];
        }
        this.values = objArr;
        if (max == 0) {
            jArr2 = SieveCacheKt.EmptyNodes;
        } else {
            long[] jArr4 = new long[max];
            Arrays.fill(jArr4, 0, max, 4611686018427387903L);
            jArr2 = jArr4;
        }
        this.nodes = jArr2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x029c, code lost:
    
        r14 = 4294967295L;
        r3 = (int) (r8[r3] & 4294967295L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x02ba, code lost:
    
        r1 = r43.head;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x02bf, code lost:
    
        if (r1 == Integer.MAX_VALUE) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x02c1, code lost:
    
        r9 = 4294967295L;
        r43.head = (int) (r8[r1] & 4294967295L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x02d2, code lost:
    
        r1 = r43.tail;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x02d4, code lost:
    
        if (r1 == Integer.MAX_VALUE) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x02d6, code lost:
    
        r43.tail = (int) (r8[r1] & r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x02dc, code lost:
    
        r1 = r43.hand;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x02de, code lost:
    
        if (r1 == Integer.MAX_VALUE) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x02e0, code lost:
    
        r43.hand = (int) (r8[r1] & r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x02cd, code lost:
    
        r9 = 4294967295L;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x02e8, code lost:
    
        r41 = r10;
        r5 = 0;
        r1 = androidx.collection.ScatterMapKt.nextCapacity(r43._capacity);
        r2 = r43.metadata;
        r3 = r43.keys;
        r6 = r43.values;
        r7 = r43.nodes;
        r8 = r43._capacity;
        r9 = new int[r8];
        initializeStorage(r1);
        r1 = r43.metadata;
        r10 = r43.keys;
        r11 = r43.values;
        r12 = r43.nodes;
        r13 = r43._capacity;
        r14 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x030b, code lost:
    
        if (r14 >= r8) goto L146;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x031d, code lost:
    
        if (((r2[r14 >> 3] >> ((r14 & 7) << 3)) & 255) >= 128) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x031f, code lost:
    
        r15 = r3[r14];
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0321, code lost:
    
        if (r15 == null) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0323, code lost:
    
        r16 = r15.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x032e, code lost:
    
        r16 = r16 * (-862048943);
        r16 = r16 ^ (r16 << 16);
        r5 = findFirstAvailableSlot(r16 >>> 7);
        r27 = r2;
        r2 = r16 & 127;
        r16 = r3;
        r29 = r5 >> 3;
        r32 = (r5 & 7) << 3;
        r36 = r8;
        r37 = r9;
        r2 = (r2 << r32) | (r1[r29] & (~(255 << r32)));
        r1[r29] = r2;
        r1[(((r5 - 7) & r13) + (r13 & 7)) >> 3] = r2;
        r10[r5] = r15;
        r11[r5] = r6[r14];
        r12[r5] = r7[r14];
        r37[r14] = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x037b, code lost:
    
        r14 = r14 + 1;
        r3 = r16;
        r2 = r27;
        r8 = r36;
        r9 = r37;
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x032b, code lost:
    
        r16 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0370, code lost:
    
        r27 = r2;
        r16 = r3;
        r36 = r8;
        r37 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0387, code lost:
    
        r37 = r9;
        r1 = r43.nodes;
        r2 = r1.length;
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x038d, code lost:
    
        if (r9 >= r2) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x038f, code lost:
    
        r5 = r1[r9];
        r3 = (int) ((r5 >> 31) & 2147483647L);
        r7 = (int) (r5 & 2147483647L);
        r5 = r5 & (-4611686018427387904L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x039e, code lost:
    
        if (r3 != Integer.MAX_VALUE) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x03a0, code lost:
    
        r3 = Integer.MAX_VALUE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x03a6, code lost:
    
        r5 = (r5 | r3) << 31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x03aa, code lost:
    
        if (r7 != Integer.MAX_VALUE) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x03ac, code lost:
    
        r3 = Integer.MAX_VALUE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x03b2, code lost:
    
        r1[r9] = r5 | r3;
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x03ae, code lost:
    
        r3 = r37[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x03a2, code lost:
    
        r3 = r37[r3];
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x03b9, code lost:
    
        r1 = r43.head;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x03be, code lost:
    
        if (r1 == Integer.MAX_VALUE) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x03c0, code lost:
    
        r43.head = r37[r1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x03c4, code lost:
    
        r1 = r43.tail;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x03c6, code lost:
    
        if (r1 == Integer.MAX_VALUE) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x03c8, code lost:
    
        r43.tail = r37[r1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x03cc, code lost:
    
        r1 = r43.hand;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x03ce, code lost:
    
        if (r1 == Integer.MAX_VALUE) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x03d0, code lost:
    
        r43.hand = r37[r1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x00a2, code lost:
    
        r41 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x007f, code lost:
    
        if (((((~r8) << 6) & r8) & (-9187201950435737472L)) == 0) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0081, code lost:
    
        r3 = findFirstAvailableSlot(r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x008c, code lost:
    
        if (r43.growthLimit != 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a0, code lost:
    
        if (((r43.metadata[r3 >> 3] >> ((r3 & 7) << 3)) & 255) != 254) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a6, code lost:
    
        r3 = r43._capacity;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00aa, code lost:
    
        if (r3 <= 8) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00bc, code lost:
    
        if (java.lang.Long.compareUnsigned(r43._count * 32, r3 * 25) > 0) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00be, code lost:
    
        r3 = r43.metadata;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c0, code lost:
    
        if (r3 != null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c2, code lost:
    
        r41 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x03d4, code lost:
    
        r3 = findFirstAvailableSlot(r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x03d8, code lost:
    
        r2 = 1;
        r43._count++;
        r1 = r43.growthLimit;
        r4 = r43.metadata;
        r5 = r3 >> 3;
        r6 = r4[r5];
        r8 = (r3 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x03f2, code lost:
    
        if (((r6 >> r8) & 255) != 128) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x03f5, code lost:
    
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x03f6, code lost:
    
        r43.growthLimit = r1 - r2;
        r1 = r43._capacity;
        r6 = (r6 & (~(255 << r8))) | (r41 << r8);
        r4[r5] = r6;
        r4[(((r3 - 7) & r1) + (r1 & 7)) >> 3] = r6;
        r1 = ~r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00c7, code lost:
    
        r5 = r43._capacity;
        r6 = r43.keys;
        r15 = r43.values;
        r14 = r43.nodes;
        r8 = new long[r5];
        java.util.Arrays.fill(r8, 0, r5, -1L);
        r9 = (r5 + 7) >> 3;
        r12 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00dc, code lost:
    
        if (r12 >= r9) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00de, code lost:
    
        r13 = r3[r12] & (-9187201950435737472L);
        r3[r12] = ((~r13) + (r13 >>> 7)) & (-72340172838076674L);
        r12 = r12 + 1;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00fd, code lost:
    
        r36 = r14;
        r1 = r3.length;
        r2 = r1 - 1;
        r1 = r1 - 2;
        r3[r1] = (r3[r1] & 72057594037927935L) | (-72057594037927936L);
        r3[r2] = r3[0];
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0119, code lost:
    
        if (r9 == r5) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x011b, code lost:
    
        r12 = r9 >> 3;
        r32 = (r9 & 7) << 3;
        r13 = (r3[r12] >> r32) & 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x012b, code lost:
    
        if (r13 != 128) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0132, code lost:
    
        if (r13 == 254) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0135, code lost:
    
        r13 = r6[r9];
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0137, code lost:
    
        if (r13 == null) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0139, code lost:
    
        r13 = r13.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0143, code lost:
    
        r13 = r13 * (-862048943);
        r14 = (r13 ^ (r13 << 16)) >>> 7;
        r7 = findFirstAvailableSlot(r14);
        r14 = r14 & r5;
        r29 = r36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x015f, code lost:
    
        if ((((r7 - r14) & r5) / 8) != (((r9 - r14) & r5) / 8)) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0188, code lost:
    
        r38 = r15;
        r1 = r7 >> 3;
        r14 = r3[r1];
        r2 = (r7 & 7) << 3;
        r36 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x01a1, code lost:
    
        if (((r14 >> r2) & 255) != 128) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x01a3, code lost:
    
        r35 = r6;
        r41 = r10;
        r3[r1] = ((r13 & 127) << r2) | ((~(255 << r2)) & r14);
        r3[r12] = (r3[r12] & (~(255 << r32))) | (128 << r32);
        r35[r7] = r35[r9];
        r35[r9] = null;
        r38[r7] = r38[r9];
        r38[r9] = null;
        r29[r7] = r29[r9];
        r29[r9] = 4611686018427387903L;
        r1 = (int) ((r8[r9] >> 32) & 4294967295L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x01e3, code lost:
    
        if (r1 == (-1)) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01e5, code lost:
    
        r8[r1] = (r8[r1] & (-4294967296L)) | r7;
        r8[r9] = (4294967295L & r8[r9]) | (-4294967296L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01fc, code lost:
    
        r8[r7] = (-1) | (r9 << 32);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x025a, code lost:
    
        r3[r3.length - 1] = r3[0];
        r9 = r9 + 1;
        r6 = r35;
        r5 = r36;
        r15 = r38;
        r10 = r41;
        r36 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01f5, code lost:
    
        r8[r9] = ((-1) << 32) | r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0204, code lost:
    
        r35 = r6;
        r41 = r10;
        r3[r1] = ((r13 & 127) << r2) | ((~(255 << r2)) & r14);
        r1 = r35[r7];
        r35[r7] = r35[r9];
        r35[r9] = r1;
        r1 = r38[r7];
        r38[r7] = r38[r9];
        r38[r9] = r1;
        r1 = r29[r7];
        r29[r7] = r29[r9];
        r29[r9] = r1;
        r1 = (int) ((r8[r9] >> 32) & 4294967295L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0237, code lost:
    
        if (r1 == (-1)) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0239, code lost:
    
        r12 = r7;
        r8[r1] = (r8[r1] & (-4294967296L)) | r12;
        r8[r9] = (4294967295L & r8[r9]) | (r12 << 32);
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0251, code lost:
    
        r8[r7] = (r1 << 32) | r9;
        r9 = r9 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x024a, code lost:
    
        r1 = r7;
        r8[r9] = r1 | (r1 << 32);
        r1 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0161, code lost:
    
        r3[r12] = ((r13 & 127) << r32) | (r3[r12] & (~(255 << r32)));
        r1 = r9;
        r8[r9] = r1 | (r1 << 32);
        r3[r3.length - 1] = r3[0];
        r9 = r9 + 1;
        r36 = r29;
        r15 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0141, code lost:
    
        r13 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x012d, code lost:
    
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0270, code lost:
    
        r41 = r10;
        r43.growthLimit = androidx.collection.ScatterMapKt.loadedCapacity(r43._capacity) - r43._count;
        r1 = r43.nodes;
        r2 = r1.length;
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0282, code lost:
    
        if (r9 >= r2) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0284, code lost:
    
        r6 = r1[r9];
        r3 = (int) ((r6 >> 31) & 2147483647L);
        r10 = (int) (r6 & 2147483647L);
        r6 = r6 & (-4611686018427387904L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0293, code lost:
    
        if (r3 != Integer.MAX_VALUE) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0295, code lost:
    
        r3 = Integer.MAX_VALUE;
        r14 = 4294967295L;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x02a5, code lost:
    
        r6 = (r6 | r3) << 31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x02a9, code lost:
    
        if (r10 != Integer.MAX_VALUE) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x02ab, code lost:
    
        r3 = Integer.MAX_VALUE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x02b3, code lost:
    
        r1[r9] = r6 | r3;
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x02af, code lost:
    
        r3 = (int) (r8[r10] & r14);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void put(java.lang.Object r44, java.lang.Object r45) {
        /*
            Method dump skipped, instructions count: 1170
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.SieveCache.put(java.lang.Object, java.lang.Object):void");
    }

    public final Object remove(Object obj) {
        Object removeValueAt;
        int findKeyIndex = findKeyIndex(obj);
        if (findKeyIndex < 0 || (removeValueAt = removeValueAt(findKeyIndex)) == null) {
            return null;
        }
        this._size -= ((Number) this.sizeOf.invoke(obj, removeValueAt)).intValue();
        this.onEntryRemoved.invoke(obj, removeValueAt, null, Boolean.FALSE);
        return removeValueAt;
    }

    public final Object removeValueAt(int i) {
        this._count--;
        long[] jArr = this.metadata;
        int i2 = this._capacity;
        int i3 = i >> 3;
        int i4 = (i & 7) << 3;
        long j = (jArr[i3] & (~(255 << i4))) | (254 << i4);
        jArr[i3] = j;
        jArr[(((i - 7) & i2) + (i2 & 7)) >> 3] = j;
        this.keys[i] = null;
        Object[] objArr = this.values;
        Object obj = objArr[i];
        objArr[i] = null;
        long[] jArr2 = this.nodes;
        long j2 = jArr2[i];
        int i5 = (int) ((j2 >> 31) & 2147483647L);
        int i6 = (int) (j2 & 2147483647L);
        if (i5 != Integer.MAX_VALUE) {
            jArr2[i5] = (jArr2[i5] & (-2147483648L)) | (i6 & 2147483647L);
        } else {
            this.head = i6;
        }
        if (i6 != Integer.MAX_VALUE) {
            jArr2[i6] = ((i5 & 2147483647L) << 31) | (jArr2[i6] & (-4611686016279904257L));
        } else {
            this.tail = i5;
        }
        if (this.hand == i) {
            this.hand = i5;
        }
        jArr2[i] = 4611686018427387903L;
        return obj;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SieveCache[maxSize=");
        sb.append(this._maxSize);
        sb.append(", size=");
        sb.append(this._size);
        sb.append(", capacity=");
        sb.append(this._capacity);
        sb.append(", count=");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, this._count, ']');
    }

    public final void trimToSize(int i) {
        while (this._size > i && this._count != 0) {
            long[] jArr = this.nodes;
            int i2 = this.hand;
            if (i2 == Integer.MAX_VALUE) {
                i2 = this.tail;
            }
            while (i2 != Integer.MAX_VALUE) {
                long j = jArr[i2];
                if (((int) ((j >> 62) & 1)) == 0) {
                    break;
                }
                int i3 = (int) (2147483647L & (j >> 31));
                jArr[i2] = 4611686018427387903L & j;
                i2 = i3 != Integer.MAX_VALUE ? i3 : this.tail;
            }
            int i4 = (int) (2147483647L & (jArr[i2] >> 31));
            if (i4 == Integer.MAX_VALUE) {
                i4 = Integer.MAX_VALUE;
            }
            this.hand = i4;
            if (i2 == Integer.MAX_VALUE) {
                return;
            }
            Object obj = this.keys[i2];
            Object removeValueAt = removeValueAt(i2);
            if (removeValueAt != null) {
                this._size -= ((Number) this.sizeOf.invoke(obj, removeValueAt)).intValue();
                this.onEntryRemoved.invoke(obj, removeValueAt, null, Boolean.TRUE);
            }
        }
    }
}
