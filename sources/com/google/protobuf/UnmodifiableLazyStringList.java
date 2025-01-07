package com.google.protobuf;

import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnmodifiableLazyStringList extends AbstractList implements LazyStringList, RandomAccess {
    public final LazyStringArrayList list;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.protobuf.UnmodifiableLazyStringList$1, reason: invalid class name */
    public final class AnonymousClass1 implements ListIterator {
        public ListIterator iter;

        @Override // java.util.ListIterator
        public final void add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final boolean hasNext() {
            return this.iter.hasNext();
        }

        @Override // java.util.ListIterator
        public final boolean hasPrevious() {
            return this.iter.hasPrevious();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final Object next() {
            return (String) this.iter.next();
        }

        @Override // java.util.ListIterator
        public final int nextIndex() {
            return this.iter.nextIndex();
        }

        @Override // java.util.ListIterator
        public final Object previous() {
            return (String) this.iter.previous();
        }

        @Override // java.util.ListIterator
        public final int previousIndex() {
            return this.iter.previousIndex();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public final void set(Object obj) {
            throw new UnsupportedOperationException();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.protobuf.UnmodifiableLazyStringList$2, reason: invalid class name */
    public final class AnonymousClass2 implements Iterator {
        public Iterator iter;

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.iter.hasNext();
        }

        @Override // java.util.Iterator
        public final Object next() {
            return (String) this.iter.next();
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public UnmodifiableLazyStringList(LazyStringArrayList lazyStringArrayList) {
        this.list = lazyStringArrayList;
    }

    @Override // com.google.protobuf.LazyStringList
    public final void add(ByteString byteString) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        return (String) this.list.get(i);
    }

    @Override // com.google.protobuf.LazyStringList
    public final Object getRaw(int i) {
        return this.list.list.get(i);
    }

    @Override // com.google.protobuf.LazyStringList
    public final List getUnderlyingElements() {
        return Collections.unmodifiableList(this.list.list);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.iter = this.list.iterator();
        return anonymousClass2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.iter = this.list.listIterator(i);
        return anonymousClass1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.list.list.size();
    }

    @Override // com.google.protobuf.LazyStringList
    public final LazyStringList getUnmodifiableView() {
        return this;
    }
}
