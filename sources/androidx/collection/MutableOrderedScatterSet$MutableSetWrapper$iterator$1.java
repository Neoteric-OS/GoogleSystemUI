package androidx.collection;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequenceBuilderIterator;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutableOrderedScatterSet$MutableSetWrapper$iterator$1 implements Iterator, KMappedMarker {
    public int current = -1;
    public final SequenceBuilderIterator iterator;
    public final /* synthetic */ MutableOrderedScatterSet this$0;

    public MutableOrderedScatterSet$MutableSetWrapper$iterator$1(MutableOrderedScatterSet mutableOrderedScatterSet) {
        this.this$0 = mutableOrderedScatterSet;
        this.iterator = SequencesKt__SequenceBuilderKt.iterator(new MutableOrderedScatterSet$MutableSetWrapper$iterator$1$iterator$1(mutableOrderedScatterSet, this, null));
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
            this.this$0.removeElementAt(i);
            this.current = -1;
        }
    }
}
