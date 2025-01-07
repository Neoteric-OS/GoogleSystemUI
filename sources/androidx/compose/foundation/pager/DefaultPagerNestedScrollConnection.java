package androidx.compose.foundation.pager;

import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.unit.Velocity;
import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DefaultPagerNestedScrollConnection implements NestedScrollConnection {
    public final PagerState state;

    public DefaultPagerNestedScrollConnection(PagerState pagerState) {
        this.state = pagerState;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostFling-RZ2iAVY */
    public final Object mo69onPostFlingRZ2iAVY(long j, long j2, Continuation continuation) {
        return new Velocity(Velocity.m693copyOhffZ5M$default(0.0f, 0.0f, 1, j2));
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostScroll-DzOQY0M */
    public final long mo70onPostScrollDzOQY0M(int i, long j, long j2) {
        if (!NestedScrollSource.m454equalsimpl0(i, 2) || Float.intBitsToFloat((int) (j2 >> 32)) == 0.0f) {
            return 0L;
        }
        throw new CancellationException("Scroll cancelled");
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreScroll-OzD1aCk, reason: not valid java name */
    public final long mo139onPreScrollOzD1aCk(long j, int i) {
        if (NestedScrollSource.m454equalsimpl0(i, 1)) {
            PagerState pagerState = this.state;
            if (Math.abs(pagerState.scrollPosition.getCurrentPageOffsetFraction()) > 1.0E-6d) {
                PagerScrollPosition pagerScrollPosition = pagerState.scrollPosition;
                float currentPageOffsetFraction = pagerScrollPosition.getCurrentPageOffsetFraction() * pagerState.getPageSize$foundation_release();
                float f = ((((PagerMeasureResult) pagerState.getLayoutInfo()).pageSize + ((PagerMeasureResult) pagerState.getLayoutInfo()).pageSpacing) * (-Math.signum(pagerScrollPosition.getCurrentPageOffsetFraction()))) + currentPageOffsetFraction;
                if (pagerScrollPosition.getCurrentPageOffsetFraction() > 0.0f) {
                    f = currentPageOffsetFraction;
                    currentPageOffsetFraction = f;
                }
                float f2 = -pagerState.scrollableState.dispatchRawDelta(-RangesKt.coerceIn(Float.intBitsToFloat((int) (j >> 32)), currentPageOffsetFraction, f));
                float intBitsToFloat = Float.intBitsToFloat((int) (j & 4294967295L));
                return (Float.floatToRawIntBits(intBitsToFloat) & 4294967295L) | (Float.floatToRawIntBits(f2) << 32);
            }
        }
        return 0L;
    }
}
