package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import java.util.ListIterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AbstractListIterator implements ListIterator, KMappedMarker {
    public int index;
    public int size;

    public AbstractListIterator(int i, int i2) {
        this.index = i;
        this.size = i2;
    }

    @Override // java.util.ListIterator
    public void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.index < this.size;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.index > 0;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.index;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.index - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator
    public void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
