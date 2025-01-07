package androidx.compose.runtime.snapshots;

import java.util.ListIterator;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SubList$listIterator$1 implements ListIterator, KMappedMarker {
    public final /* synthetic */ Ref$IntRef $current;
    public final /* synthetic */ SubList this$0;

    public SubList$listIterator$1(Ref$IntRef ref$IntRef, SubList subList) {
        this.$current = ref$IntRef;
        this.this$0 = subList;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new IllegalStateException("Cannot modify a state list through an iterator");
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.$current.element < this.this$0.size - 1;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.$current.element >= 0;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        int i = this.$current.element + 1;
        SnapshotStateListKt.access$validateRange(i, this.this$0.size);
        this.$current.element = i;
        return this.this$0.get(i);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.$current.element + 1;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        int i = this.$current.element;
        SnapshotStateListKt.access$validateRange(i, this.this$0.size);
        this.$current.element = i - 1;
        return this.this$0.get(i);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.$current.element;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw new IllegalStateException("Cannot modify a state list through an iterator");
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new IllegalStateException("Cannot modify a state list through an iterator");
    }
}
