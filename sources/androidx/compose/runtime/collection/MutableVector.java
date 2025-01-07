package androidx.compose.runtime.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutableVector implements RandomAccess {
    public Object[] content;
    public List list;
    public int size = 0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class MutableVectorList implements List, KMutableList {
        public final MutableVector vector;

        public MutableVectorList(MutableVector mutableVector) {
            this.vector = mutableVector;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean add(Object obj) {
            this.vector.add(obj);
            return true;
        }

        @Override // java.util.List
        public final boolean addAll(int i, Collection collection) {
            return this.vector.addAll(i, collection);
        }

        @Override // java.util.List, java.util.Collection
        public final void clear() {
            this.vector.clear();
        }

        @Override // java.util.List, java.util.Collection
        public final boolean contains(Object obj) {
            return this.vector.contains(obj);
        }

        @Override // java.util.List, java.util.Collection
        public final boolean containsAll(Collection collection) {
            MutableVector mutableVector = this.vector;
            mutableVector.getClass();
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (!mutableVector.contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.List
        public final Object get(int i) {
            MutableVectorKt.checkIndex(i, this);
            return this.vector.content[i];
        }

        @Override // java.util.List
        public final int indexOf(Object obj) {
            return this.vector.indexOf(obj);
        }

        @Override // java.util.List, java.util.Collection
        public final boolean isEmpty() {
            return this.vector.size == 0;
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            return new VectorListIterator(0, this);
        }

        @Override // java.util.List
        public final int lastIndexOf(Object obj) {
            MutableVector mutableVector = this.vector;
            int i = mutableVector.size;
            if (i > 0) {
                int i2 = i - 1;
                Object[] objArr = mutableVector.content;
                while (!Intrinsics.areEqual(obj, objArr[i2])) {
                    i2--;
                    if (i2 < 0) {
                    }
                }
                return i2;
            }
            return -1;
        }

        @Override // java.util.List
        public final ListIterator listIterator() {
            return new VectorListIterator(0, this);
        }

        @Override // java.util.List, java.util.Collection
        public final boolean remove(Object obj) {
            return this.vector.remove(obj);
        }

        @Override // java.util.List, java.util.Collection
        public final boolean removeAll(Collection collection) {
            MutableVector mutableVector = this.vector;
            mutableVector.getClass();
            if (collection.isEmpty()) {
                return false;
            }
            int i = mutableVector.size;
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                mutableVector.remove(it.next());
            }
            return i != mutableVector.size;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean retainAll(Collection collection) {
            MutableVector mutableVector = this.vector;
            int i = mutableVector.size;
            for (int i2 = i - 1; -1 < i2; i2--) {
                if (!collection.contains(mutableVector.content[i2])) {
                    mutableVector.removeAt(i2);
                }
            }
            return i != mutableVector.size;
        }

        @Override // java.util.List
        public final Object set(int i, Object obj) {
            MutableVectorKt.checkIndex(i, this);
            Object[] objArr = this.vector.content;
            Object obj2 = objArr[i];
            objArr[i] = obj;
            return obj2;
        }

        @Override // java.util.List, java.util.Collection
        public final int size() {
            return this.vector.size;
        }

        @Override // java.util.List
        public final List subList(int i, int i2) {
            MutableVectorKt.checkSubIndex(i, i2, this);
            return new SubList(i, i2, this);
        }

        @Override // java.util.List, java.util.Collection
        public final Object[] toArray() {
            return CollectionToArray.toArray(this);
        }

        @Override // java.util.List
        public final void add(int i, Object obj) {
            this.vector.add(i, obj);
        }

        @Override // java.util.List, java.util.Collection
        public final boolean addAll(Collection collection) {
            MutableVector mutableVector = this.vector;
            return mutableVector.addAll(mutableVector.size, collection);
        }

        @Override // java.util.List
        public final ListIterator listIterator(int i) {
            return new VectorListIterator(i, this);
        }

        @Override // java.util.List
        public final Object remove(int i) {
            MutableVectorKt.checkIndex(i, this);
            return this.vector.removeAt(i);
        }

        @Override // java.util.List, java.util.Collection
        public final Object[] toArray(Object[] objArr) {
            return CollectionToArray.toArray(this, objArr);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class SubList implements List, KMutableList {
        public int end;
        public final List list;
        public final int start;

        public SubList(int i, int i2, List list) {
            this.list = list;
            this.start = i;
            this.end = i2;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean add(Object obj) {
            List list = this.list;
            int i = this.end;
            this.end = i + 1;
            list.add(i, obj);
            return true;
        }

        @Override // java.util.List
        public final boolean addAll(int i, Collection collection) {
            this.list.addAll(i + this.start, collection);
            int size = collection.size();
            this.end += size;
            return size > 0;
        }

        @Override // java.util.List, java.util.Collection
        public final void clear() {
            int i = this.end - 1;
            int i2 = this.start;
            if (i2 <= i) {
                while (true) {
                    this.list.remove(i);
                    if (i == i2) {
                        break;
                    } else {
                        i--;
                    }
                }
            }
            this.end = this.start;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean contains(Object obj) {
            int i = this.end;
            for (int i2 = this.start; i2 < i; i2++) {
                if (Intrinsics.areEqual(this.list.get(i2), obj)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean containsAll(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.List
        public final Object get(int i) {
            MutableVectorKt.checkIndex(i, this);
            return this.list.get(i + this.start);
        }

        @Override // java.util.List
        public final int indexOf(Object obj) {
            int i = this.end;
            for (int i2 = this.start; i2 < i; i2++) {
                if (Intrinsics.areEqual(this.list.get(i2), obj)) {
                    return i2 - this.start;
                }
            }
            return -1;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean isEmpty() {
            return this.end == this.start;
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            return new VectorListIterator(0, this);
        }

        @Override // java.util.List
        public final int lastIndexOf(Object obj) {
            int i = this.end - 1;
            int i2 = this.start;
            if (i2 > i) {
                return -1;
            }
            while (!Intrinsics.areEqual(this.list.get(i), obj)) {
                if (i == i2) {
                    return -1;
                }
                i--;
            }
            return i - this.start;
        }

        @Override // java.util.List
        public final ListIterator listIterator() {
            return new VectorListIterator(0, this);
        }

        @Override // java.util.List, java.util.Collection
        public final boolean remove(Object obj) {
            int i = this.end;
            for (int i2 = this.start; i2 < i; i2++) {
                if (Intrinsics.areEqual(this.list.get(i2), obj)) {
                    this.list.remove(i2);
                    this.end--;
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean removeAll(Collection collection) {
            int i = this.end;
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                remove(it.next());
            }
            return i != this.end;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean retainAll(Collection collection) {
            int i = this.end;
            int i2 = i - 1;
            int i3 = this.start;
            if (i3 <= i2) {
                while (true) {
                    if (!collection.contains(this.list.get(i2))) {
                        this.list.remove(i2);
                        this.end--;
                    }
                    if (i2 == i3) {
                        break;
                    }
                    i2--;
                }
            }
            return i != this.end;
        }

        @Override // java.util.List
        public final Object set(int i, Object obj) {
            MutableVectorKt.checkIndex(i, this);
            return this.list.set(i + this.start, obj);
        }

        @Override // java.util.List, java.util.Collection
        public final int size() {
            return this.end - this.start;
        }

        @Override // java.util.List
        public final List subList(int i, int i2) {
            MutableVectorKt.checkSubIndex(i, i2, this);
            return new SubList(i, i2, this);
        }

        @Override // java.util.List, java.util.Collection
        public final Object[] toArray() {
            return CollectionToArray.toArray(this);
        }

        @Override // java.util.List
        public final void add(int i, Object obj) {
            this.list.add(i + this.start, obj);
            this.end++;
        }

        @Override // java.util.List
        public final ListIterator listIterator(int i) {
            return new VectorListIterator(i, this);
        }

        @Override // java.util.List, java.util.Collection
        public final Object[] toArray(Object[] objArr) {
            return CollectionToArray.toArray(this, objArr);
        }

        @Override // java.util.List, java.util.Collection
        public final boolean addAll(Collection collection) {
            this.list.addAll(this.end, collection);
            int size = collection.size();
            this.end += size;
            return size > 0;
        }

        @Override // java.util.List
        public final Object remove(int i) {
            MutableVectorKt.checkIndex(i, this);
            this.end--;
            return this.list.remove(i + this.start);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class VectorListIterator implements ListIterator, KMappedMarker {
        public int index;
        public final List list;

        public VectorListIterator(int i, List list) {
            this.list = list;
            this.index = i;
        }

        @Override // java.util.ListIterator
        public final void add(Object obj) {
            this.list.add(this.index, obj);
            this.index++;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final boolean hasNext() {
            return this.index < this.list.size();
        }

        @Override // java.util.ListIterator
        public final boolean hasPrevious() {
            return this.index > 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final Object next() {
            List list = this.list;
            int i = this.index;
            this.index = i + 1;
            return list.get(i);
        }

        @Override // java.util.ListIterator
        public final int nextIndex() {
            return this.index;
        }

        @Override // java.util.ListIterator
        public final Object previous() {
            int i = this.index - 1;
            this.index = i;
            return this.list.get(i);
        }

        @Override // java.util.ListIterator
        public final int previousIndex() {
            return this.index - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final void remove() {
            int i = this.index - 1;
            this.index = i;
            this.list.remove(i);
        }

        @Override // java.util.ListIterator
        public final void set(Object obj) {
            this.list.set(this.index, obj);
        }
    }

    public MutableVector(Object[] objArr) {
        this.content = objArr;
    }

    public final void add(Object obj) {
        ensureCapacity(this.size + 1);
        Object[] objArr = this.content;
        int i = this.size;
        objArr[i] = obj;
        this.size = i + 1;
    }

    public final void addAll(int i, MutableVector mutableVector) {
        int i2 = mutableVector.size;
        if (i2 == 0) {
            return;
        }
        ensureCapacity(this.size + i2);
        Object[] objArr = this.content;
        int i3 = this.size;
        if (i != i3) {
            ArraysKt.copyInto(mutableVector.size + i, i, i3, objArr, objArr);
        }
        ArraysKt.copyInto(i, 0, mutableVector.size, mutableVector.content, objArr);
        this.size += mutableVector.size;
    }

    public final List asMutableList() {
        List list = this.list;
        if (list != null) {
            return list;
        }
        MutableVectorList mutableVectorList = new MutableVectorList(this);
        this.list = mutableVectorList;
        return mutableVectorList;
    }

    public final void clear() {
        Object[] objArr = this.content;
        int i = this.size;
        while (true) {
            i--;
            if (-1 >= i) {
                this.size = 0;
                return;
            }
            objArr[i] = null;
        }
    }

    public final boolean contains(Object obj) {
        int i = this.size - 1;
        if (i >= 0) {
            for (int i2 = 0; !Intrinsics.areEqual(this.content[i2], obj); i2++) {
                if (i2 != i) {
                }
            }
            return true;
        }
        return false;
    }

    public final void ensureCapacity(int i) {
        Object[] objArr = this.content;
        if (objArr.length < i) {
            this.content = Arrays.copyOf(objArr, Math.max(i, objArr.length * 2));
        }
    }

    public final int indexOf(Object obj) {
        int i = this.size;
        if (i <= 0) {
            return -1;
        }
        Object[] objArr = this.content;
        int i2 = 0;
        while (!Intrinsics.areEqual(obj, objArr[i2])) {
            i2++;
            if (i2 >= i) {
                return -1;
            }
        }
        return i2;
    }

    public final boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        removeAt(indexOf);
        return true;
    }

    public final Object removeAt(int i) {
        Object[] objArr = this.content;
        Object obj = objArr[i];
        int i2 = this.size;
        if (i != i2 - 1) {
            ArraysKt.copyInto(i, i + 1, i2, objArr, objArr);
        }
        int i3 = this.size - 1;
        this.size = i3;
        objArr[i3] = null;
        return obj;
    }

    public final void removeRange(int i, int i2) {
        if (i2 > i) {
            int i3 = this.size;
            if (i2 < i3) {
                Object[] objArr = this.content;
                ArraysKt.copyInto(i, i2, i3, objArr, objArr);
            }
            int i4 = this.size;
            int i5 = i4 - (i2 - i);
            int i6 = i4 - 1;
            if (i5 <= i6) {
                int i7 = i5;
                while (true) {
                    this.content[i7] = null;
                    if (i7 == i6) {
                        break;
                    } else {
                        i7++;
                    }
                }
            }
            this.size = i5;
        }
    }

    public final void sortWith(Comparator comparator) {
        Arrays.sort(this.content, 0, this.size, comparator);
    }

    public final void add(int i, Object obj) {
        ensureCapacity(this.size + 1);
        Object[] objArr = this.content;
        int i2 = this.size;
        if (i != i2) {
            ArraysKt.copyInto(i + 1, i, i2, objArr, objArr);
        }
        objArr[i] = obj;
        this.size++;
    }

    public final void addAll(int i, List list) {
        if (list.isEmpty()) {
            return;
        }
        ensureCapacity(list.size() + this.size);
        Object[] objArr = this.content;
        if (i != this.size) {
            ArraysKt.copyInto(list.size() + i, i, this.size, objArr, objArr);
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = list.get(i2);
        }
        this.size = list.size() + this.size;
    }

    public final boolean addAll(int i, Collection collection) {
        int i2 = 0;
        if (collection.isEmpty()) {
            return false;
        }
        ensureCapacity(collection.size() + this.size);
        Object[] objArr = this.content;
        if (i != this.size) {
            ArraysKt.copyInto(collection.size() + i, i, this.size, objArr, objArr);
        }
        for (Object obj : collection) {
            int i3 = i2 + 1;
            if (i2 >= 0) {
                objArr[i2 + i] = obj;
                i2 = i3;
            } else {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
        }
        this.size = collection.size() + this.size;
        return true;
    }
}
