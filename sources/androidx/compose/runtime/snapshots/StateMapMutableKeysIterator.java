package androidx.compose.runtime.snapshots;

import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class StateMapMutableKeysIterator extends StateMapMutableIterator implements Iterator, KMappedMarker {
    @Override // java.util.Iterator
    public final Object next() {
        Map.Entry entry = this.next;
        if (entry == null) {
            throw new IllegalStateException();
        }
        advance();
        return entry.getKey();
    }
}
