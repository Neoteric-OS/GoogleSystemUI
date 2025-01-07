package androidx.compose.ui.layout;

import androidx.collection.MutableOrderedScatterSet;
import androidx.collection.MutableOrderedScatterSet$MutableSetWrapper$iterator$1;
import androidx.collection.OrderedScatterSetKt;
import androidx.collection.ScatterMapKt;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface SubcomposeSlotReusePolicy {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SlotIdsSet implements Collection, KMappedMarker {
        public final MutableOrderedScatterSet set;

        public SlotIdsSet() {
            int i = OrderedScatterSetKt.$r8$clinit;
            this.set = new MutableOrderedScatterSet(6);
        }

        @Override // java.util.Collection
        public final boolean add(Object obj) {
            return this.set.add(obj);
        }

        @Override // java.util.Collection
        public final boolean addAll(Collection collection) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Collection
        public final void clear() {
            MutableOrderedScatterSet mutableOrderedScatterSet = this.set;
            mutableOrderedScatterSet._size = 0;
            long[] jArr = mutableOrderedScatterSet.metadata;
            if (jArr != ScatterMapKt.EmptyGroup) {
                Arrays.fill(jArr, 0, jArr.length, -9187201950435737472L);
                long[] jArr2 = mutableOrderedScatterSet.metadata;
                int i = mutableOrderedScatterSet._capacity;
                int i2 = i >> 3;
                long j = 255 << ((i & 7) << 3);
                jArr2[i2] = (jArr2[i2] & (~j)) | j;
            }
            Arrays.fill(mutableOrderedScatterSet.elements, 0, mutableOrderedScatterSet._capacity, (Object) null);
            Arrays.fill(r0, 0, mutableOrderedScatterSet.nodes.length, 4611686018427387903L);
            mutableOrderedScatterSet.head = Integer.MAX_VALUE;
            mutableOrderedScatterSet.tail = Integer.MAX_VALUE;
            mutableOrderedScatterSet.growthLimit = ScatterMapKt.loadedCapacity(mutableOrderedScatterSet._capacity) - mutableOrderedScatterSet._size;
        }

        @Override // java.util.Collection
        public final boolean contains(Object obj) {
            return this.set.contains(obj);
        }

        @Override // java.util.Collection
        public final boolean containsAll(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (!this.set.contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Collection
        public final boolean isEmpty() {
            return this.set._size == 0;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            MutableOrderedScatterSet mutableOrderedScatterSet = this.set;
            mutableOrderedScatterSet.getClass();
            return new MutableOrderedScatterSet$MutableSetWrapper$iterator$1(mutableOrderedScatterSet);
        }

        @Override // java.util.Collection
        public final boolean remove(Object obj) {
            return this.set.remove(obj);
        }

        @Override // java.util.Collection
        public final boolean removeAll(Collection collection) {
            return this.set.remove(collection);
        }

        @Override // java.util.Collection
        public final boolean removeIf(Predicate predicate) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Collection
        public final boolean retainAll(Collection collection) {
            MutableOrderedScatterSet mutableOrderedScatterSet = this.set;
            Object[] objArr = mutableOrderedScatterSet.elements;
            int i = mutableOrderedScatterSet._size;
            long[] jArr = mutableOrderedScatterSet.metadata;
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
                                    mutableOrderedScatterSet.removeElementAt(i5);
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
            return i != mutableOrderedScatterSet._size;
        }

        @Override // java.util.Collection
        public final int size() {
            return this.set._size;
        }

        @Override // java.util.Collection
        public final Object[] toArray() {
            return CollectionToArray.toArray(this);
        }

        @Override // java.util.Collection
        public final Object[] toArray(Object[] objArr) {
            return CollectionToArray.toArray(this, objArr);
        }
    }

    boolean areCompatible(Object obj, Object obj2);

    void getSlotsToRetain(SlotIdsSet slotIdsSet);
}
