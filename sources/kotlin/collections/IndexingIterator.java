package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IndexingIterator implements Iterator, KMappedMarker {
    public int index;
    public final Iterator iterator;

    public IndexingIterator(Iterator it) {
        this.iterator = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.index;
        this.index = i + 1;
        if (i >= 0) {
            return new IndexedValue(i, this.iterator.next());
        }
        CollectionsKt__CollectionsKt.throwIndexOverflow();
        throw null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
