package com.android.systemui.classifier;

import android.view.InputEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TimeLimitedInputEventBuffer implements List {
    public final List mInputEvents = new ArrayList();
    public final long mMaxAgeMs;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Iter implements ListIterator {
        public final ListIterator mIterator;

        public Iter(TimeLimitedInputEventBuffer timeLimitedInputEventBuffer, int i) {
            this.mIterator = timeLimitedInputEventBuffer.mInputEvents.listIterator(i);
        }

        @Override // java.util.ListIterator
        public final void add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final boolean hasNext() {
            return this.mIterator.hasNext();
        }

        @Override // java.util.ListIterator
        public final boolean hasPrevious() {
            return this.mIterator.hasPrevious();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final Object next() {
            return (InputEvent) this.mIterator.next();
        }

        @Override // java.util.ListIterator
        public final int nextIndex() {
            return this.mIterator.nextIndex();
        }

        @Override // java.util.ListIterator
        public final Object previous() {
            return (InputEvent) this.mIterator.previous();
        }

        @Override // java.util.ListIterator
        public final int previousIndex() {
            return this.mIterator.previousIndex();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final void remove() {
            this.mIterator.remove();
        }

        @Override // java.util.ListIterator
        public final void set(Object obj) {
            throw new UnsupportedOperationException();
        }
    }

    public TimeLimitedInputEventBuffer(long j) {
        this.mMaxAgeMs = j;
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        boolean addAll = this.mInputEvents.addAll(collection);
        ejectOldEvents();
        return addAll;
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        this.mInputEvents.clear();
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return this.mInputEvents.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.mInputEvents.containsAll(collection);
    }

    public final void ejectOldEvents() {
        if (this.mInputEvents.isEmpty()) {
            return;
        }
        ListIterator listIterator = listIterator();
        long eventTime = ((InputEvent) ((ArrayList) this.mInputEvents).get(((ArrayList) r1).size() - 1)).getEventTime();
        while (true) {
            Iter iter = (Iter) listIterator;
            if (!iter.mIterator.hasNext()) {
                return;
            }
            InputEvent inputEvent = (InputEvent) iter.next();
            if (eventTime - inputEvent.getEventTime() > this.mMaxAgeMs) {
                iter.remove();
                inputEvent.recycle();
            }
        }
    }

    @Override // java.util.List, java.util.Collection
    public final boolean equals(Object obj) {
        return this.mInputEvents.equals(obj);
    }

    @Override // java.util.List
    public final Object get(int i) {
        return (InputEvent) ((ArrayList) this.mInputEvents).get(i);
    }

    @Override // java.util.List, java.util.Collection
    public final int hashCode() {
        return this.mInputEvents.hashCode();
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        return this.mInputEvents.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return this.mInputEvents.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.mInputEvents.iterator();
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        return this.mInputEvents.lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return new Iter(this, 0);
    }

    @Override // java.util.List
    public final Object remove(int i) {
        return (InputEvent) this.mInputEvents.remove(i);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        return this.mInputEvents.removeAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        return this.mInputEvents.retainAll(collection);
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return ((ArrayList) this.mInputEvents).size();
    }

    @Override // java.util.List
    public final List subList(int i, int i2) {
        return this.mInputEvents.subList(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return this.mInputEvents.toArray();
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        return new Iter(this, i);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        return this.mInputEvents.remove(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.mInputEvents.toArray(objArr);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        boolean add = this.mInputEvents.add((InputEvent) obj);
        ejectOldEvents();
        return add;
    }

    @Override // java.util.List
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }
}
