package androidx.compose.ui.graphics.vector;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class VectorGroup$iterator$1 implements Iterator, KMappedMarker {
    public final Iterator it;

    public VectorGroup$iterator$1(VectorGroup vectorGroup) {
        this.it = vectorGroup.children.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.it.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return (VectorNode) this.it.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
