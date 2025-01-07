package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import androidx.collection.internal.RuntimeHelpersKt;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableSet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutableScatterSet extends ScatterSet {
    public int growthLimit;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MutableSetWrapper implements KMutableSet, Set, KMappedMarker {
        public final /* synthetic */ MutableScatterSet this$0$1;

        public MutableSetWrapper() {
            this.this$0$1 = MutableScatterSet.this;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean add(Object obj) {
            return MutableScatterSet.this.add(obj);
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean addAll(Collection collection) {
            MutableScatterSet mutableScatterSet = MutableScatterSet.this;
            int i = mutableScatterSet._size;
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                mutableScatterSet.plusAssign(it.next());
            }
            return i != mutableScatterSet._size;
        }

        @Override // java.util.Set, java.util.Collection
        public final void clear() {
            MutableScatterSet.this.clear();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean contains(Object obj) {
            return this.this$0$1.contains(obj);
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean containsAll(Collection collection) {
            MutableScatterSet mutableScatterSet = this.this$0$1;
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (!mutableScatterSet.contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean isEmpty() {
            return this.this$0$1.isEmpty();
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            return new MutableScatterSet$MutableSetWrapper$iterator$1(MutableScatterSet.this);
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean remove(Object obj) {
            return MutableScatterSet.this.remove(obj);
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean removeAll(Collection collection) {
            MutableScatterSet mutableScatterSet = MutableScatterSet.this;
            int i = mutableScatterSet._size;
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                mutableScatterSet.minusAssign(it.next());
            }
            return i != mutableScatterSet._size;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean retainAll(Collection collection) {
            MutableScatterSet mutableScatterSet = MutableScatterSet.this;
            Object[] objArr = mutableScatterSet.elements;
            int i = mutableScatterSet._size;
            long[] jArr = mutableScatterSet.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i2 = 0;
                while (true) {
                    long j = jArr[i2];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i3 = 8 - ((~(i2 - length)) >>> 31);
                        for (int i4 = 0; i4 < i3; i4++) {
                            if ((255 & j) < 128) {
                                int i5 = (i2 << 3) + i4;
                                if (!CollectionsKt.contains(collection, objArr[i5])) {
                                    mutableScatterSet.removeElementAt(i5);
                                }
                            }
                            j >>= 8;
                        }
                        if (i3 != 8) {
                            break;
                        }
                    }
                    if (i2 == length) {
                        break;
                    }
                    i2++;
                }
            }
            return i != mutableScatterSet._size;
        }

        @Override // java.util.Set, java.util.Collection
        public final int size() {
            return this.this$0$1._size;
        }

        @Override // java.util.Set, java.util.Collection
        public final Object[] toArray() {
            return CollectionToArray.toArray(this);
        }

        @Override // java.util.Set, java.util.Collection
        public final Object[] toArray(Object[] objArr) {
            return CollectionToArray.toArray(this, objArr);
        }
    }

    public MutableScatterSet(int i) {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.elements = ContainerHelpersKt.EMPTY_OBJECTS;
        if (i >= 0) {
            initializeStorage(ScatterMapKt.unloadedCapacity(i));
        } else {
            RuntimeHelpersKt.throwIllegalArgumentException("Capacity must be a positive value.");
            throw null;
        }
    }

    public final boolean add(Object obj) {
        int i = this._size;
        this.elements[findAbsoluteInsertIndex(obj)] = obj;
        return this._size != i;
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
        Arrays.fill(this.elements, 0, this._capacity, (Object) null);
        this.growthLimit = ScatterMapKt.loadedCapacity(this._capacity) - this._size;
    }

    public final int findAbsoluteInsertIndex(Object obj) {
        long j;
        int i;
        long j2;
        int hashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i2 = hashCode ^ (hashCode << 16);
        int i3 = i2 >>> 7;
        int i4 = i2 & 127;
        int i5 = this._capacity;
        int i6 = i3 & i5;
        int i7 = 0;
        while (true) {
            long[] jArr = this.metadata;
            int i8 = i6 >> 3;
            int i9 = (i6 & 7) << 3;
            long j3 = ((jArr[i8 + 1] << (64 - i9)) & ((-i9) >> 63)) | (jArr[i8] >>> i9);
            long j4 = i4;
            int i10 = i4;
            long j5 = j3 ^ (j4 * 72340172838076673L);
            long j6 = -9187201950435737472L;
            for (long j7 = (~j5) & (j5 - 72340172838076673L) & (-9187201950435737472L); j7 != 0; j7 &= j7 - 1) {
                int numberOfTrailingZeros = (i6 + (Long.numberOfTrailingZeros(j7) >> 3)) & i5;
                if (Intrinsics.areEqual(this.elements[numberOfTrailingZeros], obj)) {
                    return numberOfTrailingZeros;
                }
            }
            if ((((~j3) << 6) & j3 & (-9187201950435737472L)) != 0) {
                int findFirstAvailableSlot = findFirstAvailableSlot(i3);
                long j8 = 255;
                if (this.growthLimit != 0 || ((this.metadata[findFirstAvailableSlot >> 3] >> ((findFirstAvailableSlot & 7) << 3)) & 255) == 254) {
                    j = j4;
                    i = 0;
                } else {
                    int i11 = this._capacity;
                    if (i11 <= 8 || Long.compareUnsigned(this._size * 32, i11 * 25) > 0) {
                        j = j4;
                        i = 0;
                        int nextCapacity = ScatterMapKt.nextCapacity(this._capacity);
                        long[] jArr2 = this.metadata;
                        Object[] objArr = this.elements;
                        int i12 = this._capacity;
                        initializeStorage(nextCapacity);
                        long[] jArr3 = this.metadata;
                        Object[] objArr2 = this.elements;
                        int i13 = this._capacity;
                        int i14 = 0;
                        while (i14 < i12) {
                            if (((jArr2[i14 >> 3] >> ((i14 & 7) << 3)) & j8) < 128) {
                                Object obj2 = objArr[i14];
                                int hashCode2 = (obj2 != null ? obj2.hashCode() : 0) * (-862048943);
                                int i15 = hashCode2 ^ (hashCode2 << 16);
                                int findFirstAvailableSlot2 = findFirstAvailableSlot(i15 >>> 7);
                                long j9 = i15 & 127;
                                int i16 = findFirstAvailableSlot2 >> 3;
                                int i17 = (findFirstAvailableSlot2 & 7) << 3;
                                long j10 = (j9 << i17) | (jArr3[i16] & (~(255 << i17)));
                                jArr3[i16] = j10;
                                jArr3[(((findFirstAvailableSlot2 - 7) & i13) + (i13 & 7)) >> 3] = j10;
                                objArr2[findFirstAvailableSlot2] = obj2;
                            }
                            i14++;
                            j8 = 255;
                        }
                    } else {
                        long[] jArr4 = this.metadata;
                        int i18 = this._capacity;
                        Object[] objArr3 = this.elements;
                        int i19 = (i18 + 7) >> 3;
                        int i20 = 0;
                        while (i20 < i19) {
                            long j11 = jArr4[i20] & j6;
                            jArr4[i20] = (-72340172838076674L) & ((~j11) + (j11 >>> 7));
                            i20++;
                            j6 = -9187201950435737472L;
                        }
                        int length = jArr4.length;
                        int i21 = length - 1;
                        int i22 = length - 2;
                        jArr4[i22] = (jArr4[i22] & 72057594037927935L) | (-72057594037927936L);
                        jArr4[i21] = jArr4[0];
                        int i23 = 0;
                        while (i23 != i18) {
                            int i24 = i23 >> 3;
                            int i25 = (i23 & 7) << 3;
                            long j12 = (jArr4[i24] >> i25) & 255;
                            if (j12 != 128 && j12 == 254) {
                                Object obj3 = objArr3[i23];
                                int hashCode3 = (obj3 != null ? obj3.hashCode() : 0) * (-862048943);
                                int i26 = (hashCode3 ^ (hashCode3 << 16)) >>> 7;
                                int findFirstAvailableSlot3 = findFirstAvailableSlot(i26);
                                int i27 = i26 & i18;
                                if (((findFirstAvailableSlot3 - i27) & i18) / 8 == ((i23 - i27) & i18) / 8) {
                                    jArr4[i24] = (jArr4[i24] & (~(255 << i25))) | ((r13 & 127) << i25);
                                    jArr4[jArr4.length - 1] = (jArr4[0] & 72057594037927935L) | Long.MIN_VALUE;
                                    i23++;
                                } else {
                                    int i28 = findFirstAvailableSlot3 >> 3;
                                    long j13 = jArr4[i28];
                                    int i29 = (findFirstAvailableSlot3 & 7) << 3;
                                    if (((j13 >> i29) & 255) == 128) {
                                        j2 = j4;
                                        jArr4[i28] = (j13 & (~(255 << i29))) | ((r13 & 127) << i29);
                                        jArr4[i24] = (jArr4[i24] & (~(255 << i25))) | (128 << i25);
                                        objArr3[findFirstAvailableSlot3] = objArr3[i23];
                                        objArr3[i23] = null;
                                    } else {
                                        j2 = j4;
                                        jArr4[i28] = ((r13 & 127) << i29) | (j13 & (~(255 << i29)));
                                        Object obj4 = objArr3[findFirstAvailableSlot3];
                                        objArr3[findFirstAvailableSlot3] = objArr3[i23];
                                        objArr3[i23] = obj4;
                                        i23--;
                                    }
                                    jArr4[jArr4.length - 1] = (jArr4[0] & 72057594037927935L) | Long.MIN_VALUE;
                                    i23++;
                                    j4 = j2;
                                }
                            } else {
                                i23++;
                            }
                        }
                        j = j4;
                        i = 0;
                        this.growthLimit = ScatterMapKt.loadedCapacity(this._capacity) - this._size;
                    }
                    findFirstAvailableSlot = findFirstAvailableSlot(i3);
                }
                this._size++;
                int i30 = this.growthLimit;
                long[] jArr5 = this.metadata;
                int i31 = findFirstAvailableSlot >> 3;
                long j14 = jArr5[i31];
                int i32 = (findFirstAvailableSlot & 7) << 3;
                this.growthLimit = i30 - (((j14 >> i32) & 255) != 128 ? i : 1);
                int i33 = this._capacity;
                long j15 = ((~(255 << i32)) & j14) | (j << i32);
                jArr5[i31] = j15;
                jArr5[(((findFirstAvailableSlot - 7) & i33) + (i33 & 7)) >> 3] = j15;
                return findFirstAvailableSlot;
            }
            i7 += 8;
            i6 = (i6 + i7) & i5;
            i4 = i10;
        }
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
        this.elements = max == 0 ? ContainerHelpersKt.EMPTY_OBJECTS : new Object[max];
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0068, code lost:
    
        if (((r4 & ((~r4) << 6)) & (-9187201950435737472L)) == 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006a, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void minusAssign(java.lang.Object r14) {
        /*
            r13 = this;
            r0 = 0
            if (r14 == 0) goto L8
            int r1 = r14.hashCode()
            goto L9
        L8:
            r1 = r0
        L9:
            r2 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r1 = r1 * r2
            int r2 = r1 << 16
            r1 = r1 ^ r2
            r2 = r1 & 127(0x7f, float:1.78E-43)
            int r3 = r13._capacity
            int r1 = r1 >>> 7
        L16:
            r1 = r1 & r3
            long[] r4 = r13.metadata
            int r5 = r1 >> 3
            r6 = r1 & 7
            int r6 = r6 << 3
            r7 = r4[r5]
            long r7 = r7 >>> r6
            int r5 = r5 + 1
            r4 = r4[r5]
            int r9 = 64 - r6
            long r4 = r4 << r9
            long r9 = (long) r6
            long r9 = -r9
            r6 = 63
            long r9 = r9 >> r6
            long r4 = r4 & r9
            long r4 = r4 | r7
            long r6 = (long) r2
            r8 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r6 = r6 * r8
            long r6 = r6 ^ r4
            long r8 = r6 - r8
            long r6 = ~r6
            long r6 = r6 & r8
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
        L42:
            r10 = 0
            int r12 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r12 == 0) goto L61
            int r10 = java.lang.Long.numberOfTrailingZeros(r6)
            int r10 = r10 >> 3
            int r10 = r10 + r1
            r10 = r10 & r3
            java.lang.Object[] r11 = r13.elements
            r11 = r11[r10]
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r14)
            if (r11 == 0) goto L5b
            goto L6b
        L5b:
            r10 = 1
            long r10 = r6 - r10
            long r6 = r6 & r10
            goto L42
        L61:
            long r6 = ~r4
            r12 = 6
            long r6 = r6 << r12
            long r4 = r4 & r6
            long r4 = r4 & r8
            int r4 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r4 == 0) goto L71
            r10 = -1
        L6b:
            if (r10 < 0) goto L70
            r13.removeElementAt(r10)
        L70:
            return
        L71:
            int r0 = r0 + 8
            int r1 = r1 + r0
            goto L16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableScatterSet.minusAssign(java.lang.Object):void");
    }

    public final void plusAssign(Object obj) {
        this.elements[findAbsoluteInsertIndex(obj)] = obj;
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableScatterSet.remove(java.lang.Object):boolean");
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
    }

    public final void plusAssign(ScatterSet scatterSet) {
        Object[] objArr = scatterSet.elements;
        long[] jArr = scatterSet.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        plusAssign(objArr[(i << 3) + i3]);
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public /* synthetic */ MutableScatterSet() {
        this(6);
    }
}
