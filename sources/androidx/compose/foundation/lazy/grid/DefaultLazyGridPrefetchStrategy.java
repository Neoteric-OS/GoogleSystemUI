package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.runtime.collection.MutableVector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DefaultLazyGridPrefetchStrategy implements LazyGridPrefetchStrategy {
    public boolean wasScrollingForward;
    public int lineToPrefetch = -1;
    public final MutableVector currentLinePrefetchHandles = new MutableVector(new LazyLayoutPrefetchState.PrefetchHandle[16]);
}
