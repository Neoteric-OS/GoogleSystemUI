package androidx.leanback.widget;

import androidx.recyclerview.widget.GapWorker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Grid {
    public int mFirstVisibleIndex;
    public int mLastVisibleIndex;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Location {
    }

    public abstract boolean appendVisibleItems(int i, boolean z);

    public abstract void collectAdjacentPrefetchPositions(int i, int i2, GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl);

    public abstract int findRowMax(boolean z, int i, int[] iArr);

    public abstract int findRowMin(boolean z, int i, int[] iArr);

    public abstract Location getLocation(int i);

    public abstract boolean prependVisibleItems(int i, boolean z);
}
