package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FlatteningSequence$iterator$1 implements Iterator, KMappedMarker {
    public Iterator itemIterator;
    public final Iterator iterator;
    public final /* synthetic */ FlatteningSequence this$0;

    public FlatteningSequence$iterator$1(FlatteningSequence flatteningSequence) {
        this.this$0 = flatteningSequence;
        this.iterator = flatteningSequence.sequence.iterator();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean ensureItemIterator() {
        /*
            r4 = this;
            java.util.Iterator r0 = r4.itemIterator
            if (r0 == 0) goto Ld
            boolean r0 = r0.hasNext()
            if (r0 != 0) goto Ld
            r0 = 0
            r4.itemIterator = r0
        Ld:
            java.util.Iterator r0 = r4.itemIterator
            r1 = 1
            if (r0 != 0) goto L3a
            java.util.Iterator r0 = r4.iterator
            boolean r0 = r0.hasNext()
            if (r0 != 0) goto L1c
            r4 = 0
            return r4
        L1c:
            java.util.Iterator r0 = r4.iterator
            java.lang.Object r0 = r0.next()
            kotlin.sequences.FlatteningSequence r2 = r4.this$0
            kotlin.jvm.functions.Function1 r3 = r2.iterator
            kotlin.jvm.functions.Function1 r2 = r2.transformer
            java.lang.Object r0 = r2.invoke(r0)
            java.lang.Object r0 = r3.invoke(r0)
            java.util.Iterator r0 = (java.util.Iterator) r0
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto Ld
            r4.itemIterator = r0
        L3a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.FlatteningSequence$iterator$1.ensureItemIterator():boolean");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return ensureItemIterator();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!ensureItemIterator()) {
            throw new NoSuchElementException();
        }
        Iterator it = this.itemIterator;
        Intrinsics.checkNotNull(it);
        return it.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
