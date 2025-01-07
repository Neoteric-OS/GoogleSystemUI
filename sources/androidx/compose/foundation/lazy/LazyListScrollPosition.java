package androidx.compose.foundation.lazy;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutNearestRangeState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyListScrollPosition {
    public boolean hadFirstNotEmptyLayout;
    public final MutableIntState index$delegate;
    public Object lastKnownFirstItemKey;
    public final LazyLayoutNearestRangeState nearestRangeState;
    public final MutableIntState scrollOffset$delegate;

    public LazyListScrollPosition(int i, int i2) {
        this.index$delegate = SnapshotIntStateKt.mutableIntStateOf(i);
        this.scrollOffset$delegate = SnapshotIntStateKt.mutableIntStateOf(i2);
        this.nearestRangeState = new LazyLayoutNearestRangeState(i, 30, 100);
    }

    public final int getIndex() {
        return ((SnapshotMutableIntStateImpl) this.index$delegate).getIntValue();
    }

    public final int getScrollOffset() {
        return ((SnapshotMutableIntStateImpl) this.scrollOffset$delegate).getIntValue();
    }

    public final void update(int i, int i2) {
        if (i < 0.0f) {
            InlineClassHelperKt.throwIllegalArgumentException("Index should be non-negative (" + i + ')');
        }
        ((SnapshotMutableIntStateImpl) this.index$delegate).setIntValue(i);
        this.nearestRangeState.update(i);
        ((SnapshotMutableIntStateImpl) this.scrollOffset$delegate).setIntValue(i2);
    }
}
