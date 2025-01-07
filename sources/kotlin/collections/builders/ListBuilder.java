package kotlin.collections.builders;

import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation$$ExternalSyntheticOutline0;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.collections.AbstractList;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.ArraysKt;
import kotlin.collections.ArraysKt__ArraysJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ListBuilder extends AbstractMutableList implements List, RandomAccess, Serializable {
    public static final ListBuilder Empty;
    private Object[] array;
    private final ListBuilder backing;
    private boolean isReadOnly;
    private int length;
    private int offset;
    private final ListBuilder root;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Itr implements ListIterator, KMappedMarker {
        public int expectedModCount;
        public int index;
        public int lastIndex = -1;
        public final ListBuilder list;

        public Itr(ListBuilder listBuilder, int i) {
            this.list = listBuilder;
            this.index = i;
            this.expectedModCount = ((AbstractList) listBuilder).modCount;
        }

        @Override // java.util.ListIterator
        public final void add(Object obj) {
            checkForComodification$2();
            ListBuilder listBuilder = this.list;
            int i = this.index;
            this.index = i + 1;
            listBuilder.add(i, obj);
            this.lastIndex = -1;
            this.expectedModCount = ((AbstractList) this.list).modCount;
        }

        public final void checkForComodification$2() {
            if (((AbstractList) this.list).modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final boolean hasNext() {
            return this.index < this.list.length;
        }

        @Override // java.util.ListIterator
        public final boolean hasPrevious() {
            return this.index > 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final Object next() {
            checkForComodification$2();
            if (this.index >= this.list.length) {
                throw new NoSuchElementException();
            }
            int i = this.index;
            this.index = i + 1;
            this.lastIndex = i;
            return this.list.array[this.list.offset + this.lastIndex];
        }

        @Override // java.util.ListIterator
        public final int nextIndex() {
            return this.index;
        }

        @Override // java.util.ListIterator
        public final Object previous() {
            checkForComodification$2();
            int i = this.index;
            if (i <= 0) {
                throw new NoSuchElementException();
            }
            int i2 = i - 1;
            this.index = i2;
            this.lastIndex = i2;
            return this.list.array[this.list.offset + this.lastIndex];
        }

        @Override // java.util.ListIterator
        public final int previousIndex() {
            return this.index - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final void remove() {
            checkForComodification$2();
            int i = this.lastIndex;
            if (i == -1) {
                throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
            }
            this.list.removeAt(i);
            this.index = this.lastIndex;
            this.lastIndex = -1;
            this.expectedModCount = ((AbstractList) this.list).modCount;
        }

        @Override // java.util.ListIterator
        public final void set(Object obj) {
            checkForComodification$2();
            int i = this.lastIndex;
            if (i == -1) {
                throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
            }
            this.list.set(i, obj);
        }
    }

    static {
        ListBuilder listBuilder = new ListBuilder(0);
        listBuilder.isReadOnly = true;
        Empty = listBuilder;
    }

    public ListBuilder(Object[] objArr, int i, int i2, boolean z, ListBuilder listBuilder, ListBuilder listBuilder2) {
        this.array = objArr;
        this.offset = i;
        this.length = i2;
        this.isReadOnly = z;
        this.backing = listBuilder;
        this.root = listBuilder2;
        if (listBuilder != null) {
            ((AbstractList) this).modCount = ((AbstractList) listBuilder).modCount;
        }
    }

    private final Object writeReplace() {
        ListBuilder listBuilder;
        if (this.isReadOnly || ((listBuilder = this.root) != null && listBuilder.isReadOnly)) {
            return new SerializedCollection(0, this);
        }
        throw new NotSerializableException("The list cannot be serialized while it is being built.");
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        checkIsMutable();
        checkForComodification$3();
        addAtInternal(this.offset + this.length, obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        checkIsMutable();
        checkForComodification$3();
        int size = collection.size();
        addAllInternal(this.offset + this.length, collection, size);
        return size > 0;
    }

    public final void addAllInternal(int i, Collection collection, int i2) {
        ((AbstractList) this).modCount++;
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            listBuilder.addAllInternal(i, collection, i2);
            this.array = this.backing.array;
            this.length += i2;
        } else {
            insertAtInternal(i, i2);
            Iterator it = collection.iterator();
            for (int i3 = 0; i3 < i2; i3++) {
                this.array[i + i3] = it.next();
            }
        }
    }

    public final void addAtInternal(int i, Object obj) {
        ((AbstractList) this).modCount++;
        ListBuilder listBuilder = this.backing;
        if (listBuilder == null) {
            insertAtInternal(i, 1);
            this.array[i] = obj;
        } else {
            listBuilder.addAtInternal(i, obj);
            this.array = this.backing.array;
            this.length++;
        }
    }

    public final ListBuilder build() {
        if (this.backing != null) {
            throw new IllegalStateException();
        }
        checkIsMutable();
        this.isReadOnly = true;
        return this.length > 0 ? this : Empty;
    }

    public final void checkForComodification$3() {
        ListBuilder listBuilder = this.root;
        if (listBuilder != null && ((AbstractList) listBuilder).modCount != ((AbstractList) this).modCount) {
            throw new ConcurrentModificationException();
        }
    }

    public final void checkIsMutable() {
        ListBuilder listBuilder;
        if (this.isReadOnly || ((listBuilder = this.root) != null && listBuilder.isReadOnly)) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        checkIsMutable();
        checkForComodification$3();
        removeRangeInternal(this.offset, this.length);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        checkForComodification$3();
        if (obj != this) {
            if (!(obj instanceof List)) {
                return false;
            }
            List list = (List) obj;
            Object[] objArr = this.array;
            int i = this.offset;
            int i2 = this.length;
            if (i2 != list.size()) {
                return false;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                if (!Intrinsics.areEqual(objArr[i + i3], list.get(i3))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        checkForComodification$3();
        int i2 = this.length;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(ListImplementation$$ExternalSyntheticOutline0.m("index: ", i, i2, ", size: "));
        }
        return this.array[this.offset + i];
    }

    @Override // kotlin.collections.AbstractMutableList
    public final int getSize() {
        checkForComodification$3();
        return this.length;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        checkForComodification$3();
        Object[] objArr = this.array;
        int i = this.offset;
        int i2 = this.length;
        int i3 = 1;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i + i4];
            i3 = (i3 * 31) + (obj != null ? obj.hashCode() : 0);
        }
        return i3;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        checkForComodification$3();
        for (int i = 0; i < this.length; i++) {
            if (Intrinsics.areEqual(this.array[this.offset + i], obj)) {
                return i;
            }
        }
        return -1;
    }

    public final void insertAtInternal(int i, int i2) {
        int i3 = this.length + i2;
        if (i3 < 0) {
            throw new OutOfMemoryError();
        }
        Object[] objArr = this.array;
        if (i3 > objArr.length) {
            int length = objArr.length;
            int i4 = length + (length >> 1);
            if (i4 - i3 < 0) {
                i4 = i3;
            }
            if (i4 - 2147483639 > 0) {
                i4 = i3 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
            }
            this.array = Arrays.copyOf(objArr, i4);
        }
        Object[] objArr2 = this.array;
        ArraysKt.copyInto(i + i2, i, this.offset + this.length, objArr2, objArr2);
        this.length += i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        checkForComodification$3();
        return this.length == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        checkForComodification$3();
        for (int i = this.length - 1; i >= 0; i--) {
            if (Intrinsics.areEqual(this.array[this.offset + i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        checkIsMutable();
        checkForComodification$3();
        int indexOf = indexOf(obj);
        if (indexOf >= 0) {
            removeAt(indexOf);
        }
        return indexOf >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        checkIsMutable();
        checkForComodification$3();
        return retainOrRemoveAllInternal(false, collection, this.offset, this.length) > 0;
    }

    @Override // kotlin.collections.AbstractMutableList
    public final Object removeAt(int i) {
        checkIsMutable();
        checkForComodification$3();
        int i2 = this.length;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(ListImplementation$$ExternalSyntheticOutline0.m("index: ", i, i2, ", size: "));
        }
        return removeAtInternal(this.offset + i);
    }

    public final Object removeAtInternal(int i) {
        ((AbstractList) this).modCount++;
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            this.length--;
            return listBuilder.removeAtInternal(i);
        }
        Object[] objArr = this.array;
        Object obj = objArr[i];
        ArraysKt.copyInto(i, i + 1, this.offset + this.length, objArr, objArr);
        Object[] objArr2 = this.array;
        int i2 = this.offset;
        int i3 = this.length;
        objArr2[(i2 + i3) - 1] = null;
        this.length = i3 - 1;
        return obj;
    }

    public final void removeRangeInternal(int i, int i2) {
        if (i2 > 0) {
            ((AbstractList) this).modCount++;
        }
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            listBuilder.removeRangeInternal(i, i2);
        } else {
            Object[] objArr = this.array;
            ArraysKt.copyInto(i, i + i2, this.length, objArr, objArr);
            Object[] objArr2 = this.array;
            int i3 = this.length;
            ListBuilderKt.resetRange(objArr2, i3 - i2, i3);
        }
        this.length -= i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        checkIsMutable();
        checkForComodification$3();
        return retainOrRemoveAllInternal(true, collection, this.offset, this.length) > 0;
    }

    public final int retainOrRemoveAllInternal(boolean z, Collection collection, int i, int i2) {
        int i3;
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            i3 = listBuilder.retainOrRemoveAllInternal(z, collection, i, i2);
        } else {
            int i4 = 0;
            int i5 = 0;
            while (i4 < i2) {
                int i6 = i + i4;
                if (collection.contains(this.array[i6]) == z) {
                    Object[] objArr = this.array;
                    i4++;
                    objArr[i5 + i] = objArr[i6];
                    i5++;
                } else {
                    i4++;
                }
            }
            i3 = i2 - i5;
            Object[] objArr2 = this.array;
            ArraysKt.copyInto(i + i5, i2 + i, this.length, objArr2, objArr2);
            Object[] objArr3 = this.array;
            int i7 = this.length;
            ListBuilderKt.resetRange(objArr3, i7 - i3, i7);
        }
        if (i3 > 0) {
            ((AbstractList) this).modCount++;
        }
        this.length -= i3;
        return i3;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        checkIsMutable();
        checkForComodification$3();
        int i2 = this.length;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(ListImplementation$$ExternalSyntheticOutline0.m("index: ", i, i2, ", size: "));
        }
        Object[] objArr = this.array;
        int i3 = this.offset;
        Object obj2 = objArr[i3 + i];
        objArr[i3 + i] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i, int i2) {
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, this.length);
        Object[] objArr = this.array;
        int i3 = this.offset + i;
        int i4 = i2 - i;
        boolean z = this.isReadOnly;
        ListBuilder listBuilder = this.root;
        return new ListBuilder(objArr, i3, i4, z, this, listBuilder == null ? this : listBuilder);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        checkForComodification$3();
        int length = objArr.length;
        int i = this.length;
        if (length < i) {
            Object[] objArr2 = this.array;
            int i2 = this.offset;
            return Arrays.copyOfRange(objArr2, i2, i + i2, objArr.getClass());
        }
        Object[] objArr3 = this.array;
        int i3 = this.offset;
        ArraysKt.copyInto(0, i3, i + i3, objArr3, objArr);
        int i4 = this.length;
        if (i4 < objArr.length) {
            objArr[i4] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        checkForComodification$3();
        Object[] objArr = this.array;
        int i = this.offset;
        int i2 = this.length;
        StringBuilder sb = new StringBuilder((i2 * 3) + 2);
        sb.append("[");
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            Object obj = objArr[i + i3];
            if (obj == this) {
                sb.append("(this Collection)");
            } else {
                sb.append(obj);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        checkForComodification$3();
        int i2 = this.length;
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(ListImplementation$$ExternalSyntheticOutline0.m("index: ", i, i2, ", size: "));
        }
        return new Itr(this, i);
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        checkIsMutable();
        checkForComodification$3();
        int i2 = this.length;
        if (i >= 0 && i <= i2) {
            addAtInternal(this.offset + i, obj);
            return;
        }
        throw new IndexOutOfBoundsException(ListImplementation$$ExternalSyntheticOutline0.m("index: ", i, i2, ", size: "));
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        checkIsMutable();
        checkForComodification$3();
        int i2 = this.length;
        if (i >= 0 && i <= i2) {
            int size = collection.size();
            addAllInternal(this.offset + i, collection, size);
            return size > 0;
        }
        throw new IndexOutOfBoundsException(ListImplementation$$ExternalSyntheticOutline0.m("index: ", i, i2, ", size: "));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        checkForComodification$3();
        Object[] objArr = this.array;
        int i = this.offset;
        int i2 = this.length + i;
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, objArr.length);
        return Arrays.copyOfRange(objArr, i, i2);
    }

    public ListBuilder() {
        this(10);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ListBuilder(int i) {
        this(new Object[i], 0, 0, false, null, null);
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException("capacity must be non-negative.");
    }
}
