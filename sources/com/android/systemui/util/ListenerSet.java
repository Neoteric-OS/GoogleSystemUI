package com.android.systemui.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ListenerSet implements Collection, Set, KMappedMarker {
    public final CopyOnWriteArrayList listeners = new CopyOnWriteArrayList();

    @Override // java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean addIfAbsent(Object obj) {
        return this.listeners.addIfAbsent(obj);
    }

    @Override // java.util.Collection, java.util.Set
    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        return this.listeners.contains(obj);
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        return this.listeners.containsAll(collection);
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.listeners.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return this.listeners.iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }
        return this.listeners.remove(obj);
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.Set
    public final int size() {
        return this.listeners.size();
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] objArr) {
        return CollectionToArray.toArray(this, objArr);
    }
}
