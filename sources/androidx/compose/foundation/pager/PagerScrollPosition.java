package androidx.compose.foundation.pager;

import androidx.compose.foundation.lazy.layout.LazyLayoutNearestRangeState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PagerScrollPosition {
    public final MutableIntState currentPage$delegate;
    public final MutableFloatState currentPageOffsetFraction$delegate;
    public boolean hadFirstNotEmptyLayout;
    public Object lastKnownCurrentPageKey;
    public final LazyLayoutNearestRangeState nearestRangeState;
    public final PagerState state;

    public PagerScrollPosition(int i, float f, PagerState pagerState) {
        this.state = pagerState;
        this.currentPage$delegate = SnapshotIntStateKt.mutableIntStateOf(i);
        this.currentPageOffsetFraction$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(f);
        this.nearestRangeState = new LazyLayoutNearestRangeState(i, 30, 100);
    }

    public final float getCurrentPageOffsetFraction() {
        return ((SnapshotMutableFloatStateImpl) this.currentPageOffsetFraction$delegate).getFloatValue();
    }
}
