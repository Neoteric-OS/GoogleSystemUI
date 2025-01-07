package com.google.common.collect;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ImmutableList extends ImmutableCollection implements List, RandomAccess {
    public static final Itr EMPTY_ITR = new Itr(RegularImmutableList.EMPTY, 0);
    private static final long serialVersionUID = -889275714;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Itr extends UnmodifiableIterator implements ListIterator {
        public final ImmutableList list;
        public int position;
        public final int size;

        public Itr(ImmutableList immutableList, int i) {
            int size = immutableList.size();
            Preconditions.checkPositionIndex(i, size);
            this.size = size;
            this.position = i;
            this.list = immutableList;
        }

        @Override // java.util.ListIterator
        public final void add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public final boolean hasNext() {
            return this.position < this.size;
        }

        @Override // java.util.ListIterator
        public final boolean hasPrevious() {
            return this.position > 0;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public final Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int i = this.position;
            this.position = i + 1;
            return this.list.get(i);
        }

        @Override // java.util.ListIterator
        public final int nextIndex() {
            return this.position;
        }

        @Override // java.util.ListIterator
        public final Object previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            int i = this.position - 1;
            this.position = i;
            return this.list.get(i);
        }

        @Override // java.util.ListIterator
        public final int previousIndex() {
            return this.position - 1;
        }

        @Override // java.util.ListIterator
        public final void set(Object obj) {
            throw new UnsupportedOperationException();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        public SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        public Object readResolve() {
            Object[] objArr = this.elements;
            Itr itr = ImmutableList.EMPTY_ITR;
            return objArr.length == 0 ? RegularImmutableList.EMPTY : ImmutableList.construct((Object[]) objArr.clone());
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class SubList extends ImmutableList {
        public final transient int length;
        public final transient int offset;

        public SubList(int i, int i2) {
            this.offset = i;
            this.length = i2;
        }

        @Override // java.util.List
        public final Object get(int i) {
            Preconditions.checkElementIndex(i, this.length);
            return ImmutableList.this.get(i + this.offset);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final Object[] internalArray() {
            return ImmutableList.this.internalArray();
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final int internalArrayEnd() {
            return ImmutableList.this.internalArrayStart() + this.offset + this.length;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final int internalArrayStart() {
            return ImmutableList.this.internalArrayStart() + this.offset;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public final int size() {
            return this.length;
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
        public Object writeReplace() {
            return super.writeReplace();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public final ImmutableList subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, this.length);
            ImmutableList immutableList = ImmutableList.this;
            int i3 = this.offset;
            return immutableList.subList(i + i3, i2 + i3);
        }
    }

    public static ImmutableList asImmutableList(int i, Object[] objArr) {
        return i == 0 ? RegularImmutableList.EMPTY : new RegularImmutableList(i, objArr);
    }

    public static ImmutableList construct(Object... objArr) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (objArr[i] == null) {
                throw new NullPointerException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "at index "));
            }
        }
        return asImmutableList(objArr.length, objArr);
    }

    public static ImmutableList copyOf(Iterable iterable) {
        Object[] objArr;
        iterable.getClass();
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (!(collection instanceof ImmutableCollection)) {
                return construct(collection.toArray());
            }
            ImmutableList asList = ((ImmutableCollection) collection).asList();
            if (!asList.isPartialView()) {
                return asList;
            }
            Object[] array = asList.toArray(ImmutableCollection.EMPTY_ARRAY);
            return asImmutableList(array.length, array);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return RegularImmutableList.EMPTY;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return construct(next);
        }
        CollectPreconditions.checkNonnegative(4, "initialCapacity");
        Object[] objArr2 = new Object[4];
        next.getClass();
        int i = 0 + 1;
        if (objArr2.length < i) {
            objArr2 = Arrays.copyOf(objArr2, ImmutableCollection.Builder.expandedCapacity(objArr2.length, i));
        }
        int i2 = 0 + 1;
        objArr2[0] = next;
        boolean z = false;
        while (it.hasNext()) {
            Object next2 = it.next();
            next2.getClass();
            int i3 = i2 + 1;
            if (objArr2.length < i3) {
                objArr = Arrays.copyOf(objArr2, ImmutableCollection.Builder.expandedCapacity(objArr2.length, i3));
            } else if (z) {
                objArr = (Object[]) objArr2.clone();
            } else {
                objArr2[i2] = next2;
                i2++;
            }
            objArr2 = objArr;
            z = false;
            objArr2[i2] = next2;
            i2++;
        }
        return asImmutableList(i2, objArr2);
    }

    public static ImmutableList of() {
        return RegularImmutableList.EMPTY;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] objArr) {
        int size = size();
        for (int i = 0; i < size; i++) {
            objArr[i] = get(i);
        }
        return size;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        Object next;
        int i;
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (!(list instanceof RandomAccess)) {
                    Iterator it = list.iterator();
                    for (Object obj2 : this) {
                        if (it.hasNext() && (r1 == (next = it.next()) || (obj2 != null && obj2.equals(next)))) {
                        }
                    }
                    return !it.hasNext();
                }
                for (0; i < size; i + 1) {
                    Object obj3 = get(i);
                    Object obj4 = list.get(i);
                    i = (obj3 == obj4 || (obj3 != null && obj3.equals(obj4))) ? i + 1 : 0;
                }
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = ~(~(get(i2).hashCode() + (i * 31)));
        }
        return i;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final UnmodifiableIterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(toArray(ImmutableCollection.EMPTY_ARRAY));
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public ImmutableList subList(int i, int i2) {
        Preconditions.checkPositionIndexes(i, i2, size());
        int i3 = i2 - i;
        return i3 == size() ? this : i3 == 0 ? RegularImmutableList.EMPTY : new SubList(i, i3);
    }

    @Override // java.util.List
    public final Itr listIterator(int i) {
        Preconditions.checkPositionIndex(i, size());
        if (isEmpty()) {
            return EMPTY_ITR;
        }
        return new Itr(this, i);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final ImmutableList asList() {
        return this;
    }
}
