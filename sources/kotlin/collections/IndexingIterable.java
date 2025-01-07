package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IndexingIterable implements Iterable, KMappedMarker {
    public final Function0 iteratorFactory;

    public IndexingIterable(Function0 function0) {
        this.iteratorFactory = function0;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new IndexingIterator(((CollectionsKt___CollectionsKt$withIndex$1) this.iteratorFactory).$this_withIndex.iterator());
    }
}
