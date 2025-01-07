package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import androidx.collection.internal.RuntimeHelpersKt;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutableOrderedScatterSet extends OrderedScatterSet {
    public int growthLimit;

    public MutableOrderedScatterSet(int i) {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.elements = ContainerHelpersKt.EMPTY_OBJECTS;
        this.nodes = SieveCacheKt.EmptyNodes;
        this.head = Integer.MAX_VALUE;
        this.tail = Integer.MAX_VALUE;
        if (i >= 0) {
            initializeStorage(ScatterMapKt.unloadedCapacity(i));
        } else {
            RuntimeHelpersKt.throwIllegalArgumentException("Capacity must be a positive value.");
            throw null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x028e, code lost:
    
        r12 = r3[r11];
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0290, code lost:
    
        if (r12 == null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0292, code lost:
    
        r13 = r12.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x029c, code lost:
    
        r13 = r13 * (-862048943);
        r13 = r13 ^ (r13 << 16);
        r8 = r1.findFirstAvailableSlot(r13 >>> 7);
        r18 = r2;
        r19 = r3;
        r2 = r13 & 127;
        r13 = r8 >> 3;
        r20 = (r8 & 7) << 3;
        r2 = (r2 << r20) | (r0[r13] & (~(255 << r20)));
        r0[r13] = r2;
        r0[(((r8 - 7) & r10) + (r10 & 7)) >> 3] = r2;
        r7[r8] = r12;
        r9[r8] = r4[r11];
        r6[r11] = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x02dc, code lost:
    
        r11 = r11 + 1;
        r2 = r18;
        r3 = r19;
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x029a, code lost:
    
        r13 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x02d7, code lost:
    
        r18 = r2;
        r19 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x02e3, code lost:
    
        r3 = true;
        r1.fixupNodes(r6);
        r2 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x00a3, code lost:
    
        r1 = r0;
        r28 = r6;
        r30 = r13;
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x02f0, code lost:
    
        r19 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0084, code lost:
    
        if (((r2 & ((~r2) << 6)) & (-9187201950435737472L)) == 0) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0086, code lost:
    
        r2 = r0.findFirstAvailableSlot(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x008e, code lost:
    
        if (r0.growthLimit != 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a1, code lost:
    
        if (((r0.metadata[r2 >> 3] >> ((r2 & 7) << 3)) & 255) != 254) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ab, code lost:
    
        r2 = r0._capacity;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ad, code lost:
    
        if (r2 <= 8) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00bf, code lost:
    
        if (java.lang.Long.compareUnsigned(r0._size * 32, r2 * 25) > 0) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00c1, code lost:
    
        r2 = r0.metadata;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c3, code lost:
    
        if (r2 != null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c5, code lost:
    
        r1 = r0;
        r28 = r6;
        r16 = r9;
        r30 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0250, code lost:
    
        r2 = r16;
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x02e9, code lost:
    
        r19 = r1.findFirstAvailableSlot(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x02f2, code lost:
    
        r1._size += r3 ? 1 : 0;
        r0 = r1.growthLimit;
        r2 = r1.metadata;
        r4 = r19 >> 3;
        r5 = r2[r4];
        r7 = (r19 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x030d, code lost:
    
        if (((r5 >> r7) & 255) != 128) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x030f, code lost:
    
        r8 = r3 ? 1 : 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0312, code lost:
    
        r1.growthLimit = r0 - r8;
        r0 = r1._capacity;
        r5 = (r5 & (~(255 << r7))) | (r30 << r7);
        r2[r4] = r5;
        r2[(((r19 - 7) & r0) + (r0 & 7)) >> 3] = r5;
        r0 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0311, code lost:
    
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00cf, code lost:
    
        r4 = r0._capacity;
        r5 = r0.elements;
        r7 = r0.nodes;
        r8 = new int[r4];
        r12 = 7;
        r3 = (r4 + 7) >> 3;
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00dd, code lost:
    
        if (r10 >= r3) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00df, code lost:
    
        r30 = r13;
        r13 = r2[r10] & (-9187201950435737472L);
        r2[r10] = ((~r13) + (r13 >>> r12)) & (-72340172838076674L);
        r10 = r10 + 1;
        r7 = r7;
        r6 = r6;
        r13 = r30;
        r12 = 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00fc, code lost:
    
        r28 = r6;
        r11 = r7;
        r30 = r13;
        r3 = r2.length;
        r7 = r3 - 1;
        r3 = r3 - 2;
        r2[r3] = (r2[r3] & 72057594037927935L) | (-72057594037927936L);
        r2[r7] = r2[0];
        r3 = 0;
        r6 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x011b, code lost:
    
        if (r3 == r4) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x011d, code lost:
    
        r7 = r3 >> 3;
        r10 = (r3 & 7) << 3;
        r12 = (r2[r7] >> r10) & 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x012e, code lost:
    
        if (r12 != 128) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x013c, code lost:
    
        if (r12 == 254) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0140, code lost:
    
        r12 = r5[r3];
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0142, code lost:
    
        if (r12 == null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0144, code lost:
    
        r12 = r12.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x014e, code lost:
    
        r12 = r12 * (-862048943);
        r14 = (r12 ^ (r12 << 16)) >>> 7;
        r13 = r0.findFirstAvailableSlot(r14);
        r14 = r14 & r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0166, code lost:
    
        if ((((r13 - r14) & r4) / 8) != (((r3 - r14) & r4) / 8)) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0187, code lost:
    
        r0 = r13 >> 3;
        r14 = r2[r0];
        r1 = (r13 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x019a, code lost:
    
        if (((r14 >> r1) & 255) != 128) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x019c, code lost:
    
        r29 = r8;
        r16 = r9;
        r32 = r11;
        r2[r0] = ((r12 & 127) << r1) | ((~(255 << r1)) & r14);
        r2[r7] = (r2[r7] & (~(255 << r10))) | (128 << r10);
        r5[r13] = r5[r3];
        r5[r3] = null;
        r32[r13] = r32[r3];
        r32[r3] = 4611686018427387903L;
        r29[r3] = r13;
        r6 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0225, code lost:
    
        r2[r2.length - 1] = r2[0];
        r3 = r3 + 1;
        r0 = r35;
        r9 = r16;
        r8 = r29;
        r11 = r32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x01d1, code lost:
    
        r29 = r8;
        r16 = r9;
        r32 = r11;
        r2[r0] = ((r12 & 127) << r1) | ((~(255 << r1)) & r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01e5, code lost:
    
        if (r6 != (-1)) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01e7, code lost:
    
        r1 = r3 + 1;
        r0 = androidx.collection.ScatterMapKt.EmptyGroup;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01ec, code lost:
    
        if (r1 >= r4) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01ff, code lost:
    
        if (((r2[r1 >> 3] >> ((r1 & 7) << 3)) & 255) != 128) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0203, code lost:
    
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0201, code lost:
    
        r6 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0206, code lost:
    
        r6 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0207, code lost:
    
        r5[r6] = r5[r13];
        r5[r13] = r5[r3];
        r5[r3] = r5[r6];
        r32[r6] = r32[r13];
        r32[r13] = r32[r3];
        r32[r3] = r32[r6];
        r29[r3] = r13;
        r29[r13] = r3;
        r3 = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0168, code lost:
    
        r2[r7] = ((~(255 << r10)) & r2[r7]) | ((r12 & 127) << r10);
        r8[r3] = r3;
        r2[r2.length - 1] = r2[0];
        r3 = r3 + 1;
        r0 = r35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x014c, code lost:
    
        r12 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x013e, code lost:
    
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0130, code lost:
    
        r6 = r3;
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x023a, code lost:
    
        r1 = r0;
        r16 = r9;
        r1.growthLimit = androidx.collection.ScatterMapKt.loadedCapacity(r1._capacity) - r1._size;
        r1.fixupNodes(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0255, code lost:
    
        r1 = r0;
        r28 = r6;
        r30 = r13;
        r8 = 0;
        r0 = androidx.collection.ScatterMapKt.nextCapacity(r1._capacity);
        r2 = r1.metadata;
        r3 = r1.elements;
        r4 = r1.nodes;
        r5 = r1._capacity;
        r6 = new int[r5];
        r1.initializeStorage(r0);
        r0 = r1.metadata;
        r7 = r1.elements;
        r9 = r1.nodes;
        r10 = r1._capacity;
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0279, code lost:
    
        if (r11 >= r5) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x028c, code lost:
    
        if (((r2[r11 >> 3] >> ((r11 & 7) << 3)) & 255) >= 128) goto L74;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean add(java.lang.Object r36) {
        /*
            Method dump skipped, instructions count: 893
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableOrderedScatterSet.add(java.lang.Object):boolean");
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

    public final void fixupNodes(int[] iArr) {
        long[] jArr = this.nodes;
        int length = jArr.length;
        int i = 0;
        while (true) {
            int i2 = Integer.MAX_VALUE;
            if (i >= length) {
                break;
            }
            long j = jArr[i];
            int i3 = (int) (j & 2147483647L);
            long j2 = ((j & (-4611686018427387904L)) | (((int) ((j >> 31) & 2147483647L)) == Integer.MAX_VALUE ? Integer.MAX_VALUE : iArr[r7])) << 31;
            if (i3 != Integer.MAX_VALUE) {
                i2 = iArr[i3];
            }
            jArr[i] = j2 | i2;
            i++;
        }
        int i4 = this.head;
        if (i4 != Integer.MAX_VALUE) {
            this.head = iArr[i4];
        }
        int i5 = this.tail;
        if (i5 != Integer.MAX_VALUE) {
            this.tail = iArr[i5];
        }
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
            jArr = jArr3;
        }
        this.metadata = jArr;
        int i3 = max >> 3;
        long j = 255 << ((max & 7) << 3);
        jArr[i3] = (jArr[i3] & (~j)) | j;
        this.growthLimit = ScatterMapKt.loadedCapacity(this._capacity) - this._size;
        this.elements = max == 0 ? ContainerHelpersKt.EMPTY_OBJECTS : new Object[max];
        if (max == 0) {
            jArr2 = SieveCacheKt.EmptyNodes;
        } else {
            long[] jArr4 = new long[max];
            Arrays.fill(jArr4, 0, max, 4611686018427387903L);
            jArr2 = jArr4;
        }
        this.nodes = jArr2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x006d, code lost:
    
        if (((r7 & ((~r7) << 6)) & (-9187201950435737472L)) == 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006f, code lost:
    
        r11 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean remove(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 0
            if (r1 == 0) goto Lc
            int r3 = r18.hashCode()
            goto Ld
        Lc:
            r3 = r2
        Ld:
            r4 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r3 = r3 * r4
            int r4 = r3 << 16
            r3 = r3 ^ r4
            r4 = r3 & 127(0x7f, float:1.78E-43)
            int r5 = r0._capacity
            int r3 = r3 >>> 7
            r3 = r3 & r5
            r6 = r2
        L1c:
            long[] r7 = r0.metadata
            int r8 = r3 >> 3
            r9 = r3 & 7
            int r9 = r9 << 3
            r10 = r7[r8]
            long r10 = r10 >>> r9
            r12 = 1
            int r8 = r8 + r12
            r7 = r7[r8]
            int r13 = 64 - r9
            long r7 = r7 << r13
            long r13 = (long) r9
            long r13 = -r13
            r9 = 63
            long r13 = r13 >> r9
            long r7 = r7 & r13
            long r7 = r7 | r10
            long r9 = (long) r4
            r13 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r9 = r9 * r13
            long r9 = r9 ^ r7
            long r13 = r9 - r13
            long r9 = ~r9
            long r9 = r9 & r13
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r9 = r9 & r13
        L47:
            r15 = 0
            int r11 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r11 == 0) goto L66
            int r11 = java.lang.Long.numberOfTrailingZeros(r9)
            int r11 = r11 >> 3
            int r11 = r11 + r3
            r11 = r11 & r5
            java.lang.Object[] r15 = r0.elements
            r15 = r15[r11]
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual(r15, r1)
            if (r15 == 0) goto L60
            goto L70
        L60:
            r15 = 1
            long r15 = r9 - r15
            long r9 = r9 & r15
            goto L47
        L66:
            long r9 = ~r7
            r11 = 6
            long r9 = r9 << r11
            long r7 = r7 & r9
            long r7 = r7 & r13
            int r7 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r7 == 0) goto L79
            r11 = -1
        L70:
            if (r11 < 0) goto L73
            r2 = r12
        L73:
            if (r2 == 0) goto L78
            r0.removeElementAt(r11)
        L78:
            return r2
        L79:
            int r6 = r6 + 8
            int r3 = r3 + r6
            r3 = r3 & r5
            goto L1c
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableOrderedScatterSet.remove(java.lang.Object):boolean");
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
        this.elements[i] = null;
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
        jArr2[i] = 4611686018427387903L;
    }
}
