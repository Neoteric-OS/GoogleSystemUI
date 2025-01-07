package androidx.collection;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequenceBuilderIterator;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutableScatterSet$MutableSetWrapper$iterator$1 implements Iterator, KMappedMarker {
    public int current = -1;
    public final SequenceBuilderIterator iterator;
    public final MutableScatterSet parent;

    public MutableScatterSet$MutableSetWrapper$iterator$1(MutableScatterSet mutableScatterSet) {
        this.parent = mutableScatterSet;
        this.iterator = SequencesKt__SequenceBuilderKt.iterator(new MutableScatterSet$MutableSetWrapper$iterator$1$iterator$1(this, mutableScatterSet, null));
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.iterator.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        int i = this.current;
        if (i != -1) {
            this.parent.removeElementAt(i);
            this.current = -1;
        }
    }
}
