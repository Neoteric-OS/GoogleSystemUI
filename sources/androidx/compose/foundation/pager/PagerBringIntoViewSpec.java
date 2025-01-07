package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.BringIntoViewSpec;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PagerBringIntoViewSpec implements BringIntoViewSpec {
    public final BringIntoViewSpec defaultBringIntoViewSpec;
    public final PagerState pagerState;

    public PagerBringIntoViewSpec(PagerState pagerState, BringIntoViewSpec bringIntoViewSpec) {
        this.pagerState = pagerState;
        this.defaultBringIntoViewSpec = bringIntoViewSpec;
    }

    @Override // androidx.compose.foundation.gestures.BringIntoViewSpec
    public final float calculateScrollDistance(float f, float f2, float f3) {
        float calculateScrollDistance = this.defaultBringIntoViewSpec.calculateScrollDistance(f, f2, f3);
        PagerState pagerState = this.pagerState;
        if (calculateScrollDistance == 0.0f) {
            int i = pagerState.firstVisiblePageOffset;
            if (i == 0) {
                return 0.0f;
            }
            float f4 = i * (-1.0f);
            if (((Boolean) ((SnapshotMutableStateImpl) pagerState.isLastScrollForwardState).getValue()).booleanValue()) {
                f4 += pagerState.getPageSizeWithSpacing$foundation_release();
            }
            return RangesKt.coerceIn(f4, -f3, f3);
        }
        float f5 = pagerState.firstVisiblePageOffset * (-1);
        while (calculateScrollDistance > 0.0f && f5 < calculateScrollDistance) {
            f5 += pagerState.getPageSizeWithSpacing$foundation_release();
        }
        float f6 = f5;
        while (calculateScrollDistance < 0.0f && f6 > calculateScrollDistance) {
            f6 -= pagerState.getPageSizeWithSpacing$foundation_release();
        }
        return f6;
    }
}
